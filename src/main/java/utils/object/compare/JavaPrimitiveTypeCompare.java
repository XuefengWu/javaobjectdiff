package utils.object.compare;

public class JavaPrimitiveTypeCompare implements CustomCompare {

    public boolean isAcceptType(Object o) {
        String typ = o.getClass().getCanonicalName();
        return "int".equalsIgnoreCase(typ) || "long".equalsIgnoreCase(typ)
                || "float".equalsIgnoreCase(typ) || "byte".equalsIgnoreCase(typ)
                || "short".equalsIgnoreCase(typ) || "double".equalsIgnoreCase(typ)
                || "boolean".equalsIgnoreCase(typ);
    }

    public Diff compare(Object o1, Object o2) {
        boolean res = o1.equals(o2);
        if(!res){
            return new Diff(o1,o2,"JavaPrimitiveTypeCompare: " + o1 + " != " + o2);
        }
        return new Diff();
    }

}
