package utils.object.compare;

import java.util.Collection;
public class JavaCollectionCompare implements CustomCompare {

    private ObjectDeepCompare objectCompare;
    public JavaCollectionCompare(ObjectDeepCompare objectCompare) {
        this.objectCompare = objectCompare;
    }

    public boolean isAcceptType(Object o) {
        return o instanceof Collection;
    }

    public Diff compare(Object o1, Object o2) {
        Collection c1 = (Collection)o1;
        Collection c2 = (Collection)o2;
        if(c1.size() != c2.size()) {
            return new Diff(c1.size(),c2.size(),"JavaCollectionCompare size is not equal");
        }
        Object[] o1s = c1.toArray();
        Object[] o2s = c2.toArray();
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
