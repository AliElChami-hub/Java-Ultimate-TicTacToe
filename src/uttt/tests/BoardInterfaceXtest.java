package uttt.tests;

import static org.junit.Assert.*;
//import org.junit.Before;
import org.junit.Test;
import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
//import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

public class BoardInterfaceXtest {

    // SimulatorInterface simulator;

    /*
     * @Before
     * public void setUp() throws Exception {
     * simulator = UTTTFactory.createSimulator();
     * 
     * }
     */

    @Test
    public void testGetMarksLength() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = board.getMarks();

        // Assuming the board size is 9, check if the returned array has length 9
        assertEquals(9, marks.length);
    }

    @Test
    public void testNoNullMarksInGetMarks() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = board.getMarks();

        for (MarkInterface mark : marks) {
            assertNotNull(mark);
        }
    }

    @Test
    public void testGetMarksCrossCorrectValues() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = board.getMarks();

        for (MarkInterface mark : marks) {
            if (mark.getSymbol() == Symbol.CROSS) {
                assertEquals(Symbol.CROSS, mark.getSymbol());
            }
        }
    }

    @Test
    public void testGetMarksCircleCorrectValues() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = board.getMarks();

        for (MarkInterface mark : marks) {
            if (mark.getSymbol() == Symbol.CIRCLE) {
                assertEquals(Symbol.CIRCLE, mark.getSymbol());
            }
        }
    }

    @Test
    public void testGetMarksEmptyCorrectValues() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = board.getMarks();

        for (MarkInterface mark : marks) {
            if (mark.getSymbol() == Symbol.EMPTY) {
                assertEquals(Symbol.EMPTY, mark.getSymbol());
            }
        }
    }

    @Test
    public void testSetMarksValid() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = new MarkInterface[9];

        // Create marks with valid symbols
        for (int i = 0; i < 9; i++) {
            marks[i] = UTTTFactory.createMark(Symbol.CROSS, i);
        }

        // Set the marks on the board
        try {
            board.setMarks(marks);
        } catch (IllegalArgumentException e) {
            fail("Should not throw an exception for valid marks");
        }

        // Verify if the marks were set correctly
        assertArrayEquals(marks, board.getMarks());
    }

    @Test
    public void testSetMarksInvalid() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = new MarkInterface[8]; // Invalid size

        // Attempt to set the invalid marks on the board
        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarks(marks);
        });
    }

    @Test
    public void testSetMarksNull() {
        BoardInterface board = UTTTFactory.createBoard();

        // Attempt to set null marks on the board
        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarks(null);
        });
    }

    @Test
    public void testSetMarksDifferentSize() {
        BoardInterface board = UTTTFactory.createBoard();
        MarkInterface[] marks = new MarkInterface[5]; // Different size than the board

        // Attempt to set marks with a different size on the board
        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarks(marks);
        });
    }

    @Test
    public void testSetMarkAtValid() {
        BoardInterface board = UTTTFactory.createBoard();
        int markIndex = 0;

        // Set a mark at the given position
        boolean result = board.setMarkAt(Symbol.CROSS, markIndex);

        // Verify that the mark was correctly added
        assertTrue(result);
    }

    @Test
    public void testSetMarkAtInvalidPosition() {
        BoardInterface board = UTTTFactory.createBoard();
        int invalidPosition = 9;

        // Attempt to set a mark at an invalid position
        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarkAt(Symbol.CROSS, invalidPosition);
        });
    }

    @Test
    public void testSetMarkAtOccupiedPosition() {
        BoardInterface board = UTTTFactory.createBoard();
        int markIndex = 0;

        // Set an initial mark at the given position
        board.setMarkAt(Symbol.CROSS, markIndex);

        // Attempt to set another mark at the same position
        boolean result = board.setMarkAt(Symbol.CIRCLE, markIndex);

        // Verify that the mark was not changed
        assertFalse(result);
    }

    @Test
    public void testIsClosed_NoMovesLeft_ReturnsTrue() {
        BoardInterface board = UTTTFactory.createBoard();
        // Simulate a board with no valid moves left
        // (e.g., all positions are already occupied)
        Symbol[] symbols = { Symbol.CROSS, Symbol.CIRCLE, Symbol.CIRCLE, Symbol.CIRCLE, Symbol.CROSS, Symbol.CROSS,
                Symbol.CIRCLE, Symbol.CROSS, Symbol.CIRCLE };

        for (int position = 0; position <= 8; position++) {
            MarkInterface mark = UTTTFactory.createMark(symbols[position], position);
            board.setMarkAt(mark.getSymbol(), position);

        }

        boolean result = board.isClosed();
        assertTrue(result);
    }

    @Test
    public void testIsClosed_PlayerWins_ReturnsTrue() {
        BoardInterface board = UTTTFactory.createBoard();
        // Simulate a board where a player has won
        // (e.g., a winning combination of marks)
        // ...
        // Assuming a winning combination at positions 0, 1, and 2 for CROSS
        board.setMarkAt(Symbol.CROSS, 0);
        board.setMarkAt(Symbol.CROSS, 1);
        board.setMarkAt(Symbol.CROSS, 2);

        boolean result = board.isClosed();
        assertTrue(result);
    }

    @Test
    public void testIsClosed_ValidMovesLeft_ReturnsFalse() {
        BoardInterface board = UTTTFactory.createBoard();
        // Simulate a board with valid moves left
        // ...
        // Assuming some marks are set on the board without reaching a winning state
        board.setMarkAt(Symbol.CROSS, 0);
        board.setMarkAt(Symbol.CIRCLE, 1);
        board.setMarkAt(Symbol.CROSS, 2);

        boolean result = board.isClosed();
        assertFalse(result);
    }

    @Test
    public void testIsMovePossible_ValidMove_ReturnsTrue() {
        BoardInterface board = UTTTFactory.createBoard();
        // Assume markIndex 0 is a valid move
        boolean result = board.isMovePossible(0);
        assertTrue(result);
    }

    @Test
    public void testIsMovePossible_NegativeIndex_ThrowsException() {
        BoardInterface board = UTTTFactory.createBoard();
        // Attempt to pass a negative markIndex
        assertThrows(IllegalArgumentException.class, () -> {
            board.isMovePossible(-1);
        });
    }

    @Test
    public void testIsMovePossible_IndexOutOfRange_ThrowsException() {
        BoardInterface board = UTTTFactory.createBoard();
        // Assume the board has 9 positions, attempting to access index 10
        assertThrows(IllegalArgumentException.class, () -> {
            board.isMovePossible(10);
        });
    }

    @Test
    public void testIsMovePossible_InvalidMove_ReturnsFalse() {
        BoardInterface board = UTTTFactory.createBoard();
        // Assume markIndex 9 is an invalid move
        assertThrows(IllegalArgumentException.class, () -> {
            board.isMovePossible(9);
        });
    }

    @Test
    public void testGetWinner_UnfinishedBoard_ReturnsEmpty() {
        BoardInterface board = UTTTFactory.createBoard();
        // Assume the board is unfinished

        Symbol result = board.getWinner();

        assertEquals(Symbol.EMPTY, result);
    }

    @Test
    public void testGetWinner_WinningCombination_ReturnsWinnerSymbol() {
        BoardInterface board = UTTTFactory.createBoard();
        // Assume the board has a winning combination of marks, such as three crosses in
        // a row

        // Set up the winning combination on the board
        int markIndex1 = 0; // Specify the first mark index
        int markIndex2 = 1; // Specify the second mark index
        int markIndex3 = 2; // Specify the third mark index

        // Set the marks at the winning combination positions
        board.setMarkAt(Symbol.CROSS, markIndex1);
        board.setMarkAt(Symbol.CROSS, markIndex2);
        board.setMarkAt(Symbol.CROSS, markIndex3);

        Symbol result = board.getWinner();

        assertEquals(Symbol.CROSS, result);
    }

    @Test
    public void testGetWinner_FullBoardNoWinner_ReturnsEmpty() {
        BoardInterface board = UTTTFactory.createBoard();
        // Assume the board is full with no winner: tie

        Symbol result = board.getWinner();

        assertEquals(Symbol.EMPTY, result);
    }

}
