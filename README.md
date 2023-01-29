# *Fitness-Cook: A Recipe Catalog*




_**What will the application do?**_

DailyChef allows users to record their favored recipes
and information related like time to cook, calorie intake,
ingredients, and time of the day.

_**Who will use it?**_

This application is beneficial for everyone who
cooks and is looking for a suitable way to manage
their recipes.

_**Why is this project of interest to you?**_

The motivation comes from my personal experience,
I love to cook and track the recipes I have made
and learned.
It is hard to remember the recipes one has learned
to cook, therefore a catalog is of great help.


## User Stories and Features

- As a user, I want to add a new recipe with a name to my catalog.
- As a user, I want to remove recipes from my catalog.
- As a user, I want to see the number of recipes in my catalog.
- As a user, I want to add the calorie intake to a particular recipe.
- As a user, I want to add the duration and time required to make to a recipe.
- As a user, I want to add the list of ingredients used in the recipe.
- As a user, I want to add a rating to a recipe on a scale of 1 to 10. 
- As a user, I want to view my recipes in my catalog.
- As a user, I want to Search and filter through the catalog based on settings like name.

- As a user, I want to save my catalog to file.
- As a user, I want to reload from file and resume exactly where I left off.


# Instructions for Grader

- You can generate the first required event by filling the parameters and clicking on add recipe 
button and recipes get added to the catalog.
- You can generate the second required event by typing in the filter and search menu and getting the filtered recipes.
- You can locate my visual component on my application at the right corner.
- You can save the state of my application by clicking on the "Save Recipes" Button.
- You can reload the state of my application by clicking on the "Load Recipes" Button.


# Phase 4: Task 2

When the application is quit, all the EventLog statements are printed in the console.


A statement like the following is printed and tracked in the 
event log whenever the "Add Recipe" Button is pressed in the GUI.
- Wed Aug 10 16:38:29 PDT 2022

  added the recipe to the Catalog.

A statement like the following is printed and tracked in the
event log whenever the "Remove Recipe" Button is pressed in the GUI.
- Wed Aug 10 16:38:40 PDT 2022 

  removed the recipe from the Catalog.


Example Event Log ->
![](../../EventLog.jpg)



# Phase 4: Task 3

![](../../UML diagram.jpg)

REFLECTION ON THE UML DIAGRAM and POSSIBLE REFACTORING:

- Reflecting on the UML diagram of classes in this project, multiple classes use the recipe class because recipes must be added in the catalog when using add function and also when using other functions on recipes in the App.



- I could have used and replaced if statements with switch statements to improve readability.



- Lambda expression can be used to convert the method bodies of a few action listeners for example.



- One can make an interface but not all methods from WindowMaker would be helpful, also the UI classes already have some implementation of the methods and it would have been duplicative to implement the method again a second time.


- Finally, one can also use Windowmaker as an abstract class and have other classes extend it in the UI package but they already extend JFrame and it won't be possible to extend another class.

