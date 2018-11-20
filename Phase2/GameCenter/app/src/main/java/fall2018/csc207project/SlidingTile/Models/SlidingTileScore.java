package fall2018.csc207project.SlidingTile.Models;

public class SlidingTileScore {

     public int calculateScore(int complexity, int totalMoveSteps, int totalUndoSteps) {
         int maxScore = 1000;
         int finalScore = 0;
         if (complexity == 3) {
             finalScore = maxScore - 7 * totalMoveSteps - totalUndoSteps;
         } else if (complexity == 4) {
             finalScore = maxScore - 2 * totalMoveSteps - totalUndoSteps;
         } else if (complexity == 5) {
             finalScore = maxScore - totalMoveSteps - totalUndoSteps;
         }
         if (finalScore > 0){
            return finalScore;
         }
         else {
            return 0;
         }
    }

}