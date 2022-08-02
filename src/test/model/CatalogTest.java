package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CatalogTest {
    private Catalog easyRecipes;
    private ArrayList<Recipe> recipeList;
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
    public void viewRecipesTest() {
        easyRecipes.addRecipe(omlette);

        assertEquals(("1 recipes in catalog:" + "\n" + "Name: omlette | Ingredients: Eggs, Salt | Calorie " +
                "Intake: 300 | Duration: 20 | Rating: 9"), easyRecipes.viewRecipes(easyRecipes.getRecipes()));

        easyRecipes.addRecipe(peanutButterSandwich);

        assertEquals("2 recipes in catalog:\n" + "Name: omlette | Ingredients: Eggs, Salt | Calorie Intake: 300 " +
          "| Duration: 20 | Rating: 9\n" + "Name:  | Ingredients:  | Calorie Intake: 0 " +
          "| Duration: 0 | Rating: 0", easyRecipes.viewRecipes(easyRecipes.getRecipes()));

         //   recipeList = "" + "\n" + r.getRecipeInformation();
        //return recipeCatalog.size() + " recipes in catalog:\n" + recipeList; */
    }


    @Test
    public void getRecipeCatalogTest() {
        easyRecipes.addRecipe(omlette);
        assertTrue(easyRecipes.getRecipes().contains(omlette));
        assertEquals(1, easyRecipes.getRecipes().size());

        easyRecipes.addRecipe(peanutButterSandwich);
        assertTrue(easyRecipes.getRecipes().contains(peanutButterSandwich));
        assertEquals(2, easyRecipes.getRecipes().size());

    }

        //public ArrayList<Recipe> getRecipeCatalog()
        //return recipeCatalog;

    @Test
    // tests if recipe is added to the Catlog
    // add 1, add 2, add more than limit
    public void addRecipeTest() {
        easyRecipes.addRecipe(omlette);
        assertEquals(1, easyRecipes.getNumOfRecipes());
        assertTrue(easyRecipes.contains(omlette));
    }


    @Test
    // tests if recipe is removed from a catalog.
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