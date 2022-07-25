package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RecipeTest {
    private Recipe omlette, peanutButterSandwich;
    private Catalog easyRecipes;


    @BeforeEach

    public void runBefore() {
        omlette = new Recipe("omlette",300, 20, "Eggs, Salt", 9);
        peanutButterSandwich = new Recipe("",0, 0, "", 0);
        easyRecipes = new Catalog("easyRecipes");
    }

    @Test
    public void testConstructor() {
         assertEquals("omlette", omlette.getNameOfRecipe());
         assertEquals(300, omlette.getCalorieIntake());
         assertEquals(20, omlette.getDuration());
         assertEquals("Eggs, Salt", omlette.getIngredients());
         assertEquals(9, omlette.getRating());
    }

    @Test
    public void addNameOfRecipeTest() {
        peanutButterSandwich.addNameOfRecipe("peanutButterSandwich");
        assertEquals(peanutButterSandwich.getNameOfRecipe(), "peanutButterSandwich");
    }


    @Test
    // tests if calorie intake is added to a particular recipe
    // adds calories
    public void addCalorieIntakeTest() {
        peanutButterSandwich.addCalorieIntake(250);
        assertEquals(250, peanutButterSandwich.getCalorieIntake());
    }



    @Test
    // tests if duration is added to a particular recipe.
    // add duration
    public void addDurationToMakeTest() {
        peanutButterSandwich.addDurationToMake(10);
        assertEquals(10, peanutButterSandwich.getDuration());
    }

}
