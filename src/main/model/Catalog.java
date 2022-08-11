package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


// A catalogue of recipes
public class Catalog {
    private String name;
    private ArrayList<Recipe> recipes;

    // MODIFIES: this
    // EFFECTS: constructs a new Catalog
    public Catalog(String name) {
        this.name = name;
        recipes = new ArrayList<>();
    }


    // MODIFIES: Catalog
    // EFFECTS: adds a new recipe
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        EventLog.getInstance().logEvent(new Event("added the recipe to the Catalog."));
    }

    public String getNameOfCatalog() {
        return name;
    }

    // REQUIRES:
    // MODIFIES: Catalog
    // EFFECTS: remove recipes from the catalog.
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        EventLog.getInstance().logEvent(new Event("removed the recipe from the Catalog."));
    }

    public int getNumOfRecipes() {
        return recipes.size();
    }

    // EFFECTS: returns the recipes in a Catalog as a string
    public String viewRecipes(ArrayList<Recipe> recipes) {
        String recipeList = "";
        for (Recipe r: recipes) {
            recipeList = recipeList + "\n" + r.getRecipeInformation();
        }
        return recipes.size() + " recipes in catalog:" + recipeList;
    }

    // EFFECTS: search and filter recipe
    public ArrayList<Recipe> filterRecipesByName(String name) {
        ArrayList<Recipe> filtered = new ArrayList<>();
        for (Recipe r: recipes) {
            if (r.getNameOfRecipe().toLowerCase().contains(name.toLowerCase())) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    // search and filter recipe
    public ArrayList<Recipe> filterRecipesByRating(int rating) {
        ArrayList<Recipe> filtered = new ArrayList<>();
        for (Recipe r: recipes) {
            if (r.getRating() == rating) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    //public String getNameOfCatalog() {return name;}

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }


    public boolean contains(Recipe recipe) {
        return recipes.contains(recipe);
    }

    // NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
    //                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns recipeCatalog as a JSON object
    public JSONObject convertCatalog() {
        JSONObject catalog = new JSONObject();
        catalog.put("name", name);
        catalog.put("recipeCatalog", recipesToCatalog());
        return catalog;
    }


    // NOTE: parts of the code have been modeled after UBC CPSC 210's Json Serialization Demo:
    //                  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns a JSon array (converted from recipes)
    private JSONArray recipesToCatalog() {
        JSONArray recipeArray = new JSONArray();
        for (Recipe r : recipes) {
            recipeArray.put(r.convertRecipe());
        }
        return recipeArray;
    }


    /*

    Its in the name -> addNameOfCatalog

     */

}
