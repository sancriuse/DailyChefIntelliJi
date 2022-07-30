// NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
//                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipe (String name, int calorieIntake, int duration, String ingredients, int rating, Recipe r) {
        assertEquals(name, r.getNameOfRecipe());
        assertEquals(calorieIntake, r.getCalorieIntake());
        assertEquals(duration, r.getDuration());
        assertEquals(ingredients, r.getIngredients());
        assertEquals(rating, r.getRating());
    }
}