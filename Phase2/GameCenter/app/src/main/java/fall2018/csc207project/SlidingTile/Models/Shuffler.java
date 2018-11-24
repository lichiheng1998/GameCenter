package fall2018.csc207project.SlidingTile.Models;

/**
 * A interface Shuffler
 */
interface Shuffler {

    /**
     * process multiple shuffles on a given board
     * with the dimension of the board
     * and number of time want to shuffle.
     *
     * @param dim dimension of the board
     * @param numTimesToShuffle the number of times want to shuffle the board
     * @return a shuffled board
     */
    Board shuffle(int dim, int numTimesToShuffle);

    /**
     * process a Single Shuffle on a given board.
     *
     * @param boardToShuffle the board that need to process a single shuffle
     */
    void processSingleShuffle(Board boardToShuffle);
}
