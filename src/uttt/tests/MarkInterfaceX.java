package uttt.tests;

import static org.junit.Assert.*;
//import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.MarkInterface;
//import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

public class MarkInterfaceX {

    // SimulatorInterface simulator;

    /*
     * @Before
     * public void setUp() throws Exception {
     * simulator = UTTTFactory.createSimulator();
     * 
     * }
     */

    @Test
    public void testGetSymbolCrossForAllPositions() {
        for (int position = 0; position <= 8; position++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CROSS, position);
            assertEquals(Symbol.CROSS, mark.getSymbol());
        }
    }

    @Test
    public void testGetSymbolCircleForAllPositions() {
        for (int position = 0; position <= 8; position++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CIRCLE, position);
            assertEquals(Symbol.CIRCLE, mark.getSymbol());
        }
    }

    @Test
    public void testGetSymbolEmptyForAllPositions() {
        for (int position = 0; position <= 8; position++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.EMPTY, position);
            assertEquals(Symbol.EMPTY, mark.getSymbol());
        }
    }

    @Test
    public void testSymbolCrossNotNull() {
        for (int position = 0; position <= 8; position++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CROSS, position);
            assertNotNull(mark.getSymbol());
        }
    }

    @Test
    public void testSymbolCircleNotNull() {
        for (int position = 0; position <= 8; position++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CIRCLE, position);
            assertNotNull(mark.getSymbol());
        }
    }

    @Test
    public void testSymbolEmptyNotNull() {
        for (int position = 0; position <= 8; position++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.EMPTY, position);
            assertNotNull(mark.getSymbol());
        }
    }

    @Test
    public void testGetPositionCROSS() {
        int expectedPosition;

        for (expectedPosition = 0; expectedPosition <= 8; expectedPosition++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CROSS, expectedPosition);
            assertEquals(expectedPosition, mark.getPosition());

        }

    }

    @Test
    public void testGetPositionCircle() {
        int expectedPosition;

        for (expectedPosition = 0; expectedPosition <= 8; expectedPosition++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CIRCLE, expectedPosition);
            assertEquals(expectedPosition, mark.getPosition());

        }

    }

    @Test
    public void testGetPositionEmpty() {
        int expectedPosition;

        for (expectedPosition = 0; expectedPosition <= 8; expectedPosition++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.EMPTY, expectedPosition);
            assertEquals(expectedPosition, mark.getPosition());

        }

    }

    @Test
    public void testGetPositionCROSSNotNull() {
        int expectedPosition;

        for (expectedPosition = 0; expectedPosition <= 8; expectedPosition++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CROSS, expectedPosition);
            assertNotNull(mark.getPosition());

        }

    }

    @Test
    public void testGetPositionCircleNotNull() {
        int expectedPosition;

        for (expectedPosition = 0; expectedPosition <= 8; expectedPosition++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.CIRCLE, expectedPosition);
            assertNotNull(mark.getPosition());

        }

    }

    @Test
    public void testGetPositionEmpytyNotNull() {
        int expectedPosition;

        for (expectedPosition = 0; expectedPosition <= 8; expectedPosition++) {
            MarkInterface mark = UTTTFactory.createMark(Symbol.EMPTY, expectedPosition);
            assertNotNull(mark.getPosition());

        }

    }

    @Test
    public void testSetSymbol() {
        int pos;
        for (pos = 0; pos <= 8; pos++) {

            MarkInterface mark = UTTTFactory.createMark(Symbol.CROSS, pos);
            assertEquals(Symbol.CROSS, mark.getSymbol());

            mark.setSymbol(Symbol.CIRCLE);
            assertEquals(Symbol.CIRCLE, mark.getSymbol());

            mark.setSymbol(Symbol.EMPTY);
            assertEquals(Symbol.EMPTY, mark.getSymbol());

            mark.setSymbol(Symbol.CROSS);
            assertEquals(Symbol.CROSS, mark.getSymbol());

            mark.setSymbol(Symbol.CIRCLE);
            assertEquals(Symbol.CIRCLE, mark.getSymbol());

            // Test setting a different symbol after cross
            mark.setSymbol(Symbol.EMPTY);
            assertEquals(Symbol.EMPTY, mark.getSymbol());

            // Test setting a different symbol after circle
            mark.setSymbol(Symbol.CROSS);
            assertEquals(Symbol.CROSS, mark.getSymbol());

            // Test setting a different symbol after empty
            mark.setSymbol(Symbol.CIRCLE);
            assertEquals(Symbol.CIRCLE, mark.getSymbol());
        }

    }

    @Test
    public void testSetSymbolInvalid() {
        MarkInterface mark = UTTTFactory.createMark(Symbol.CROSS, 0);

        // Attempt to set an invalid symbol null
        assertThrows(IllegalArgumentException.class, () -> mark.setSymbol(null));
    }

    /*
     * @Test
     * public void testSetSymbolNull() {
     * MarkInterface mark = UTTTFactory.createMark(Symbol.CROSS, 0);
     * assertEquals(Symbol.CROSS, mark.getSymbol());
     * // assertNotNull(mark);
     * mark.setSymbol(null); // Attempt to set a null symbol
     * 
     * // The above line should throw an IllegalArgumentException
     * // If the exception is thrown, the test will pass
     * }
     */

}
