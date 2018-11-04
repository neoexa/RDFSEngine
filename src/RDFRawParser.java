import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.Rio;

public final class RDFRawParser {

    RDFListener listener;

    public void parse(String path) throws FileNotFoundException {

        Reader reader = new FileReader(path);

        org.openrdf.rio.RDFParser rdfParser = Rio
                .createParser(RDFFormat.RDFXML);

        listener = new RDFListener();

        rdfParser.setRDFHandler(listener);

        try {rdfParser.parse(reader, "");} catch (Exception e) {}

        try {reader.close();} catch (IOException e) {}
    }

    public void toString(int i) {
        for(int x=0; x<i; x++) {
            System.out.println("------");
            System.out.println("Predicate : ["+ listener.getPredicates().get(x).toString() + "]");
            System.out.println("Object : ["+ listener.getObjects().get(x).toString() + "]");
            System.out.println("Subject : ["+ listener.getSubjects().get(x).toString()+ "]");

        }
    }

    public RDFListener getListener() {
        return listener;
    }

    public void setListener(RDFListener listener) {
        this.listener = listener;
    }



}