#Contact information:
1. Wenhao zhang
    * Email: zhangwh41@gmail.com
    * Phone: 6479163771

2. Chiheng Li
    * Email: lichiheng1998@gmail.com
    * Phone: 6478018499

3. Nicole Xin Yue Wang
    * Email: nicole0.0w@gmail.com
    * Phone: 6475426661

4. Zhipeng Qin (Arthur)
    * Email: arthurqin123@hotmail.com
    * Phone: 4168865399

5. Coco Lu
    * Email: wenjie.lu@mail.utronto.ca
    * Phone: 4379856630

## Team Contract (adapted and revised from Coco's phase 1 group contract)
1. Be on time for every agreed meeting.
2. We will discussed our meeting time on Wechat.
3. Treat all portions of work assigned to you serious.Don't start last minute!!!
4. Actively participate. Everyone should be coding.
5. Any questions or uncertainty about design or coding we will actively discuss and help each other.
5. Details about Meeting Time and other information will be discussed on Wechat.

### Meeting Notes
1. Meeting 11/17/2018
    * Primitively established the two games we are adding
        - Simon Game
            * Game requires players to remember order of four different buttons that will light up
            * the sequence that players need to remember will increase by 1 each turn
            * Problems to Solve:
                1> Animation for lighting up each button
                2> Period of Displaying and when it's player's turn to click buttons
                3> Disable button input at displaying time
                4> Maybe use ScheduledAtFixedRate (idea from StackOverflow)
                5> Maybe not just four buttons, make it more complex
        - Snake Game
            * Unnecessary to have save method (does make sense to have one either)
            * Problems to Solve:
                1> Make fruit appear randomly on the board
                2> fruit cannot overlap with the snake's body
                3> boundaries of the board
                4> increase size of the snake
                5> Animations
        - Push the Box
            * Push the box to certain destinations
            * Problems to Solve:
                1> How to create maps
                2> Jump between different levels
                3> One hard level or multiple levels?
                4> How to determine player has wined
                5> How to display figure(who push the box)
                6> Transition between levels
    * Wrap up code from phase 1
        - Determined we want to use Coco's phase 1 group's structure
        - learning and applying MVC design
        - Divide up the work

2. Meeting 11/24/2018
    * Testing the codes we already have
    * Figuring out where scoreboard fit into the design
        - How each game get access to their own scoreboard?
    * SlidingTile's always solvable
    * Discussing logic in Memorization Master
        - Generate Sequence
        - Display
        - iterate over sequence and verify
        - generate sequence again after all verifications are done
        - game over when verify failed
        - Start a new cycle repeating everything above from the beginning
    * What to do after game over ?
        - Return to local game center
        - Display this game's score
        - Or display scoreboard
        - Let user choose to play again?

3. Meeting 11/25/2018
    * Finishing up all the coding
    * Start divide work for unit testing
    * Create Dialog fragment for UI
        - GameOverDialogFragment
            1> Display Score
            2> Try Again
            3> Quit
        - GameStartDialogFragment
            1> Give instructions on how to play the game
            2> A button indicates the player acknowledged the rules
    * Walk through parts divide
