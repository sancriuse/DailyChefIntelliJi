// NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
//                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Catalog;
import model.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// represents a reader that reads JSON data from source file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads catalog from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Catalog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCatalog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses catalog from JSON object and returns it
    private Catalog parseCatalog(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Catalog c = new Catalog(name);
        addRecipes(c, jsonObject);
        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses recipes from JSON object and adds them to catalog.
    private void addRecipes(Catalog c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipeCatalog");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addOneRecipe(c, nextRecipe);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses recipe from JSON object and adds it to catalog
    private void addOneRecipe(Catalog c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int calorieIntake = jsonObject.getInt("calorieIntake");
        int duration = jsonObject.getInt("duration");
        String ingredients = jsonObject.getString("ingredients");
        int rating = jsonObject.getInt("rating");

        Recipe r = new Recipe(name, calorieIntake, duration, ingredients, rating);
        c.addRecipe(r);
    }
}

