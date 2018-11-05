package utils.object.compare;

public class JavaArrayCompare implements CustomCompare {

    private ObjectDeepCompare objectCompare;

    public JavaArrayCompare(ObjectDeepCompare objectCompare) {
        this.objectCompare = objectCompare;
    }

    public boolean isAcceptType(Object o) {
        return o!=null && o.getClass().isArray();
    }

    public Diff compare(Object o1, Object o2) {
        Object[] o1s = (Object[]) o1;
        Object[] o2s = (Object[]) o2;
        for (int i = 0; i < o1s.length; i++) {
            Object v1 = o1s[i];
            Object v2 = o2s[i];
            Diff diff = objectCompare.compare(v1, v2);
            if(!diff.isEmpty()) {
                return diff;
            }
        }
        return new Diff();
    }
}
