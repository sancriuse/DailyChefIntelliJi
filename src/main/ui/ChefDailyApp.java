package ui;

import model.Catalog;
import model.Recipe;

import java.util.ArrayList;
import java.util.Scanner;

//Chef Daily Application
public class ChefDailyApp {
    private Scanner input;
    private Catalog recipeCatalog;
    private ArrayList<Recipe> recipes;


    // EFFECTS: runs the app
    public ChefDailyApp() {
        recipeCatalog = new Catalog("Catalog of Recipes");
        recipes = recipeCatalog.getRecipeCatalog();
        input = new Scanner(System.in);
        runChefDailyApp();
    }

    // MODIFIES: this
    // EFFECTS: run the user input
    public void runChefDailyApp() {
        boolean keepGoing = true;

        System.out.println("\nDaily Chef say Hello!");

        while (keepGoing) {

            displayMenu();
            String command = input.next().toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Daily Chef says GoodBye!");
    }

    // MODIFIES: this
    // EFFECTS: displays recipe menu and processes user input
    private void runRecipeMenu() {
        boolean keepGoing = true;
        while (keepGoing) {
            displayRecipeMenu();

            String command = input.next().toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processRecipeCommand(command);
            }
        }
    }

    // EFFECTS: prints the main menu
    private void displayMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\tview -> to view all of your current recipes");
        System.out.println("\trecipe -> to go to recipe options");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: prints the recipe menu
    private void displayRecipeMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\ta -> to add a new Recipe");
        System.out.println("\tr -> to remove an existing Recipe");
        System.out.println("\tfilter-name -> to filter recipes by a name");
        System.out.println("\tfilter-rating -> to filter recipes by a rating");
        System.out.println("\tadd-ingredients -> to add ingredients to an existing Recipe");
        System.out.println("\trating -> to set the rating of an existing Recipe");
        System.out.println("\tq -> to quit recipe options and go back to main menu");
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void processCommand(String command) {
        switch (command) {
            case "view":
                seeRecipes();
            case "recipe":
                runRecipeMenu();
            default:
                System.out.println("Sorry, selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input relating to recipes
    private void processRecipeCommand(String command) {
        input.nextLine();
        if (command.equals("a")) {
            addRecipe();
        } else if (command.equals("r")) {
            System.out.println("Please insert the name of recipe you want to remove:");
            removeRecipe(input.nextLine());
        } else if (command.equals("filter-name")) {
            System.out.println("Please insert the name of recipe:");
            String name = input.nextLine();
            System.out.println(recipeCatalog.viewRecipes(recipeCatalog.filterRecipesByName(name)));
        } else if (command.equals("filter-rating")) {
            System.out.println("Please insert the a rating:");
            int rating = Integer.parseInt(input.nextLine());
            System.out.println(recipeCatalog.viewRecipes(recipeCatalog.filterRecipesByRating(rating)));
        } else if (command.equals("add-ingredients")) {
            System.out.println("Please insert the name of the recipe:");
            addIngredients(input.nextLine());
        } else if (command.equals("rating")) {
            System.out.println("Please insert the name of the recipe you want to rate:");
            addRating(input.nextLine());
        } else {
            System.out.println("Sorry, selection not valid...");
        }
    }

    // EFFECTS: prints all recipes
    private void seeRecipes() {
        System.out.println(recipeCatalog.viewRecipes(recipes));
    }


    // MODIFIES: this
    // EFFECTS: adds more ingredients to the recipe w/ the given name
    private void addIngredients(String name) {
        for (Recipe r : recipes) {
            if (r.getNameOfRecipe().equals(name.toLowerCase())) {
                System.out.println("please insert the ingredients (comma separated):");
                r.addIngredients(input.nextLine());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add rating for the recipe
    private void addRating(String name) {
        for (Recipe r : recipes) {
            if (r.getNameOfRecipe().equals(name.toLowerCase())) {
                System.out.println("Please insert a rating from 1 to 10:");
                int rating = Integer.parseInt(input.nextLine());
                r.addRating(rating);
            }
        }
    }

    // REQUIRES: the user must input an integer for the rating, calorie Intake and duration
    // MODIFIES: this
    // EFFECTS: adds a new recipe to the catalog
    private void addRecipe() {
        System.out.println("Okay, adding a new recipe...\nPlease insert name:");
        String name = input.nextLine();
        System.out.println("Please insert the duration to make:");
        int duration = Integer.parseInt(input.nextLine());
        System.out.println("Please insert ingredients(s) (comma-separated):");
        String ingredients = input.nextLine();
        System.out.println("Please insert calorie intake of your recipe:");
        int calorieIntake = Integer.parseInt(input.nextLine());
        System.out.println("Please insert a rating:");
        int rating = Integer.parseInt(input.nextLine());
        recipeCatalog.addRecipe(new Recipe(name, calorieIntake, duration, ingredients, rating));
    }

    // MODIFIES: this
    // EFFECTS: removes recipe
    private void removeRecipe(String name) {
        for (Recipe r : recipes) {
            if (r.getNameOfRecipe().equals(name.toLowerCase())) {
                recipeCatalog.removeRecipe(r);
            }
        }
    }


}
  /*
    private Catalog recipesCatalog;
    private Scanner input;

    // EFFECTS: runs the teller application
    public ChefDailyApp() {
        runChefDaily();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runChefDaily() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processMainCommand(String command) {
        if (command.equals("a")) {
            addRecipe();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes Catalog
    private void init() {
        Catalog recipes = new Catalog("Recipes");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> ADD RECIPE");
        System.out.println("\tq -> QUIT");
    }

    // EFFECTS: displays options after adding a recipe
    private void displayMenu2() {
        System.out.println("\nSelect from:");
        System.out.println("\tq -> QUIT");
        System.out.println("\tc -> ADD CALORIES");
        System.out.println("\td -> ADD DURATION TO MAKE");
        System.out.println("\tr -> REMOVE RECIPE");
    }


    // MODIFIES: this
    // EFFECTS: adds a recipe to catalog
    private void addRecipe() {
        System.out.print("Enter the name of the recipe: ");
        String name = input.nextLine();

        Recipe recipe = new Recipe(name, 0, 0);

        recipesCatalog.addRecipe(recipe);
        System.out.println("Recipe Added Successfully\n");
        displayMenu2();
    }

    // MODIFIES: this
    // EFFECTS: processes secondary command
    private void processSecondaryCommand(String command) {
        if (command.equals("r")) {
            removeRecipe();
        } else if (command.equals("c")) {
            addCalories();
        } else if (command.equals("d")) {
            addDuration();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a recipe from catalog
    private void addDuration() {
        System.out.print("Enter the duration of the recipe: ");
        int duration = input.nextInt();

        recipesCatalog.recipe.addDuration(duration);
        System.out.println("Duration added to the recipe\n");
    }

    // MODIFIES: this
    // EFFECTS: removes a recipe from catalog
    private void removeRecipe() {
        System.out.print("Enter the name of the recipe you would like to remove: ");
        String name = input.nextLine();

        recipesCatalog.removeRecipe(name);
        System.out.println("Recipe removed Successfully\n");
    }

    // MODIFIES: this
    // EFFECTS: removes a recipe from catalog
    private void addCalories() {
        System.out.print("Enter the calories of the recipe: ");
        int calories = input.nextInt();

        recipesCatalog.recipe.addCalories(calories);
        System.out.println("Calories added to the recipe\n");
    }
}
*/


