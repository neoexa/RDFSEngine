import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class RDFSEngine {

    public static void main(String[] args) throws IOException {
        RDFRawParser parser = new RDFRawParser();
        parser.parse("../RDFSEngine/data/500K.rdfxml");

        Dictionary mDictionary = new Dictionary(parser.getList());
        Index mIndex = new Index(mDictionary, parser.getList());
        Model mModel = new Model(mIndex, mDictionary);
        QueryFactory factory = new QueryFactory();

        /*String mQuery = "SELECT ?v0 WHERE {\n" +
                "\t?v0 <http://purl.org/dc/terms/Location> <http://db.uwaterloo.ca/~galuc/wsdbm/City20> .\n" +
                "\t?v0 <http://db.uwaterloo.ca/~galuc/wsdbm/gender> <http://db.uwaterloo.ca/~galuc/wsdbm/Gender0> .\n" +
                "\t?v0 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://db.uwaterloo.ca/~galuc/wsdbm/Role2> .\n" +
                "}";*/
        //Query q = factory.createSimpleQuery(mQuery);

        //QueryExecutioner qe = new QueryExecutioner(q, mModel);

        //System.out.println(qe.execute());


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


		int x = 0;
        long startTime = System.currentTimeMillis();
        for(ArrayList<Query> aq : factory.loadFromFolder("../RDFSEngine/data/querySet")) {
            x++;
            int i=0;
            for(Query q : aq) {
                QueryExecutioner qe = new QueryExecutioner(q, mModel);
                qe.execute();
            }
        }

        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + " Milliseconds");

    }



}
