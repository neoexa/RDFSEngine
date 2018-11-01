import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class RDFSEngine {

    public static void main(String[] args) throws IOException {

        RDFRawParser parser = new RDFRawParser();
        parser.parse("../RDFSEngine/data/100K.rdfxml");

        Dictionary mDictionary = new Dictionary(parser.getList());
        Index mIndex = new Index(mDictionary, parser.getList());

        Model mModel = new Model(mIndex, mDictionary);

        QueryFactory factory = new QueryFactory();

        String mQuery = "SELECT ?v0 WHERE {\n" +
                "\t?v0 <http://purl.org/dc/terms/Location> <http://db.uwaterloo.ca/~galuc/wsdbm/City73> .\n" +
                "\t?v0 <http://db.uwaterloo.ca/~galuc/wsdbm/gender> <http://db.uwaterloo.ca/~galuc/wsdbm/Gender0> .\n" +
                "\t?v0 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://db.uwaterloo.ca/~galuc/wsdbm/Role2> .\n" +
                "} ";
        Query q = factory.createSimpleQuery(mQuery);

        //System.out.print(q.getConditions().toString());

        QueryExecutioner qe = new QueryExecutioner(q, mModel);

        qe.execute();



    }


}
