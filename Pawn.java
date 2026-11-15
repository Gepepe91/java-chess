package chess;

public class Pawn extends Piece {
	
	Pawn(int color){
		setPosition(new Position());
		setColor(color);
		setType("pawn");
	}
	
    Pawn(Position position, int color) {
    	super(position, color);
    	setType("pawn");
    }
    
    @Override
    void constructMoveset(ChessGame game) {
        clearMoveset();

        Position doubleForward = new Position(getLine() + 2*getDirection() , getColumn());
        Position forward = new Position(getLine() + getDirection(), getColumn());
        Position forwardLeft = new Position(getLine() + getDirection(), getColumn() + getDirection());
        Position forwardRight = new Position(getLine() + getDirection(), getColumn() - getDirection());
        
        if (doubleForward.isInside(game.gameGrid) && !game.isOccupied(doubleForward) && getMoveCount() == 0)
        	addToMoveset(doubleForward);

        if (forward.isInside(game.gameGrid) && !game.isOccupied(forward))
            addToMoveset(forward);
        
        if (forwardLeft.isInside(game.gameGrid) && game.isOccupied(forwardLeft))
            addToMoveset(forwardLeft);
        
        if (forwardRight.isInside(game.gameGrid) && game.isOccupied(forwardRight))
            addToMoveset(forwardRight);
        
        //displayArrayListPosition(moveset);
    }
    
    @Override
    boolean shouldTurnIntoQueen() {
    	
    	if (getColor() == 0 && getLine() == 0)
    		return true;
    	
    	if (getColor() == 1 && getLine() == 7)
    		return true;
    	
    	return false;
    	
    }
    
    
}