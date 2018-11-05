package utils.object.compare;

public interface CustomCompare {

    public boolean isAcceptType(Object o);
    public Diff compare(Object o1, Object o2);

}
