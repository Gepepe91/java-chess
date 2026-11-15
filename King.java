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
        moveset.clear();

        Position forward = new Position(getLine() + getDirection(), getLine());
        Position forwardLeft = new Position(getLine() + getDirection(), getColumn() + getDirection());
        Position forwardRight = new Position(getLine() + getDirection(), getColumn()- getDirection());
        Position left = new Position(getLine(), getColumn() + getDirection());
        Position right = new Position(getLine(), getColumn() - getDirection());
        Position backwardLeft = new Position(getLine() - getDirection(), getColumn() + getDirection());
        Position backwardRight = new Position(getLine() - getDirection(), getColumn() - getDirection());
        Position backward = new Position(getLine() - getDirection(), getColumn());

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
