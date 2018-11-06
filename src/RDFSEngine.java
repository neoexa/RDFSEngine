import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class RDFSEngine {

    public static void main(String[] args) throws IOException {
        boolean verbose = false, exportResults = false, exportStats = false, workloadTime = false;
        if(args.length != 3) {
            System.out.println("At least 3 arguments ");
        }
        String queries = args[1];
        String data = args[2];
        String output = args[3];
        if(args[4].equals("-verbose")) verbose = true;
        if(args[5].equals("-export_results")) exportResults = true;
        if(args[6].equals("-export_stats")) exportStats = true;
        if(args[7].equals("-workload_time")) workloadTime = true;




        RDFRawParser parser = new RDFRawParser();
        parser.parse(data);


        Dictionary mDictionary = new Dictionary(parser.getListener());
        Index mIndex = new Index(mDictionary, parser.getListener());
        Model mModel = new Model(mIndex, mDictionary);
        QueryFactory factory = new QueryFactory();



        String aggFileName = String.valueOf(output + "results.txt");
        FileWriter fstream = new FileWriter(aggFileName);
        BufferedWriter out = new BufferedWriter(fstream);

        String aggFileName3 = String.valueOf(output + "exportStats.txt");
        FileWriter fstream3 = new FileWriter(aggFileName);
        BufferedWriter out3 = new BufferedWriter(fstream);


		int x = 0;
        long startTime = System.currentTimeMillis();
        for(ArrayList<Query> aq : factory.loadFromFolder(queries)) {
            x++;
            int i=0;
            for(Query q : aq) {
                QueryExecutioner qe = new QueryExecutioner(q, mModel);
                for(String s: qe.execute()){
                    if (exportResults)
                    out.write("Requete n° : "+ x + s + "\n");
                    if (exportStats) {
                        out.write(qe.getEvalOrdre().toString() + "\n");
                    }
                }

            }
        }

        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;

        if(workloadTime) {
            String aggFileName2 = String.valueOf(output + "exec_time.txt");
            FileWriter fstream2 = new FileWriter(aggFileName);
            BufferedWriter out2 = new BufferedWriter(fstream);
            System.out.println(elapsedTime + " Milliseconds");
            out2.write(elapsedTime + " Milliseconds");

        }
    }



}
