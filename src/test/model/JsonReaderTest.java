// NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
//                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest extends SaveLoadTest {

    @Test
    void writerInvalidTest() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            Catalog recipeCatalog5 = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readerEmptyTest() {
        JsonReader reader = new JsonReader("./data/readerEmptyTest.json");
        try {
            Catalog emptyCatalog = reader.read();
            assertEquals("Empty recipes", emptyCatalog.getNameOfCatalog());
            assertEquals(0, emptyCatalog.getNumOfRecipes());
         } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void readerFileTest() {
        JsonReader reader = new JsonReader("./data/readerFileTest.json");
        try {
            Catalog meatCatalog = reader.read();
            assertEquals("Meat recipes", meatCatalog.getNameOfCatalog());
            ArrayList<Recipe> recipes = meatCatalog.getRecipes();
            assertEquals(1, meatCatalog.getNumOfRecipes());
            scanRecipe("Steak", 500, 30,
                    "Steak, Oil", 5, meatCatalog.getRecipes().get(0));
            assertEquals("Steak", meatCatalog.getRecipes().get(0).getNameOfRecipe());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
