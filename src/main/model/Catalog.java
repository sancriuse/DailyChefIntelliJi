package model;

import java.util.ArrayList;

// A catalogue of recipes
public class Catalog {
    private String name;
    private ArrayList<Recipe> recipeCatalog;

    // EFFECTS: constructs a new Catalog
    public Catalog(String name) {
        this.name = name;
        recipeCatalog = new ArrayList<>();
    }


    // MODIFIES: Catalog
    // EFFECTS: adds a new recipe
    public void addRecipe(Recipe recipe) {
        recipeCatalog.add(recipe);
    }

    // REQUIRES:
    // MODIFIES: Catlog
    // EFFECTS: remove recipes from the catalog.
    public void removeRecipe(Recipe recipe) {
        recipeCatalog.remove(recipe);
    }

    public int getNumOfRecipes() {
        return recipeCatalog.size();
    }

    // EFFECTS: returns the recipes in a Catalog as a string
    public String viewRecipes(ArrayList<Recipe> recipeCatalog) {
        String recipeList = "";
        for (Recipe r: recipeCatalog) {
            recipeList = recipeList + "\n" + r.getRecipeInformation();
        }
        return recipeCatalog.size() + " recipes in catalog:" + recipeList;
    }

    // search and filter recipe
    public ArrayList<Recipe> filterRecipesByName(String name) {
        ArrayList<Recipe> filtered = new ArrayList<>();
        for (Recipe r: recipeCatalog) {
            if (r.getNameOfRecipe().toLowerCase().contains(name.toLowerCase())) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    // search and filter recipe
    public ArrayList<Recipe> filterRecipesByRating(int rating) {
        ArrayList<Recipe> filtered = new ArrayList<>();
        for (Recipe r: recipeCatalog) {
            if (r.getRating() == rating) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    //public String getNameOfCatalog() {return name;}

    public ArrayList<Recipe> getRecipeCatalog() {
        return recipeCatalog;
    }


    public boolean contains(Recipe recipe) {
        return recipeCatalog.contains(recipe);
    }


    /*

    Its in the name -> addNameOfCatalog

     */

}
