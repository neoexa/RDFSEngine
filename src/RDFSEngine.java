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




        //Return ArrayList containing ArrayList<Query> for each file
        //factory.loadFromFolder("../RDFprojet/queries");

        //Return ArrayList containing each query
		/*for(Query q2 : factory.loadFromFile("../RDFprojet/queries/Q_1_eligibleregion.queryset") ) {
			QueryExecutioner qe2 = new QueryExecutioner(q2, mModel);
	        System.out.println(qe2.execute());
		}/*


		/*
		ArrayList<Query> aq = factory.loadFromFile("../RDFprojet/queries/Q_4_location_nationality_gender_type.queryset");
		int i=0;
		for(Query q : aq) {
			i++;
			System.out.println("Query "+i + " : ");
			System.out.println(q.toString());
		}
		*/

        String aggFileName = String.valueOf(output + "output.txt");
        FileWriter fstream = new FileWriter(aggFileName);
        BufferedWriter out = new BufferedWriter(fstream);

		int x = 0;
        long startTime = System.currentTimeMillis();
        for(ArrayList<Query> aq : factory.loadFromFolder(queries)) {
            x++;
            int i=0;
            for(Query q : aq) {
                QueryExecutioner qe = new QueryExecutioner(q, mModel);
                for(String s: qe.execute()){
                    out.write(s+ "\n");
                }

            }
        }

        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + " Milliseconds");

    }



}
