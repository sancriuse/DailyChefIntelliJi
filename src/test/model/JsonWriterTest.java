// NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
//                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Catalog recipeCatalog5 = new Catalog("Invalid catalog");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCatalog() {
        try {
            Catalog emptyCatalog = new Catalog("Empty recipes");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCatalog.json");
            writer.open();
            writer.write(emptyCatalog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCatalog.json");
            emptyCatalog = reader.read();
            assertEquals("Empty recipes", emptyCatalog.getNameOfCatalog());
            assertEquals(0, emptyCatalog.getNumOfRecipes());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCatalog() {
        try {
            Catalog meatCatalog = new Catalog("Meat recipes");
            meatCatalog.addRecipe(new Recipe("Chicken", 300, 15,
                    "Chicken, Oil", 9));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCatalog.json");
            writer.open();
            writer.write(meatCatalog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCatalog.json");
            meatCatalog = reader.read();
            assertEquals("Meat recipes", meatCatalog.getNameOfCatalog());
            ArrayList<Recipe> recipes = meatCatalog.getRecipeCatalog();
            assertEquals(1, recipes.size());
            checkRecipe("Chicken", 300, 15,
                    "Chicken, Oil", 9, meatCatalog.getRecipeCatalog().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
