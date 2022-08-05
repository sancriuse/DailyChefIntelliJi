// NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
//                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Catalog;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents a writer that writes to destination file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter catalogWriter;
    private String finalCatalog;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.finalCatalog = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //           be opened for writing
    public void open() throws FileNotFoundException {
        catalogWriter = new PrintWriter(new File(finalCatalog));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of catalog to file
    public void write(Catalog c) {
        JSONObject json = c.convertCatalog();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        catalogWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        catalogWriter.print(json);
    }
}

