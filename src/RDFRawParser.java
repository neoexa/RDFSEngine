import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.Rio;

public final class RDFRawParser {

    public static void main(String args[]) throws IOException {


        Reader reader = new FileReader("../RDFSEngine/data/100K.rdfxml");

        org.openrdf.rio.RDFParser rdfParser = Rio
                .createParser(RDFFormat.RDFXML);

        RDFListener list = new RDFListener();
        rdfParser.setRDFHandler(list);

        try {rdfParser.parse(reader, "");} catch (Exception e) {}

        try {reader.close();} catch (IOException e) {}

        Dictionary dico = new Dictionary(list);
        Index index = new Index(dico, list);

        // dico.writeDico();
        // dico.writeBase();

        System.out.println("Result : " + query(index, dico , "http://db.uwaterloo.ca/~galuc/wsdbm/userId", "9279708" ).toString());

    }




    }
}