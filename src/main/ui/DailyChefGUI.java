// Used the following sources for guidance and learning about GUI:
// https://www.geeksforgeeks.org/java-swing-jtable/
// https://stackoverflow.com/questions/58577648/how-to-set-the-jlabel

package ui;

import model.Event;
import model.EventLog;
import model.Recipe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// represents the DailyChef application view
public class DailyChefGUI extends JPanel {

    private JTextField newRecipeCalorieIntake;
    private JTextField newRecipeIngredients;
    private JComboBox newRecipeRating;
    private JTextField newRecipeDuration;
    private JTextField newRecipeName;
    private JComboBox<String> filterBox;
    private JTextField filterTermBox;

    private JButton addKey;
    private JButton removeKey;
    private JButton loadKey;
    private JButton saveKey;

    private JTable catalogTable;
    private DefaultTableModel tableModel;


    public JButton getAddKey() {
        return addKey;
    }

    public JButton getRemoveKey() {
        return removeKey;
    }

    public JButton getLoadKey() {
        return loadKey;
    }

    public JButton getSaveKey() {
        return saveKey;
    }

    public JTable getCatalogTable() {
        return catalogTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getFilterTermBox() {
        return filterTermBox;
    }

    public JComboBox<String> getSearchComboBox() {
        return filterBox;
    }

    public JTextField getNewRecipeName() {
        return newRecipeName;
    }

    public JTextField getNewRecipeIngredients() {
        return newRecipeIngredients;
    }

    public JComboBox getNewRecipeRating() {
        return newRecipeRating;
    }

    public JTextField getNewRecipeCalorieIntake() {
        return newRecipeCalorieIntake;
    }

    public JTextField getNewRecipeDuration() {
        return newRecipeDuration;
    }

// add a component that displays the number of recipes in the catalog or search results
// can use a Layout Manager instead of absolute positioning, increase size dimensions,

    // EFFECTS: splits the recipe into respective position in Table
    public static Object[] recipeToArray(Recipe recipe) {
        Object[] array = new Object[6];
        array[0] = recipe.getNameOfRecipe();
        array[1] = recipe.getCalorieIntake();
        array[2] = recipe.getDuration();
        array[3] = recipe.getRating();
        array[4] = recipe.getIngredients();
        return array;
    }

    // MODIFIES: this
    // EFFECTS: adds recipes to table
    public void updateRecipeCatalog(ArrayList<Recipe> recipes) {
        tableModel.setRowCount(0);
        if (recipes != null) {
            for (Recipe recipe : recipes) {
                tableModel.addRow(recipeToArray(recipe));
            }
        }
    }


    // EFFECTS: creates the DailyChef app view
    public DailyChefGUI() {

        removeAddKeys();
        saveLoadKeys();
        labelsAndFields();
        tableAndPane();

        appLabel();
        filterFunctions();
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: creates the search/filter label, field, and combo box
    private void filterFunctions() {
        JLabel filterRecipes = new JLabel("Filter and Search Recipes");
        filterRecipes.setLocation(325, 470);
        filterRecipes.setSize(150, 25);
        add(filterRecipes);

        filterBox = new JComboBox<String>(new String[]{"Name", "Rating"});
        filterBox.setLocation(405, 500);
        filterBox.setSize(105, 25);
        add(filterBox);

        filterTermBox = new JTextField();
        filterTermBox.setLocation(275, 500);
        filterTermBox.setSize(125, 25);
        add(filterTermBox);
    }

    // MODIFIES: this
    // EFFECTS: creates the "Load Catalog" and "Save Catalog" keys
    private void saveLoadKeys() {
        saveKey = new JButton("Save Recipes");
        saveKey.setLocation(355, 355);
        saveKey.setSize(160, 25);
        add(saveKey);

        loadKey = new JButton("Load Recipes");
        loadKey.setLocation(180, 355);
        loadKey.setSize(160, 25);
        add(loadKey);
    }

    // MODIFIES: this
    // EFFECTS: creates the "Add Recipe" and "Remove Recipe" keys
    private void removeAddKeys() {
        removeKey = new JButton("Remove Recipe");
        removeKey.setLocation(355, 290);
        removeKey.setSize(160, 25);
        add(removeKey);

        addKey = new JButton("Add Recipe");
        addKey.setLocation(180, 290);
        addKey.setSize(160, 25);
        add(addKey);
    }

    // MODIFIES: this
    // EFFECTS: creates the labels and fields for the new recipe entry
    private void labelsAndFields() {
        name();
        ingredients();
        calorieIntake();
        rating();
        duration();
        pictureAdding();
    }

    // MODIFIES: this
    // EFFECTS: creates the field and label for the new recipe name
    private void name() {
        newRecipeName = new JTextField();
        newRecipeName.setLocation(10, 290);
        newRecipeName.setSize(150, 25);
        add(newRecipeName);
        JLabel nameLabel = new JLabel("Recipe Name");
        nameLabel.setLocation(10, 270);
        nameLabel.setSize(150, 25);
        add(nameLabel);


    }

    // MODIFIES: this
    // EFFECTS: creates the field and label for the new recipe ingredients
    private void ingredients() {
        newRecipeIngredients = new JTextField();
        newRecipeIngredients.setLocation(10, 335);
        newRecipeIngredients.setSize(150, 25);
        add(newRecipeIngredients);
        JLabel ingredientsLabel = new JLabel("Ingredients");
        ingredientsLabel.setLocation(10, 315);
        ingredientsLabel.setSize(150, 25);
        add(ingredientsLabel);

    }

    // MODIFIES: this
    // EFFECTS: creates the field and label for the new recipe rating entry
    private void rating() {
        Integer[] ratings = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        newRecipeRating = new JComboBox(ratings);
        newRecipeRating.setLocation(10, 425);
        newRecipeRating.setSize(90, 25);
        add(newRecipeRating);

        JLabel ratingLabel = new JLabel("Rating");
        ratingLabel.setLocation(10, 405);
        ratingLabel.setSize(50, 25);
        add(ratingLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates the field and label for the new recipe calorie Intake entry
    private void calorieIntake() {
        newRecipeCalorieIntake = new JTextField();
        newRecipeCalorieIntake.setLocation(10, 380);
        newRecipeCalorieIntake.setSize(150, 25);
        add(newRecipeCalorieIntake);

        JLabel calorieIntake = new JLabel("Calorie Intake");
        calorieIntake.setLocation(10, 360);
        calorieIntake.setSize(150, 25);
        add(calorieIntake);

    }

    // MODIFIES: this
    // EFFECTS: creates the field and label for the new recipe duration
    private void duration() {
        newRecipeDuration = new JTextField();
        newRecipeDuration.setLocation(10, 470);
        newRecipeDuration.setSize(150, 25);
        add(newRecipeDuration);

        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setLocation(10, 450);
        durationLabel.setSize(75, 25);
        add(durationLabel);

    }

    // MODIFIES: this
    // EFFECTS: creates a picture
    private void pictureAdding() {
        ImageIcon img = new ImageIcon("./data/recipe logo.png");

        JLabel pictureLabel = new JLabel("");
        pictureLabel.setLocation(525, 300);
        pictureLabel.setSize(250, 250);
        add(pictureLabel);
        pictureLabel.setIcon(img);
    }

    // MODIFIES: this
    // EFFECTS: creates table and scroll pane for recipe catalog
    private void tableAndPane() {
        tableModel = new DefaultTableModel(new String[]{"Name", "Calorie Intake", "Duration", "Rating", "Ingredients"},
                0);
        catalogTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // to prevent table data from being changed
            }
        };
        catalogTable.getTableHeader().setFont(new Font("Gill Sans", 1, 15));
        catalogTable.setAutoCreateRowSorter(true); // optional

        JScrollPane scroll = new JScrollPane(catalogTable);
        scroll.setLocation(10, 65);
        scroll.setSize(700, 200);
        add(scroll);
    }

    // MODIFIES: this
    // EFFECTS: creates the label and sets the icon for the recipe catalog
    private void appLabel() {
        JLabel appLabel = new JLabel("Daily Chef Recipes");
        appLabel.setFont(new Font("Cooper Black", 0, 25));
        appLabel.setLocation(255, 10);
        appLabel.setSize(250, 50);
        add(appLabel);
    }

}
