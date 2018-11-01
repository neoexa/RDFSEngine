import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Index {


    //TreeSet for Ordering S index (For later sort-merge-join)
    private HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> pos =  new HashMap<>();
    private HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> ops =  new HashMap<>();

    // Create Index ops and pos
    public Index(Dictionary dico, RDFListener list) {

        int i=0;
        while(i<list.nbTriple){

            int o = dico.getDico().get(list.objects.get(i));
            int p = dico.getDico().get(list.predicates.get(i));
            int s = dico.getDico().get(list.subjects.get(i));

            ops.putIfAbsent(o, new HashMap<Integer, TreeSet<Integer>>()) ;

            HashMap<Integer, TreeSet<Integer>> hashPS = ops.get(o);
            hashPS.putIfAbsent(p, new TreeSet<Integer>());
            hashPS.get(p).add(s);

            pos.putIfAbsent(p, new HashMap<Integer, TreeSet<Integer> >() ) ;
            HashMap<Integer, TreeSet<Integer>> hashOS = pos.get(p);
            hashOS.putIfAbsent(o, new TreeSet<Integer>());
            hashOS.get(o).add(s);

            i++;
        }


    }

    public HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> getPos() {
        return pos;
    }

    public void setPos(HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> pos) {
        this.pos = pos;
    }

    public HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> getOps() {
        return ops;
    }

    public void setOps(HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> ops) {
        this.ops = ops;
    }



}
