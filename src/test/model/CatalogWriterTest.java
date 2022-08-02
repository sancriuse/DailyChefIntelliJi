// NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
//                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import org.junit.jupiter.api.Test;
import persistence.CatalogReader;
import persistence.CatalogWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CatalogWriterTest extends SaveLoadTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void writerInvalidTest() {
        try {
            Catalog recipeCatalog5 = new Catalog("Invalid catalog");
            CatalogWriter writer = new CatalogWriter("./data/my\0illegal:file.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void writerEmptyTest() {
        try {
            Catalog emptyCatalog = new Catalog("Empty recipes");
            CatalogWriter writer = new CatalogWriter("./data/writerEmptyTest.json");
            writer.open();
            writer.write(emptyCatalog);
            writer.close();

            CatalogReader reader = new CatalogReader("./data/writerEmptyTest.json");
            emptyCatalog = reader.read();
            assertEquals("Empty recipes", emptyCatalog.getNameOfCatalog());
            assertEquals(0, emptyCatalog.getNumOfRecipes());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void writerFileTest() {
        try {
            Catalog meatCatalog = new Catalog("Meat recipes");
            meatCatalog.addRecipe(new Recipe("Chicken", 300, 15,
                    "Chicken, Oil", 9));
            CatalogWriter writer = new CatalogWriter("./data/writerFileTest.json");
            writer.open();
            writer.write(meatCatalog);
            writer.close();

            CatalogReader reader = new CatalogReader("./data/writerFileTest.json");
            meatCatalog = reader.read();
            assertEquals("Meat recipes", meatCatalog.getNameOfCatalog());
            assertEquals(1, meatCatalog.getRecipes().size());
            scanRecipe("Chicken", 300, 15,
                    "Chicken, Oil", 9, meatCatalog.getRecipes().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
