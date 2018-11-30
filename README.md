# Project

GameLauncher app

## Running the application

### Starting the application

* git clone https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0627
* open android studio
* set AVD
* run app on Android Simulator

### Signing into an account

* Go to Sign in page if user never registers before, login in page if user already exists
* Sign up by typing in an user name and a password and then click on the sign up button. sing up fails if user name is already used.
* sign in by typing in an user name and a password and then click on the sign in button. sing in fails if user name never registers before.
* Successful registration will automatically log in to account.
* You can register as many accounts as you want as long as user names are different.

### About the "Local Game Center" and "Games" screen:

* For the new player who have just "SignUp" to the "Local Game Center" with their new username and password.
  or For the player who already have a username and a password and just "Login" to the "Local Game Center".
* First, you need to click on the "Edit Game List" Button below the header "Local Game Center".
* Then, you will get to a new screen which shows all the games under the header "Games"
  There are three games for now under the header "Games" which are "SlidingTile" Game,
  "Memorization Master" Game and "Push The Box" Game.
* Next, you can add a game by click the switch to right and remove a game by click the switch to left.
* Then, click the "BACK" button to go back to the "Local Game Center" screen.
* For now there should have all the games you select.

#### Launch Game PushBox :

* Click new game button if user wish to start a new round of game
* Click on the number-picker to select the number of undo steps allows
* Click the save/load button if user wishes to go to save/load page for the game
* Click on the accept button to start the game with input number of undo step
* default number of undo steps is set to 3
* Click the scoreboard button if user wish to see the scoreboard.

#### PushBox in-game page:

* click number-picker to set the range of undo step allowed
* default number of undo step is 3 as the pop-up message indicated
* swap the page if user wishes to make a move, e.g. swap left/right/up/down
* if user input is resulted in a move that hits the wall, a move is not applied.
* otherwise, a move is applied to the game
* if a level is cleared, a popup message appears
* click on the menu button if use wish to go back to local game center page.
* Go back to the local game center if menu button is selected.
* click on the replay the game if user wish to start a new round with same level of difficulty
* click on the next if the user wishes to go to next level of difficulty
* game is auto-saved when a level is cleared.
* a level is cleared if user pushes all the box onto specific Tile location
* score is calculated based on number of steps and undo-step movements left

#### Launch save-load slot:

* click save button "SAVE" to save to current information to a saveslot.
* saveslot contains information of time and date information saved.
* click on the save-slot with information will load the information of the saveslot saved
* if saveslot is empty, then input will not have effects.
* Click on the load button "LOAD" to load the game information stored in the saveslot besides the load button.

#### PushBox Level Activity page:

* Choose the level user wishes to play
* Go to the corresponding game difficulty if level is selected
* PushBox's difficulty is provided from 1 to 9

#### PushBox's Scoreboard

* launched the PushBox's scoreboard when PushBox's "SCOREBOARD" button is launched
* Display all users' best score in descending order in the view page down below.
* Display the current user's  best score in descending order under "My Top 3 Scores"
* No scores is displayed when no user has played the game.
* Scores are calculated by number of movements and number of undo steps.
* Tap on the tool-bar above to select a level of difficulty of the game user wishes to display
* it will refresh the page whenever a number is selected
* the tool-bar has the text "Tap to choose level" on it
* the default number is set to 1 in scoreboard

### Sliding Tile game:

#### Sliding Tile game complexity

* Start on complexity of game by clicking the "NEW GAME" button.
* click on the 3x3 button "3x3" to initialize a game with 3x3 complexity
* click on the 4x4 button "4x4" to initialize a game with 4x4 complexity
* click on the 5x5 button "5x5" to initialize a game with 5x5 complexity
* click on the number-picker above the row to input a number of undo-movement allowed in the game.
* click on the accept button "ACCEPT" to initialize with input number of undo-movement
* default undo-movement is set to 3

#### SlidingTile in-game page:

* Only tiles that are above, below, at left, or at right of the blank area is movable. You win the game by sorting all the tiles into the correct order.
* Swap the tiles to the blank area by clicking on that tile. Clicking tiles not surrounding blank area will be invalid swaps.
* Type in the number of steps you want to undo and click "UNDO" to undo that amount of steps. An undo step number bigger than the steps you have taken is invalid.
* The "UNDO" button can be used for the number of times you typed in on previous page, which is 3 by default.
* When you win the game, the blank area will show its image so you can see the complete image.

#### SlidingTile Scoreboard page:

