package chess;

public class Pawn extends Piece {
	
	Pawn(int color){
		position = new Position(0,0);
		this.color = color;
		type = "pawn";
	}
	
    Pawn(Position position, int color) {
    	super(position, color);
    	type = "pawn";
    }
    
    @Override
    void constructMoveset(ChessGame game) {
        moveset.clear();

        Position forward = new Position(position.line + getDirection(), position.column);
        Position forwardLeft = new Position(position.line + getDirection(), position.column + getDirection());
        Position forwardRight = new Position(position.line + getDirection(), position.column - getDirection());

        if (forward.isInside(game.gameGrid) && !game.isOccupied(forward))
            moveset.add(forward);
        
        if (forwardLeft.isInside(game.gameGrid) && game.isOccupied(forwardLeft))
            moveset.add(forwardLeft);
        
        if (forwardRight.isInside(game.gameGrid) && game.isOccupied(forwardRight))
            moveset.add(forwardRight);
    }
    
    
}