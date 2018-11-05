package utils.object.compare;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ObjectDeepCompare {

    private JavaPrimitiveTypeCompare basicTypeCompare = new JavaPrimitiveTypeCompare();
    private WithEqualsCompare equalsCompare = new WithEqualsCompare();
    private JavaCollectionCompare collectionCompare = new JavaCollectionCompare(this);
    private JavaArrayCompare arrayCompare = new JavaArrayCompare(this);
    private List<CustomCompare> customCompares = new LinkedList<>();

    public ObjectDeepCompare() {
        customCompares.add(equalsCompare);
        customCompares.add(basicTypeCompare);
        customCompares.add(collectionCompare);
        customCompares.add(arrayCompare);
    }

    public ObjectDeepCompare addCustomerCompareAhead(CustomCompare compare) {
        customCompares.add(0,compare);
        return this;
    }

    public ObjectDeepCompare addCustomerCompareLast(CustomCompare compare) {
        customCompares.add(customCompares.size()-1,compare);
        return this;
    }

    public Diff compare(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return Diff.empty();
        }
        if (!o1.getClass().equals(o2.getClass())) {
            String message = String.format("o1 class:%s is not equals o2 class:%s" + o1.getClass() + o2.getClass());
            return new Diff(o1, o2, message);
        }

        for (CustomCompare cc : customCompares) {
            if (cc.isAcceptType(o1)) {
                return cc.compare(o1, o2);
            }
        }

        try {
            Field[] fields = o1.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }
                Object v1 = field.get(o1);
                String fieldName = field.getName();
                Object v2 = getFieldValue(o2, fieldName);
                Diff diff = compare(v1, v2);
                if (!diff.isEmpty()) {
                    return diff.withMessage("ObjectDeepCompare v1: " + v1 + ", v2: " + v2 + " is not equal");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Diff(o1, o2, e.getMessage());
        }
        return Diff.empty();
    }

    public Object getFieldValue(Object o2, String fieldName) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {

        Field field = o2.getClass().getDeclaredField(fieldName);
        if (Modifier.isPrivate(field.getModifiers())) {
            field.setAccessible(true);
        }
        return field.get(o2);
    }

}