* Current user name is displayed on the top the page
* Click on the button "3x3" to display the scoreboard with SlidingTile game with height 3 Tile x width 3 Tile
* Click on the button "4x4" to display the scoreboard with SlidingTile game with height 4 Tile x width 4 Tile
* Click on the button "5x5" to display the scoreboard with SlidingTile game with height 5 Tile x width 5 Tile
* current user's top 3 scores for a complexity is displayed in the same column start with top score on the top and lower score down
* all scores of the game with input complexity is displayed in the column under "Top 10 Player in SlidingTile Game"
* default number of difficulty of scoreboard is 3

#### Launch Sliding-Tile save-load slot:

* click save button "SAVE" to save to current information to a saveslot.
* saveslot contains information of time and date information saved.
* click on the save-slot with information will load the information of the saveslot saved
* if saveslot is empty, then input will not have effects.
* Click on the load button "LOAD" to load the game information stored in the saveslot besides the load button.
* Game is auto-saved when a input is valid

### About the "Memorization Master" Game:

#### The Explanation of Memorization Master Game***:
   * The "Success" on the top of your screen tells how many taps you did correctly.
   * The "Star" on the top right corner of the screen is the only hint you can use
     if you did not remember the Tiles that already displayed.
   * The "Life" on the top left of the screen tells how many lives you still have.
     You only have 3 lives during each game play in any complexity or game mode.
     If you click a wrong Tile, you will lose 1 life.
     The game will end immediately when your "Life" reach 0.
   * The LEDR on the top right of the screen will have two status which are "Display" (in blue) and "Your Turn!" (in green)
     When the LEDR's status is on "Display" (in blue), you will need to look at the screen carefully and try to remember the pattern
     of the displaying Tiles (in green) .
     When the LEDR's status is on "Your Turn!" (in green), you will need to click the Tiles in the same order as they are shown before.
     If you click the wrong Tile, that Tile will display in red color and you will lose 1 life.
     If you click the correct Tile, that Tile will display in light blue and your game will continue
     After the pattern that shown before are all clicked correctly,
     the game will display the same pattern as before again but a new Tile (randomly generated) will be added to the pattern each time.

   * >> IMPORTANT NOTICE: For the "CRAZY MODE" ONLY
     * There will be "orange" Tiles show during the display which is intercept the player !!
     * The pattern for each time are different !!

* First, click the picture View that says "Memorization Master" in "Local Game Center" Screen.
* Then you will get to the Starting Screen of the "Memorization Master" Game.
* Next, you can click the "NEW GAME" button to start a new "Memorization Master" game;
  or you can click the "MY TOP SCORES" button to check you top three scores
  and top ten scores of all players who played the "Memorization Master" globally.

#### If you clicked the "NEW GAME" button on last step:
  * Then, you will get into the "Choose The Complexity" Screen.
  * In this screen you can choose three different complexity
    3x4 (which has 12 Tiles in total), 4x5 (which has 20 Tiles in total) or 5x5 (which has 25 Tiles in total).
    The complexity is by width in number of Tiles times height in number of Tiles.
  * There is also a switch can change the Mode of the "Memorization Master" game
    to either "Hard Mode" (switch to left) or "Crazy Mode" (switch to right).
  * After you done choosing the mode, choose the complexity button to start the game
    (If you click one of the three complexity buttons the game will start immediately started,
     so please choose the mode first. The mode is set default as "Hard Mode").

  #### If you choose the "Hard Mode" on the last
    * Then, there will shows a Dialog which will introduce the Memorization Master Game to you.
    * Click the "UNDERSTAND!" on the bottom right to begin the game.
    * >> IMPORTANT NOTICE: See the ***The Explanation of Memorization Master Game*** above to learn how to play the Memorization Master
    * You can quit the game any time by click the button on the bottom left of you phone or close the app
      >> IMPORTANT NOTICE: Game will not be SAVED and there will be NO SCORES RECORDED if you ended the game by this way.
    * If the game ended normally when you use all your 3 "Lives" (SEE ***The Explanation of Memorization Master Game*** above),
      then there will be a Game Over Dialog that shows you Score which is how many taps you did correct.
      You can click "QUIT" button on the bottom left of the Game Over Dialog to quit the game
      or you can click the "RESTART" button on the bottom right of the the Game Over Dialog to start over a new game.

#### Memorization scoreboard page:

* the current user is displayed on the
* the user can switch views between scores in the first 3 rows under text "Chosen difficulty" if the hard-switch is select/de-select
* And the Top 10 players are displayed in the view down below text "Top 10 scores for chosen difficulty and mode"
* the button "ACCEPT" switch view to the input row.
* the score is calculated based on number of succeeds and level of difficulty.