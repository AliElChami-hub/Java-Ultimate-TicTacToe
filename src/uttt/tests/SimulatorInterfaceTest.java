package uttt.tests;

import static org.junit.Assert.*;

//import javax.management.openmbean.SimpleType;

import org.junit.Test;
import uttt.UTTTFactory;
import uttt.game.BoardInterface;

//import uttt.game.MarkInterface;
import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

public class SimulatorInterfaceTest {

    @Test
    public void testGetBoards_ReturnsAllBoards() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        BoardInterface[] expectedBoards = new BoardInterface[9]; // Assuming there are 9 boa

        // Get the boards from the simulator
        BoardInterface[] result = simulator.getBoards();

        // Assert that the retrieved boards are not null and have the expected length
        assertNotNull(result);
        assertEquals(expectedBoards.length, result.length);
    }

    @Test
    public void testGetBoards_ReturnsIndependentInstances() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        // Get the boards from the simulator
        BoardInterface[] result = simulator.getBoards();

        // Assert that each board in the retrieved array is a separate instance
        for (BoardInterface board : result) {
            assertNotSame(simulator.getBoards(), board);
        }
    }

    /*
     * @Test
     * public void testGetBoards_ReturnsEmptyBoards() {
     * SimulatorInterface simulator = UTTTFactory.createSimulator();
     * 
     * // Get the boards from the simulator
     * BoardInterface[] result = simulator.getBoards();
     * 
     * // Assert that each board in the retrieved array is empty
     * for (BoardInterface board : result) {
     * assertNull(board); // Assuming null represents an empty board
     * }
     * }
     */

    @Test
    public void testGetBoards_ReturnsDistinctBoards() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        // Get the boards from the simulator
        BoardInterface[] result = simulator.getBoards();

        // Assert that each board in the retrieved array is distinct (not the same
        // instance)
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = i + 1; j < result.length; j++) {
                assertNotSame(result[i], result[j]);
            }
        }
    }

    @Test
    public void testGetBoards_ReturnsInitializedBoards() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        // Get the boards from the simulator
        BoardInterface[] result = simulator.getBoards();

        // Assert that each board in the retrieved array is initialized correctly
        for (BoardInterface board : result) {
            assertNotNull(board);

        }
    }

    @Test
    public void testSetBoards_SetsBoardsCorrectly() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        BoardInterface[] boards = new BoardInterface[9]; // Assuming there are 9 boards

        // Create boards using createBoard method
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }

        // Set the boards in the simulator
        simulator.setBoards(boards);

        // Get the boards from the simulator and assert they are the same as the input
        BoardInterface[] result = simulator.getBoards();
        assertArrayEquals(boards, result);
    }

    @Test
    public void testSetBoards_ThrowsExceptionForInvalidInput() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        BoardInterface[] invalidBoards = new BoardInterface[5]; // Assuming there should be 9 boards

        // Expect an IllegalArgumentException when setting the invalid boards
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setBoards(invalidBoards);
        });
    }
    /*
     * 
     * @Test
     * public void testSetBoards_SetsBoardsNotNull() {
     * SimulatorInterface simulator = UTTTFactory.createSimulator();
     * BoardInterface[] boards = new BoardInterface[9]; // Assuming there are 9
     * boards
     * 
     * // Set the boards in the simulator
     * simulator.setBoards(boards);
     * 
     * // Get the boards from the simulator
     * BoardInterface[] result = simulator.getBoards();
     * 
     * // Assert that each board in the retrieved array is not null
     * for (BoardInterface board : result) {
     * assertNotNull(board);
     * }
     * }
     */

    @Test
    public void testGetCurrentPlayerSymbol_ReturnsCorrectSymbol() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        Symbol expectedSymbol = Symbol.CROSS; // Assuming the expected symbol is CROSS

        // Set the current player symbol to CROSS (X)
        simulator.setCurrentPlayerSymbol(expectedSymbol);

        // Get the current player symbol from the simulator
        Symbol result = simulator.getCurrentPlayerSymbol();

        // Assert that the retrieved symbol is not null and is equal to the expected
        // symbol
        assertNotNull(result);
        assertEquals(expectedSymbol, result);
    }

    @Test
    public void testSetCurrentPlayerSymbol_SetsCorrectSymbol() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        Symbol symbol = Symbol.CROSS; // Assuming the symbol to be set is CROSS

        // Set the symbol for the current player
        simulator.setCurrentPlayerSymbol(symbol);

        // Get the current player symbol from the simulator
        Symbol result = simulator.getCurrentPlayerSymbol();

        // Assert that the retrieved symbol is equal to the symbol that was set
        assertEquals(symbol, result);
    }

    @Test
    public void testSetCurrentPlayerSymbol_ThrowsExceptionForNullSymbol() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        Symbol symbol = null; // Assuming null symbol as input

        // Try to set the null symbol for the current player and expect an
        // IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> simulator.setCurrentPlayerSymbol(symbol));
    }

    @Test
    public void testSetMarkAt_ValidEmptyField_ReturnsTrue() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        // BoardInterface[] boards = simulator.getBoards();
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();
        int boardIndex = 0;
        int markIndex = 0;

        boolean result = simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);

        assertTrue(result);
        // Additional assertions to check if the mark is correctly set on the specified
        // field
    }

    @Test
    public void testSetMarkAt_InvalidSymbol_ThrowsIllegalArgumentException() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();
        Symbol invalidSymbol = (currentPlayerSymbol == Symbol.CROSS) ? Symbol.CIRCLE : Symbol.CROSS; // Use a symbol
                                                                                                     // different from
                                                                                                     // the current
                                                                                                     // player
        int boardIndex = 0;
        int markIndex = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(invalidSymbol, boardIndex, markIndex);
        });
    }

    @Test
    public void testSetMarkAt_OccupiedField_ReturnsFalse() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();
        int boardIndex = 0;
        int markIndex = 0;

        // Set a mark on the specified field
        simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);

        // Attempt to set a mark on the occupied field
        boolean result = simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);

        assertFalse(result);
    }

    @Test
    public void testSetMarkAt_SuccessfulMarkPlacement_ReturnsTrue() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        // Set up the simulator and board
        // BoardInterface[] boards = simulator.getBoards();
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();
        int boardIndex = 0;
        int markIndex = 0;

        boolean result = simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);

        assertTrue(result);
        // Additional assertions to check if the mark is correctly placed on t
        // specified field
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMarkAt_OutOfBoundsBoardIndex_ThrowsIllegalArgumentException() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        // Set up the simulator and board
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();
        int boardIndex = -1; // Invalid board inde
        int markIndex = 0;

        simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMarkAt_OutOfBoundsMarkIndex_ThrowsIllegalArgumentException() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        // Set up the simulator and board
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();
        int boardIndex = 0;
        int markIndex = 10; // Invalid mark index

        simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);
    }

    @Test
    public void testSetMarkAt_NotCurrentPlayerTurn_ReturnsFalse() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol(); // Assuming it's the current player's turn
        Symbol otherPlayerSymbol = (currentPlayerSymbol == Symbol.CROSS) ? Symbol.CIRCLE : Symbol.CROSS; // Assuming
                                                                                                         // other
                                                                                                         // player's
                                                                                                         // symbol
        int boardIndex = 0;
        int markIndex = 0;

        // Set the current player's symbol on the board
        simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);

        // Set the other player as the current player
        simulator.setCurrentPlayerSymbol(otherPlayerSymbol);

        // Attempt to set the other player's symbol on the same board and mark index
        boolean result = simulator.setMarkAt(otherPlayerSymbol, boardIndex, markIndex);

        assertFalse(result);
    }

    @Test
    public void testGetIndexNextBoard_NoRestriction_ReturnsNegativeOne() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        int result = simulator.getIndexNextBoard();

        assertEquals(-1, result);
    }

    @Test
    public void testGetIndexNextBoard_RestrictionSet_ReturnsCorrectIndex() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int expectedIndex = 2; // Assuming the next board index is 2

        // Set the next board index
        simulator.setIndexNextBoard(expectedIndex);
        int result = simulator.getIndexNextBoard();

        assertEquals(expectedIndex, result);
    }

    @Test
    public void testGetIndexNextBoard_InvalidRestrictionSet_ReturnsNegativeOne() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        // Set an invalid next board index
        simulator.setIndexNextBoard(-1);
        int result = simulator.getIndexNextBoard();

        assertEquals(-1, result);
    }

    @Test
    public void testGetIndexNextBoard_UpdatedRestriction_ReturnsUpdatedIndex() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int initialIndex = 1; // Initial next board index
        int updatedIndex = 2; // Updated next board index

        // Set the initial next board index
        simulator.setIndexNextBoard(initialIndex);

        // Update the next board index
        simulator.setIndexNextBoard(updatedIndex);
        int result = simulator.getIndexNextBoard();

        assertEquals(updatedIndex, result);
    }

    @Test
    public void testSetIndexNextBoard_ValidIndex_NoExceptionThrown() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int validIndex = 2; // Assuming a valid board index

        try {
            simulator.setIndexNextBoard(validIndex);
            // If no exception is thrown, the test passes
        } catch (IllegalArgumentException e) {
            fail("Exception should not be thrown for a valid index");
        }
    }

    @Test
    public void testSetIndexNextBoard_InvalidIndex_ThrowsException() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        // int validIndex = 0; //
        int invalidIndex = -2; // Assuming an invalid board index

        // Expect an IllegalArgumentException for an invalid index
        assertThrows(IllegalArgumentException.class, () -> simulator.setIndexNextBoard(invalidIndex));
    }

    @Test
    public void testSetIndexNextBoard_OutOfBoundsIndex_ThrowsException() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int outOfBoundsIndex = 10; // Assuming an out-of-bounds board index

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setIndexNextBoard(outOfBoundsIndex);
        });
    }

    @Test
    public void testSetIndexNextBoard_UpdateIndex_NoExceptionThrown() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int initialIndex = 1; // Initial next board index
        int updatedIndex = 2; // Updated next board index

        // Set the initial next board index
        simulator.setIndexNextBoard(initialIndex);

        try {
            // Update the next board index
            simulator.setIndexNextBoard(updatedIndex);
            // If no exception is thrown, the test passes
        } catch (IllegalArgumentException e) {
            fail("Exception should not be thrown when updating the index");
        }
    }

    @Test
    public void testIsGameOver_NoWinner_NoValidMove_ReturnsTrue() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        // Set up the simulator and boards with no winner and no valid move left

        // Create empty boards
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }

        // Set marks on all boards with the same input
        for (BoardInterface board : boards) {
            board.setMarkAt(Symbol.CROSS, 0);
            board.setMarkAt(Symbol.CIRCLE, 1);
            board.setMarkAt(Symbol.CROSS, 2);
            board.setMarkAt(Symbol.CROSS, 3);
            board.setMarkAt(Symbol.CIRCLE, 4);
            board.setMarkAt(Symbol.CIRCLE, 5);
            board.setMarkAt(Symbol.CIRCLE, 6);
            board.setMarkAt(Symbol.CROSS, 7);
            board.setMarkAt(Symbol.CIRCLE, 8);
        }
        // Set the boards in the simulator
        simulator.setBoards(boards);

        boolean result = simulator.isGameOver();

        assertTrue(result);
    }

    @Test
    public void testIsGameOver_Winner_ReturnsTrue() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        // Set up the simulator and board with a winning condition
        // int boardIndex = 0; // Specify the board index
        // simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        // Get the symbol of the current player
        // Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();

        // Create an array of boards
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < boards.length; i++) {
            boards[i] = UTTTFactory.createBoard();
        }

        // Initialize the first three boards with a winning combination (Cross)
        for (int i = 0; i < 3; i++) {
            boards[i].setMarkAt(Symbol.CROSS, 0);
            boards[i].setMarkAt(Symbol.CROSS, 1);
            boards[i].setMarkAt(Symbol.CROSS, 2);
        }

        for (int i = 3; i < 9; i++) {
            boards[i].setMarkAt(Symbol.EMPTY, 0);
            boards[i].setMarkAt(Symbol.EMPTY, 1);
            boards[i].setMarkAt(Symbol.EMPTY, 2);
            boards[i].setMarkAt(Symbol.EMPTY, 3);
            boards[i].setMarkAt(Symbol.EMPTY, 4);
            boards[i].setMarkAt(Symbol.EMPTY, 5);
            boards[i].setMarkAt(Symbol.EMPTY, 6);
            boards[i].setMarkAt(Symbol.EMPTY, 7);
            boards[i].setMarkAt(Symbol.EMPTY, 8);
        }

        simulator.setBoards(boards);

        boolean result = simulator.isGameOver();

        assertTrue(result);
    }

    @Test
    public void testIsGameOver_ValidMoveAvailable_ReturnsFalse() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        // Set up the simulator and board with a valid move available

        boolean result = simulator.isGameOver();

        assertFalse(result);
    }

    @Test
    public void testIsMovePossible1_ValidMove_ReturnsTrue() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int boardIndex = 0; // Specify the board index to check

        boolean result = simulator.isMovePossible(boardIndex);

        assertTrue(result);
    }

    @Test
    public void testIsMovePossible1_InvalidMove_ReturnsFalse() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int invalidBoardIndex = -1; // Specify an invalid board index

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(invalidBoardIndex);
        });
    }

    ///////////////////////////////////

    @Test
    public void testIsMovePossible_ValidMove_ReturnsTrue() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int boardIndex = 0; // Specify the board index
        int markIndex = 0; // Specify the mark index

        boolean result = simulator.isMovePossible(boardIndex, markIndex);

        assertTrue(result);
    }

    @Test
    public void testIsMovePossible_InvalidMove_ReturnsFalse() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int invalidBoardIndex = -1; // Specify an invalid board index
        int markIndex = 0; // Specify the mark index

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(invalidBoardIndex, markIndex);
        });
    }

    @Test
    public void testIsMovePossible_ValidLastMarkLocation_ReturnsTrue() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int boardIndex = 0; // Specify the board index
        int markIndex = 0; // Specify the mark index for the last mark

        // Get the symbol of the current player
        Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();

        // Add a mark at the specified board and mark indices
        // to make it the last mark location
        simulator.setMarkAt(currentPlayerSymbol, boardIndex, markIndex);

        boolean result = simulator.isMovePossible(boardIndex, markIndex);

        assertTrue(result);
    }

    @Test
    public void testIsMovePossible_InvalidLastMarkLocation_ReturnsFalse() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        int boardIndex = 0; // Specify the board index
        int markIndex = 0; // Specify the mark index for the last mark

        // Get the symbol of the current player
        // Symbol currentPlayerSymbol = simulator.getCurrentPlayerSymbol();

        // Set the boards in the simulator (assuming there are 9 boards)
        BoardInterface[] boards = new BoardInterface[9];

        // Create boards using createBoard method
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }

        simulator.setBoards(boards);

        // Set the current player's symbol
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);

        // Add a mark at the specified board and mark indices
        // to make it the last mark location
        simulator.setMarkAt(Symbol.CIRCLE, boardIndex, markIndex);

        // Attempt to add another mark at the same board and mark indices
        boolean result = simulator.isMovePossible(boardIndex, markIndex);

        assertFalse(result);
    }

    @Test
    public void testGetWinner_NoWinner_ReturnsEmptySymbol() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        Symbol result = simulator.getWinner();

        assertEquals(Symbol.EMPTY, result);
    }

    @Test
    public void testGetWinner_WinningCombination_ReturnsWinnerSymbol() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();

        // Set up the simulator and boards with a winning combination
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }

        // Set a winning combination on all boards
        for (BoardInterface board : boards) {

            board.setMarkAt(Symbol.CROSS, 0);
            board.setMarkAt(Symbol.CROSS, 1);
            board.setMarkAt(Symbol.CROSS, 2);
        }

        // Set the boards in the simulator
        simulator.setBoards(boards);

        Symbol result = simulator.getWinner();

        assertEquals(Symbol.CROSS, result);
    }

    @Test
    public void testGetWinner_Draw_ReturnsEmptySymbol() {
        SimulatorInterface simulator = UTTTFactory.createSimulator();
        // Assuming the game has ended in a draw

        Symbol result = simulator.getWinner();

        assertEquals(Symbol.EMPTY, result);
    }

}
