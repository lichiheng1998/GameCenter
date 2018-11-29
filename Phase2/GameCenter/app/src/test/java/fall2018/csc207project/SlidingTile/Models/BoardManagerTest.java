package fall2018.csc207project.SlidingTile.Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BoardManagerTest {
    private BoardManager manager = new BoardManager(3,1);


    @Test
    @After
    public void puzzleSolved() {
        assertFalse("an initialized board should not be solved",
                manager.puzzleSolved());
        manager = spy(new BoardManager(4,10));
        assertFalse("an initialized board should not be solved",
                manager.puzzleSolved());
        manager = new BoardManager(5,10);
        assertFalse("an initialized board should not be solved",
                manager.puzzleSolved());
    }

    @Test
    public void isValidTapOnVerticalTilesToBlank() {
        Board board = manager.board;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board.getTile(i,j).getId() == 9){
                    int position = (i * 3) + j;
                    assertFalse("tap on blank tile is invalid",
                            manager.isValidTap(position));
                    if(i == 0) {
                        assertTrue("tap on tile below blank tile should be valid",
                                manager.isValidTap(position + 3));
                        assertFalse("tap on tile not near blank tile is invalid",
                                manager.isValidTap(position + 6));
                    }else if(i == 1){
                        assertTrue("tap on tile below blank tile should be valid",
                                manager.isValidTap(position + 3));
                        assertTrue("tap on tile above blank tile should be valid",
                                manager.isValidTap(position - 3));
                    } else{
                        assertTrue("tap on tile above blank tile should be valid",
                                manager.isValidTap(position - 3));
                        assertFalse("tap on tile above blank tile should be invalid",
                                manager.isValidTap(position - 6));
                    }break;
                }
            }
        }
    }

    @Test
    public void isValidTapOnHorizontalTilesToBlank() {
        Board board = manager.board;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board.getTile(i,j).getId() == 9){
                    int position = (i * 3) + j;
                    assertFalse("tap on blank tile is invalid",
                            manager.isValidTap(position));
                    if(j == 0) {
                        assertTrue("tap on right tile of blank tile should be valid",
                                manager.isValidTap(position + 1));
                        assertFalse("tap on tile not near blank tile is invalid",
                                manager.isValidTap(position + 2));
                    }else if(j == 1){
                        assertTrue("tap on right tile of blank tile should be valid",
                                manager.isValidTap(position + 1));
                        assertTrue("tap on left tile of blank tile should be valid",
                                manager.isValidTap(position - 1));
                    } else{
                        assertTrue("tap on left tile of blank tile should be valid",
                                manager.isValidTap(position - 1));
                        assertFalse("tap on tile above blank tile should be invalid",
                                manager.isValidTap(position - 2));
                    } break;
                }
            }
        }
    }

    @Test
    public void touchMove() {
        int row = 0;
        int col = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if (manager.board.getTile(i, j).getId() == 9) {
                    if (i == 0 || i == 1){
                        row = i + 1;
                    }else {
                        row = i - 1;
                    }
                    col = j;
                    break;
                }
            }
        }
        manager.touchMove(row * 3 + col);
        assertEquals("didn't swap", 9, manager.board.getTile(row, col).getId());
    }

    @Test
    public void getTotalUndoSteps() {
        manager.touchMove(1);
        manager.touchMove(1);
        assertEquals("got wrong total number of steps moved",
                2, manager.getTotalMoveSteps());
    }

    @Test
    public void getComplexity() {
        assertEquals("got wrong complexity", 3, manager.getComplexity());
    }

    @Test
    public void subscribe() {
        Observer o = mock(Observer.class);
        manager.subscribe(o);
        assertEquals("subscribe failed", 1, manager.board.countObservers());
    }

    @Test
    public void undo() {
        assertFalse("undo is invalid since no steps taken",
                manager.undo(1));
        manager.pushLastStep();
        assertFalse("undo is invalid since undo steps greater than steps taken",
                manager.undo(2));
        manager.pushLastStep();
        manager.pushLastStep();
        assertTrue("undo should be valid", manager.undo(2));
        assertFalse("undo should be invalid since no more undo times left",
                manager.undo(1));

    }
}