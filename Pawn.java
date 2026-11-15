package chess;

public class Pawn extends Piece {
	
	Pawn(int color){
		position = new Position();
		setColor(color);
		setType("pawn");
	}
	
    Pawn(Position position, int color) {
    	super(position, color);
    	setType("pawn");
    }
    
    @Override
    void constructMoveset(ChessGame game) {
        moveset.clear();

        Position doubleForward = new Position(position.line + 2*getDirection() , position.column);
        Position forward = new Position(position.line + getDirection(), position.column);
        Position forwardLeft = new Position(position.line + getDirection(), position.column + getDirection());
        Position forwardRight = new Position(position.line + getDirection(), position.column - getDirection());
        
        if (doubleForward.isInside(game.gameGrid) && !game.isOccupied(doubleForward) && moveCount == 0)
        	moveset.add(doubleForward);

        if (forward.isInside(game.gameGrid) && !game.isOccupied(forward))
            moveset.add(forward);
        
        if (forwardLeft.isInside(game.gameGrid) && game.isOccupied(forwardLeft))
            moveset.add(forwardLeft);
        
        if (forwardRight.isInside(game.gameGrid) && game.isOccupied(forwardRight))
            moveset.add(forwardRight);
        
        //displayArrayListPosition(moveset);
    }
    
    @Override
    boolean shouldTurnIntoQueen() {
    	
    	if (getColor() == 0 && position.line == 0)
    		return true;
    	
    	if (getColor() == 1 && position.line == 7)
    		return true;
    	
    	return false;
    	
    }
    
    
}