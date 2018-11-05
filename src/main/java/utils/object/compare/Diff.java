package utils.object.compare;

public class Diff {

    private boolean isEmpty = true;
    private Object o1;
    private Object o2;
    private String message;


    public Diff(Object o1,Object o2, String message) {
        this.o1 = o1;
        this.o2 = o2;
        this.message = message;
        this.isEmpty = false;
    }

    public Diff(){}
    public static Diff empty() {
        return new Diff();
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Diff withMessage(String message) {
        this.message = message + "\n\t" + this.message;
        return this;
    }

}
