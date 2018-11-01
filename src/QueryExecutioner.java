import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;

public class QueryExecutioner {
    Query mQuery;
    Model mModel;
    Dictionary mDico;
    Index mIndex;

    public QueryExecutioner(Query q, Model m) {
        this.mQuery = q;
        this.mModel = m;
        this.mDico = mModel.getmDictionary();
        this.mIndex = mModel.getmIndex();
    }

    public ArrayList<String> execute() {
        ArrayList<String> results = new ArrayList<>();
        Stack<TreeSet<Integer>> toMergeJoin = new Stack<>();
        ArrayList<Condition> mConditions = mQuery.getConditions();


        for (Condition c : mConditions) {
            int Ipredicate = mDico.getDico().get(c.getP());
            int Iobject = mDico.getDico().get(c.getO());

            //Optimisation !!!
            TreeSet<Integer> Isubject = mIndex.getPos().get(Ipredicate).get(Iobject);

            toMergeJoin.add(Isubject);

            System.out.println(Isubject.toString());

        }

        while (toMergeJoin.size() > 1) {
            TreeSet<Integer> res1 = toMergeJoin.pop();
            TreeSet<Integer> res2 = toMergeJoin.pop();

            TreeSet<Integer> tmp = intersection(res1, res2);
            toMergeJoin.push(tmp);
        }
        TreeSet<Integer> finalRes = toMergeJoin.pop();

        for(Integer s: finalRes) {
            results.add(mDico.getBase().get(s));
        }

        return results;
    }

    public static TreeSet<Integer> intersection(TreeSet<Integer> a, TreeSet<Integer> b) {
        // unnecessary; just an optimization to iterate over the smaller set
        if (a.size() > b.size()) {
            return intersection(b, a);
        }

        TreeSet<Integer> results = new TreeSet<>();

        for (Integer element : a) {
            if (b.contains(element)) {
                results.add(element);
            }
        }

        return results;
    }

    //public String getExecTime(){}
}
