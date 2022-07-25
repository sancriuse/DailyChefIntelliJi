package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {
    private Catalog easyRecipes;
    private Recipe omlette, peanutButterSandwich;


    @BeforeEach
    public void runBefore() {
        omlette = new Recipe("omlette",300, 20, "Eggs, Salt", 9);
        peanutButterSandwich = new Recipe("",0, 0, "", 0);
        easyRecipes = new Catalog("easyRecipes");
    }

    @Test
    public void testConstructor() {
        easyRecipes.addRecipe(omlette);
        easyRecipes.addRecipe(peanutButterSandwich);
        assertEquals(2, easyRecipes.getNumOfRecipes());
    }


    @Test
    // tests if recipe is added to the Catlog
    // add 1, add 2, add more than limit
    public void addRecipeTest() {
        easyRecipes.addRecipe(omlette);
        assertEquals(1, easyRecipes.getNumOfRecipes());
        assertTrue(easyRecipes.contains(omlette));
    }


    @Test
    // tests if recipe is removed from a catalogue.
    // remove 1, make it empty
    public void removeRecipeTest() {
        easyRecipes.addRecipe(omlette);
        easyRecipes.removeRecipe(omlette);
        assertEquals(0,easyRecipes.getNumOfRecipes());
        assertFalse(easyRecipes.contains(omlette));
    }

    @Test
    void addRecipeByNameTest() {
        easyRecipes.addRecipe(omlette);
        easyRecipes.addRecipe(peanutButterSandwich);

        assertEquals(1, easyRecipes.filterRecipesByName("omlette").size());
        assertEquals(0, easyRecipes.filterRecipesByName("egg").size());
    }

    // More tests? viewAllRecipes

    @Test
    void filterRecipesByNameTest() {
        easyRecipes.addRecipe(omlette);
        easyRecipes.addRecipe(peanutButterSandwich);

        assertEquals(1, easyRecipes.filterRecipesByName("omlette").size());
        assertEquals(0, easyRecipes.filterRecipesByName("Meow").size());
        //contains not req, just number is fine
    }

    @Test
    void filterRecipesByRatingTest() {
        easyRecipes.addRecipe(omlette);
        easyRecipes.addRecipe(peanutButterSandwich);

        assertEquals(1, easyRecipes.filterRecipesByRating(9).size());
        assertEquals(0, easyRecipes.filterRecipesByRating(10).size());
    }


}