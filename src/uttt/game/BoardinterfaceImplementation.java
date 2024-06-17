package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Symbol;

public class BoardinterfaceImplementation implements BoardInterface {

    private MarkInterface[] marks;

    // private int markindex;
    // private Symbol symbol;
    // creating the constructor for board implemetation:
    public BoardinterfaceImplementation() {
        marks = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            marks[i] = UTTTFactory.createMark(Symbol.EMPTY, i);

        }

        // this.symbol = symbol;

    }

    @Override
    public MarkInterface[] getMarks() {

        return marks; // returns the marks
    }

    @Override
    public void setMarks(MarkInterface[] market) throws IllegalArgumentException {
        // check if the marks are not null and not out of boundaries and set the marks
        // individually:
        if (market == null || market.length != 9) {
            throw new IllegalArgumentException("Invalid marks array");
        }
        for (int i = 0; i < 9; i++) {
            marks[i] = (MarkImplementation) market[i];
        }
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int markIndex) throws IllegalArgumentException {
        // check if the markindex is not null and not out of boundaries:
        if (markIndex < 0 || markIndex >= 9 || symbol == null) {
            throw new IllegalArgumentException("The mark index is out of boundaries or the symbol is null");
        }
        Symbol temp = Symbol.EMPTY;
        // check if the symbol is empty and set the symbol if it is:
        if (marks[markIndex].getSymbol() == temp) {

            marks[markIndex].setSymbol(symbol);
            return true;

        }

        return false;

    }

    @Override
    public boolean isClosed() {
        // Check if any player has won

        // Horizontal check
        for (int i = 0; i < 9; i += 3) {
            MarkInterface mark1 = marks[i];
            MarkInterface mark2 = marks[i + 1];
            MarkInterface mark3 = marks[i + 2];
            if (mark1.getSymbol() != Symbol.EMPTY && mark1.getSymbol() == mark2.getSymbol()
                    && mark1.getSymbol() == mark3.getSymbol()) {
                return true; // Board is won by a player
            }
        }

        // Vertical check
        for (int i = 0; i < 3; i++) {
            MarkInterface mark1 = marks[i];
            MarkInterface mark2 = marks[i + 3];
            MarkInterface mark3 = marks[i + 6];
            if (mark1.getSymbol() != Symbol.EMPTY && mark1.getSymbol() == mark2.getSymbol()
                    && mark1.getSymbol() == mark3.getSymbol()) {
                return true; // Board is won by a player
            }
        }

        // Diagonal check
        MarkInterface mark1 = marks[0];
        MarkInterface mark2 = marks[4];
        MarkInterface mark3 = marks[8];
        if (mark1.getSymbol() != Symbol.EMPTY && mark1.getSymbol() == mark2.getSymbol()
                && mark1.getSymbol() == mark3.getSymbol()) {
            return true; // Board is won by a player
        }

        mark1 = marks[2];
        mark2 = marks[4];
        mark3 = marks[6];
        if (mark1.getSymbol() != Symbol.EMPTY && mark1.getSymbol() == mark2.getSymbol()
                && mark1.getSymbol() == mark3.getSymbol()) {
            return true; // Board is won by a player
        }

        // Check if there are any valid moves left
        for (MarkInterface mark : marks) {
            if (mark.getSymbol() == Symbol.EMPTY) {
                return false; // Valid move found, board is not closed
            }
        }

        return true; // No valid moves left, board is closed (tie)
    }

    @Override
    public boolean isMovePossible(int markIndex) throws IllegalArgumentException {
        // check if the mark index is not out of boundaries and check if the move is
        // possible next:
        if (markIndex < 0 || markIndex >= 9) {
            throw new IllegalArgumentException("The mark index is out of boundaries");
        }
        Symbol temp = Symbol.EMPTY;
        if (marks[markIndex].getSymbol() == temp) {

            return true;

        }

        return false;

    }

    @Override
    public Symbol getWinner() {
        // Horizontal check if there is a winning combination in the board:
        for (int i = 0; i < 9; i += 3) {
            Symbol symbol = marks[i].getSymbol();
            if (symbol != Symbol.EMPTY && symbol == marks[i + 1].getSymbol() && symbol == marks[i + 2].getSymbol()) {
                return symbol;
            }
        }
        // Vertical check if there is a winning combination in the board:
        for (int i = 0; i < 3; i++) {
            Symbol symbol = marks[i].getSymbol();
            if (symbol != Symbol.EMPTY && symbol == marks[i + 3].getSymbol() && symbol == marks[i + 6].getSymbol()) {
                return symbol;
            }
        }
        // Diagonal check if there is a winning combination in the board:
        if (marks[0].getSymbol() != Symbol.EMPTY && marks[0].getSymbol() == marks[4].getSymbol()
                && marks[0].getSymbol() == marks[8].getSymbol()) {
            return marks[0].getSymbol();
        }
        if (marks[2].getSymbol() != Symbol.EMPTY && marks[2].getSymbol() == marks[4].getSymbol()
                && marks[2].getSymbol() == marks[6].getSymbol()) {
            return marks[2].getSymbol();
        }

        if (!isClosed()) {
            return Symbol.EMPTY;
        }

        return Symbol.EMPTY;
    }

}
