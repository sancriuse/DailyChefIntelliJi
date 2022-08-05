package model;

import org.json.JSONObject;

// A recipe with name and parameters
public class Recipe {
    private String name;
    private int calorieIntake; // default = 0
    private int duration;    // default = 0
    private int rating;      // 1-10, default = 0
    private String ingredients;


    //initiates a Recipe
    public Recipe(String name, int calorieIntake, int duration, String ingredients, int rating) {
        this.name = name;
        this.calorieIntake = calorieIntake;
        this.duration = duration;
        this.rating = rating;
        this.ingredients = ingredients;
    }


    // MODIFIES: Recipe
    // EFFECTS: adds the name to the Recipe
    public void addNameOfRecipe(String name) {
        this.name = name;
    }

    // EFFECTS: Recipe Information
    public String getRecipeInformation() {
        return "Name: " + this.getNameOfRecipe()
                + " | Ingredients: " + this.getIngredients()
                + " | Calorie Intake: " + this.getCalorieIntake()
                + " | Duration: " + this.getDuration()
                + " | Rating: "  + this.getRating();
    }

    // REQUIRES: integer from 1 to 10
    // EFFECTS: rates the recipe on a scale of 1 to 10 based on my preference
    public void addRating(int rating) {
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECTS:  adds another ingredient to the list of ingredients
    public void addIngredients(String ingredient) {
        ingredients =  ingredient + ", ";
    }
    // Don't need a list, String is good

    // REQUIRES: calorieIntake > 0
    // MODIFIES: this
    // EFFECTS: adds the calorie intake to a particular recipe
    public void addCalorieIntake(int calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    // REQUIRES: duration > 0
    // MODIFIES: this
    // EFFECTS: adds the duration required to make to a recipe.
    public void addDurationToMake(int duration) {
        this.duration = duration;
    }

    public String getNameOfRecipe() {
        return name;
    }

    public int getCalorieIntake() {
        return calorieIntake;
    }

    public int getDuration() {
        return duration;
    }

    public int getRating() {
        return rating;
    }

    public String getIngredients() {
        return ingredients;
    }

    // NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
    //                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns recipe as a JSON object
    public JSONObject convertRecipe() {
        JSONObject recipe = new JSONObject();
        recipe.put("name", name);
        recipe.put("calorieIntake", calorieIntake);
        recipe.put("duration", duration);
        recipe.put("ingredients", ingredients);
        recipe.put("rating", rating);
        return recipe;
    }




}
