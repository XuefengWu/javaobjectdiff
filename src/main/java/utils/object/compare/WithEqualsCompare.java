package utils.object.compare;

import java.lang.reflect.Method;

public class WithEqualsCompare implements CustomCompare {

    public boolean isAcceptType(Object o) {
        Method[]  declaredMethods = o.getClass().getDeclaredMethods();
        for(Method m: declaredMethods){
            if("equals".equalsIgnoreCase(m.getName())){
                return true;
            }
        }
        return false;
    }

    public Diff compare(Object o1, Object o2) {
        boolean res = o1.equals(o2);
        if(!res){
            return new Diff(o1,o2,"WithEqualsCompare: " + o1 + " != " + o2);
        }
        return new Diff();
    }
}
