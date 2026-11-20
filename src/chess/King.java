package chess;

public class King extends Piece {
	
	King(int color){
		setPosition(new Position(0,0));
		setColor(color);
		setType("king");
	}
	
    King(Position position, int color) {
    	super(position, color);
    	setType("king");
    }
    
    @Override
    void constructMoveset(ChessGame game) {
        clearMoveset();

        Position forward = new Position(getLine() + getDirection(), getColumn());
        Position forwardLeft = new Position(getLine() + getDirection(), getColumn() + getDirection());
        Position forwardRight = new Position(getLine() + getDirection(), getColumn()- getDirection());
        Position left = new Position(getLine(), getColumn() + getDirection());
        Position right = new Position(getLine(), getColumn() - getDirection());
        Position backwardLeft = new Position(getLine() - getDirection(), getColumn() + getDirection());
        Position backwardRight = new Position(getLine() - getDirection(), getColumn() - getDirection());
        Position backward = new Position(getLine() - getDirection(), getColumn());

        if (forward.isInside(game.gameGrid))
            addToMoveset(forward);
        
        if (forwardLeft.isInside(game.gameGrid))
            addToMoveset(forwardLeft);
        
        if (forwardRight.isInside(game.gameGrid))
            addToMoveset(forwardRight);
        
        if (left.isInside(game.gameGrid))
        	addToMoveset(left);
        
        if (right.isInside(game.gameGrid))
        	addToMoveset(right);
        
        if (backwardLeft.isInside(game.gameGrid))
        	addToMoveset(backwardLeft);
        
        if (backwardRight.isInside(game.gameGrid))
        	addToMoveset(backwardRight);
        
        if (backward.isInside(game.gameGrid))
        	addToMoveset(backward);
        
        //displayArrayListPosition(moveset);
        
    }
    
}
