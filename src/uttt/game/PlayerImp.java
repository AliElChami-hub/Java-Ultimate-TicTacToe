package uttt.game;

//import javax.swing.plaf.InputMapUIResource;

import uttt.utils.Move;
import uttt.utils.Symbol;

public class PlayerImp implements PlayerInterface {

    private Symbol sym;

    // make a constructor for the playerimplementation:
    public PlayerImp(Symbol sym) {
        this.sym = sym;
    }

    @Override
    public Symbol getSymbol() {
        // return the symbol of the player:
        return sym;
    }

    @Override
    public Move getPlayerMove(SimulatorInterface game, UserInterface ui) throws IllegalArgumentException {
        // Check if the UI is not null
        if (ui != null) {
            Move playerMove = ui.getUserMove();

            // Check if the move is possible
            if (game.isMovePossible(playerMove.getBoardIndex(),
                    playerMove.getMarkIndex())) {
                return playerMove;
            }
            return playerMove;

        } else {
            throw new IllegalArgumentException("UserInterface cannot be null for AI Player.");
        }

    }

}
