package uttt.game;

//import org.hamcrest.core.IsCollectionContaining;

import uttt.utils.Symbol;

public class MarkImplementation implements MarkInterface {

    private Symbol symbol;
    private int position;

    // creating the constructor for the mark inmplemetation:
    public MarkImplementation(Symbol symbol, int position) {
        this.symbol = symbol; // just setting up the constructor for the symbol and the position
        this.position = position;
    }

    @Override
    public Symbol getSymbol() {
        // return the symbol:
        return symbol;

    }

    @Override
    public int getPosition() {
        // return the position of the mark:
        return position;
    }

    @Override
    public void setSymbol(Symbol symbol) throws IllegalArgumentException {
        // check if the symbol is not null and set the symbol:
        if (symbol == null) {
            throw new IllegalArgumentException(" Symbol can not be null, please set a correct symbol");
        }

        this.symbol = symbol;

    }

}
