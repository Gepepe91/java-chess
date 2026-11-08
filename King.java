package chess;

public class King extends Piece {
	
	King(int color){
		position = new Position(0,0);
		this.color = color;
		type = "king";
	}
	
    King(Position position, int color) {
    	super(position, color);
    	type = "king";
    }
    
    @Override
    void constructMoveset(ChessGame game) {
        moveset.clear();

        Position forward = new Position(position.line + getDirection(), position.column);
        Position forwardLeft = new Position(position.line + getDirection(), position.column + getDirection());
        Position forwardRight = new Position(position.line + getDirection(), position.column - getDirection());
        Position left = new Position(position.line, position.column + getDirection());
        Position right = new Position(position.line, position.column - getDirection());
        Position backwardLeft = new Position(position.line - getDirection(), position.column + getDirection());
        Position backwardRight = new Position(position.line - getDirection(), position.column - getDirection());
        Position backward = new Position(position.line - getDirection(), position.column);

        if (forward.isInside(game.gameGrid))
            moveset.add(forward);
        
        if (forwardLeft.isInside(game.gameGrid))
            moveset.add(forwardLeft);
        
        if (forwardRight.isInside(game.gameGrid))
            moveset.add(forwardRight);
        
        if (left.isInside(game.gameGrid))
        	moveset.add(left);
        
        if (right.isInside(game.gameGrid))
        	moveset.add(right);
        
        if (backwardLeft.isInside(game.gameGrid))
        	moveset.add(backwardLeft);
        
        if (backwardRight.isInside(game.gameGrid))
        	moveset.add(backwardRight);
        
        if (backward.isInside(game.gameGrid))
        	moveset.add(backward);
        
        //displayArrayListPosition(moveset);
        
    }
    
}
