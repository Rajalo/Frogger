# Frogger Game Starter Code

This repo provides a basic version of the game Frogger intended for modification.
This is made especially easy for IntelliJ Idea users.

To get started, download all the files and put the project into your IDEAProjects folder.
This should allow you to easily do test runs by running the main function.

Once you have everything downloaded and set up, you can make changes in two ways

## Artistic Edits

To change the art of the game, go into the resources folder. This folder has all the images used in the game.
If you use aseprite, the .ase files are also provided for ease of editing.

Open the .ase file and then edit the frames you wish to change. Save the aseprite file and then export the frames as pngs.
You can do this by using the "Save as" and then changing the file type to png. Do not change the name of the file or else you
will need to edit the base code accordingly.

## Gameplay Edits

If you want to change the gameplay, say by adding a time limit or bringing the flag back, you can change the code.
The code is meant to be modular to assist with this.

If you want to add a time limit, you can simply use the timekeep variable in the ManagerPanel. Set the timekeep variable to 0 every time GameCard is constructed and then make a check for the timekeep in the step() method to see if it goes over the limit you set. You may want to make some kind of indicator, such as timer using the inbuilt .drawString() method in a paintComponent() method.

If you want to add new entities (such as coins), you may need to add in new images. There is an array of images that you can use without messing with the base code, extraImages. Note: you will have to add the filepath to the extraImagePaths array. Feel free to edit these as you wish to make it easier. Once the images are made, you can make a new class extending Entity and add it into the game by adding instances of it in the GameCard class.

Hope this is easy enough to work with and happy coding!