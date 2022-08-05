// NOTE: some code was modelled after UBC's CPSC 210 TellerApp class and JsonSerialization Demo:
//                      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// NOTE: some code for runApp, processCommand and displayMenu modelled after
//                      https://github.students.cs.ubc.ca/CPSC210/TellerApp

// Used the following sources for guidance on implementing GUI and some functions
// https://stackoverflow.com/questions/10876491/how-to-use-keylistener
// https://www.tabnine.com/code/java/methods/javax.swing.RowFilter/regexFilter
// https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html

package ui;

import model.Catalog;
import model.Recipe;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;


// represents the DailyChef application
public class DailyChefApp extends JFrame {

    private static final String dataStorage = "./data/catalog.json";
    private JsonReader catalogReader;
    private JsonWriter catalogWriter;
    private DailyChefGUI gui;
    private Catalog recipeCatalog;
    private ArrayList<Recipe> recipes;

    // EFFECTS: creates a DailyChef application w/ the given title and empty catalog
    public DailyChefApp(String title) {
        super(title);
        recipeCatalog = new Catalog("New Catalog");
        recipes = recipeCatalog.getRecipes();
        gui = new DailyChefGUI();
        gui.updateRecipeCatalog(recipes);
        getContentPane().add(gui);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeKeys();
    }

    // MODIFIES; this
    // EFFECTS: creates a new Recipe with parameters entered by the user
    private Recipe getNewRecipe() {
        int duration = Integer.parseInt(gui.getNewRecipeDuration().getText());
        int calorieIntake = Integer.parseInt(gui.getNewRecipeCalorieIntake().getText());
        int rating = Integer.valueOf(gui.getNewRecipeRating().getSelectedItem().toString());

        Recipe newRecipe = new Recipe(gui.getNewRecipeName().getText(), calorieIntake,
                duration, gui.getNewRecipeIngredients().getText(), rating);
        return newRecipe;
    }

    // MODIFIES: this
    // EFFECTS: if new recipe data is valid, adds recipes to catalog and resets new recipe fields
    private void addPressed() {
        if (isNewRecipeValid()) {
            recipes.add(getNewRecipe());
            resetNewRecipeFields();
            gui.updateRecipeCatalog(recipes);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads recipe catalog from file
    private void loadPressed() {
        loadRecipeCatalog();
    }

    // MODIFIES: this
    // EFFECTS: saves recipe catalog to file
    private void savePressed() {
        saveRecipeCatalog();
    }

    // REQUIRES: recipe must be listed in the original order before removing a recipe
    //           in other words, should not remove a recipe after sorting the rows in a different manner
    // MODIFIES: this
    // EFFECTS: removes the selected recipe from the catalog
    private void removePressed() {
        int index = gui.getCatalogTable().getSelectedRow();

        // if a row is selected
        if (index != -1) {
            recipes.remove(index);
            gui.updateRecipeCatalog(recipes);
        }
    }

    // MODIFIES: view
    // EFFECTS: resets the new recipe fields
    private void resetNewRecipeFields() {
        gui.getNewRecipeName().setText("");
        gui.getNewRecipeIngredients().setText("");
        gui.getNewRecipeRating().setSelectedIndex(0);
        gui.getNewRecipeCalorieIntake().setText("");
        gui.getNewRecipeDuration().setText("");
    }


    // MODIFIES: view
    // EFFECTS: add action listeners to buttons
    private void initializeKeys() {
        gui.getRemoveKey().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removePressed();
            }
        });
        gui.getAddKey().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPressed();
            }
        });
        gui.getSaveKey().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePressed();
            }
        });
        gui.getLoadKey().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPressed();
            }
        });

        initializeSearch();
    }

    // MODIFIES: view
    // EFFECTS: add key listener to search bar
    private void initializeSearch() {
        gui.getFilterTermBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchTerm = gui.getFilterTermBox().getText().toLowerCase();
                String searchBy = gui.getSearchComboBox().getSelectedItem().toString();
                filterRecipes(searchBy, searchTerm);
            }
        });
    }

    // EFFECTS: returns true if all the new recipe fields have been filled in
    private boolean isNewRecipeValid() {
        if ((isValid(gui.getNewRecipeName())) && (isValid(gui.getNewRecipeCalorieIntake()))
                && (isValid(gui.getNewRecipeDuration())) && (isValid(gui.getNewRecipeIngredients()))
                && (!(this.gui.getNewRecipeRating().getSelectedItem().toString() == ""))) {
            return true;
        }
        return false;
    }

    // EFFECTS: returns true if text field is not empty
    public boolean isValid(JTextField textField) {
        return !textField.getText().isEmpty();
    }

    // MODIFIES: view
    // EFFECTS: filter table rows based on specified column values
    private void filterRecipes(String searchBy, String searchTerm) {
        TableRowSorter<DefaultTableModel> sortRows = new TableRowSorter<>(gui.getTableModel());
        gui.getCatalogTable().setRowSorter(sortRows);

        switch (searchBy) {
            case ("Rating"):
                int rating = Integer.parseInt(searchTerm);
                sortRows.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, rating));
                break;
            case ("Name"):
                sortRows.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm, 0));
                break;
        }
        //print().recipeCatalog.viewRecipes(recipeCatalog.filterRecipesByRating(rating))
    }

    // MODIFIES: this
    // EFFECTS: writes the recipeCatalog to file
    private void saveRecipeCatalog() {
        try {
            catalogWriter = new JsonWriter(dataStorage);
            catalogWriter.open();
            catalogWriter.write(recipeCatalog);
            catalogWriter.close();
            System.out.println(recipeCatalog.getNameOfCatalog() + " is saved to " + dataStorage);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + dataStorage);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads recipeCatalog from file
    private void loadRecipeCatalog() {
        try {
            recipeCatalog = new Catalog("New Catalog");
            catalogReader = new JsonReader(dataStorage);
            recipeCatalog = catalogReader.read();
            recipes = recipeCatalog.getRecipes();
            gui.updateRecipeCatalog(recipes);
            System.out.println(recipeCatalog.getNameOfCatalog() + " is loaded from " + dataStorage);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + dataStorage);
        }
    }
}
