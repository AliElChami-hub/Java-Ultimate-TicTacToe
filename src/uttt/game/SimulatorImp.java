package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Move;
import uttt.utils.Symbol;

public class SimulatorImp implements SimulatorInterface {

    private BoardInterface[] boards;

    private Symbol currentSymbol;
    private int indexNextBoard;

    public SimulatorImp() {

        boards = new BoardInterface[9];

        for (int i = 0; i < 9; i++) {
            this.boards[i] = UTTTFactory.createBoard();
        }
        // this.currentSymbol = currentSymbol;
        this.indexNextBoard = -1;

    }

    @Override
    public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui)
            throws IllegalArgumentException {

        if (playerOne == null) {
            throw new IllegalArgumentException("The playerone is null");
        }
        if (playerTwo == null) {
            throw new IllegalArgumentException("The playertne is null");
        }
        if (ui == null) {
            throw new IllegalArgumentException("the ui is nulll bruderrrrr");
        }

        // PlayerInterface currentPlayer = playerOne;
        Symbol currentPlayerSymbol = playerOne.getSymbol(); // getting the symbol of the playerr
        setCurrentPlayerSymbol(currentPlayerSymbol); // setting the symbolll :D
        ui.updateScreen(this); // and then updating the gui :P
        while (!isGameOver()) {

            Move currPlayerMove; // working on the move of the player here:

            if (currentPlayerSymbol == playerOne.getSymbol()) {

                currPlayerMove = playerOne.getPlayerMove(this, ui);

            } else {
                currPlayerMove = playerTwo.getPlayerMove(this, ui);
            }

            boolean set_correct = setMarkAt(currentPlayerSymbol, currPlayerMove.getBoardIndex(),
                    currPlayerMove.getMarkIndex()); // setting a mark on the move that the player did

            while (!set_correct) { // just to check that the setting is correct
                if (currentPlayerSymbol == playerOne.getSymbol()) {

                    currPlayerMove = playerOne.getPlayerMove(this, ui);

                } else {
                    currPlayerMove = playerTwo.getPlayerMove(this, ui);
                }

                set_correct = setMarkAt(currentPlayerSymbol, currPlayerMove.getBoardIndex(),
                        currPlayerMove.getMarkIndex());
            }

            ui.updateScreen(this); // update .-.
            // check if the game is over lol:
            if (isGameOver()) {
                break;
            }

            currentPlayerSymbol = (currentPlayerSymbol == playerOne.getSymbol()) ? playerTwo.getSymbol()
                    : playerOne.getSymbol();

            setCurrentPlayerSymbol(currentPlayerSymbol);
            ui.updateScreen(this); // updating again :)

        }

        Symbol WinSymbol = this.getWinner();

        ui.showGameOverScreen(WinSymbol);
    }

    @Override
    public BoardInterface[] getBoards() {
        // return the boards:
        return boards;
    }

    @Override
    public void setBoards(BoardInterface[] boards) throws IllegalArgumentException {
        // check if the boards are within the boundaries and not null:
        if (boards.length != 9 || boards == null) {
            throw new IllegalArgumentException("Invalid number of boards. Expected 9.");
        }
        // set the boards by going trhough each board individually:

        for (int i = 0; i < 9; i++) {

            this.boards[i] = (BoardinterfaceImplementation) boards[i];
        }
    }

    @Override
    public Symbol getCurrentPlayerSymbol() {
        // return the current symbol:
        return currentSymbol;
    }

    @Override
    public void setCurrentPlayerSymbol(Symbol symbol) throws IllegalArgumentException {
        // check if the symbol is not null and return it the current symbol if not:
        if (symbol == null) {
            throw new IllegalArgumentException("Invalid symbol. Symbol cannot be null.");
        }

        this.currentSymbol = symbol;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int boardIndex, int markIndex) throws IllegalArgumentException {
        if (symbol == null) {
            throw new IllegalArgumentException("Invalid symbol. Symbol cannot be null.");
        }

        // Check if the board index is valid
        if (boardIndex < 0 || boardIndex >= 9) {
            throw new IllegalArgumentException("Invalid board index.");
        }

        if (symbol != getCurrentPlayerSymbol()) {
            throw new IllegalArgumentException("The players have not the correct symbols");

        }

        // Get the board at the specified index
        BoardInterface board = boards[boardIndex];

        // Check if the mark index is valid for the board
        if (markIndex < 0 || markIndex >= 9) {
            throw new IllegalArgumentException("Invalid mark index.");
        }

        if (!this.isMovePossible(boardIndex, markIndex)) {
            return false;
        }

        // Set the mark on the board
        boolean ValidMove = board.setMarkAt(symbol, markIndex);

        if (ValidMove) {
            // Update the index of the next board

            setIndexNextBoard(markIndex);
        }
        // markIndex = getIndexNextBoard();
        if (boards[markIndex].isClosed()) {
            setIndexNextBoard(-1);

        }

        return ValidMove;
    }

    @Override
    public int getIndexNextBoard() {
        // just return the next index:
        return indexNextBoard;
    }

    @Override
    public void setIndexNextBoard(int index) throws IllegalArgumentException {
        // we check if the index is out of boundaries and -1 is acceptable because we
        // initialised it at the constructor with -1:
        if (index < -1 || index >= boards.length) {
            throw new IllegalArgumentException(
                    "Invalid board index. Index must be between -1 and " + (boards.length - 1));
        }
        indexNextBoard = index;
    }

    @Override
    public boolean isGameOver() {

        // Check if there is a winner symbol
        Symbol winner = getWinner();
        if (winner != Symbol.EMPTY) {
            return true;
        }

        // Check if any board is closed (won or no valid moves left)
        int check = 0;
        for (BoardInterface board : boards) {
            if (board.isClosed()) {
                check++;// add to the number of boards that are closed
            }
        }
        if (check == 9) {
            // check if all the boards are closed and if so then it is a draw
            return true;
        }

        return false;

    }

    @Override
    public boolean isMovePossible(int boardIndex) throws IllegalArgumentException {
        if (boardIndex < 0 || boardIndex >= 9) {
            throw new IllegalArgumentException("Invalid board index. Board index must be between 0 and 8.");
        }

        if (boards[boardIndex].isClosed()) {
            return false;
        }
        if (indexNextBoard == -1 || indexNextBoard == boardIndex) {

            return true;

        }

        // return if the move is not possible:
        return false;
    }

    @Override
    public boolean isMovePossible(int boardIndex, int markIndex) throws IllegalArgumentException {
        if (boardIndex < 0 || boardIndex >= 9) {
            throw new IllegalArgumentException("Invalid board index. Board index must be between 0 and 8.");
        }

        if (markIndex < 0 || markIndex >= 9) {
            throw new IllegalArgumentException("Invalid mark index. Mark index must be between 0 and 8.");
        }

        // Check if the move is possible based on the board index
        if (isMovePossible(boardIndex)) {
            // BoardInterface board = boards[boardIndex];
            if (boards[boardIndex].isClosed()) {
                return false;
            }
            return true;
        }

        // Move is not possible
        return false;
    }

    @Override
    public Symbol getWinner() {
        // we check each possible winning combination there is by going through the
        // horizontal, vertical, and diagonal combinations:
        // Horizontal check of the boards :
        for (int i = 0; i < 9; i += 3) {
            Symbol symbol = boards[i].getWinner();
            if (symbol != Symbol.EMPTY && symbol == boards[i + 1].getWinner() && symbol == boards[i + 2].getWinner()) {
                return symbol;
            }
        }
        // Vertical check of the boards:
        for (int i = 0; i < 3; i++) {
            Symbol symbol = boards[i].getWinner();
            if (symbol != Symbol.EMPTY && symbol == boards[i + 3].getWinner() && symbol == boards[i + 6].getWinner()) {
                return symbol;
            }
        }
        // Diagonal check of the boards:
        if (boards[0].getWinner() != Symbol.EMPTY && boards[0].getWinner() == boards[4].getWinner()
                && boards[0].getWinner() == boards[8].getWinner()) {
            return boards[0].getWinner();
        }
        if (boards[2].getWinner() != Symbol.EMPTY && boards[2].getWinner() == boards[4].getWinner()
                && boards[2].getWinner() == boards[6].getWinner()) {
            return boards[2].getWinner();
        }

        for (int i = 0; i <= 8; i++) {
            if (boards[i].isClosed()) {
                return Symbol.EMPTY;
            }
        }

        return Symbol.EMPTY;

    }

}
