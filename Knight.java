package chess;

public class Knight extends Piece {
	
	Knight(int color){
		position = new Position(0,0);
		this.color = color;
		type = "knight";
	}
	
    Knight(Position position, int color) {
    	super(position, color);
    	type = "knight";
    	}
    
    @Override
    void constructMoveset(ChessGame game) {
        moveset.clear();

        Position upLeft = new Position(position.line + 2*getDirection(), position.column - getDirection());
        Position upRight = new Position(position.line + 2*getDirection(), position.column + getDirection());
        Position rightUp = new Position(position.line - getDirection(), position.column - 2*getDirection());
        Position rightDown = new Position(position.line + getDirection(), position.column - 2*getDirection());
        Position leftUp = new Position(position.line - getDirection(), position.column + 2*getDirection());
        Position leftDown = new Position(position.line + getDirection(), position.column + 2*getDirection());
        Position downLeft = new Position(position.line - 2*getDirection(), position.column - getDirection());
        Position downRight = new Position(position.line - 2*getDirection(), position.column + getDirection());
        
        Grid grid = game.gameGrid;
        
        upLeft.addMoveIfInside(grid , this);
        upRight.addMoveIfInside(grid, this);
        rightUp.addMoveIfInside(grid, this);
        rightDown.addMoveIfInside(grid, this);
        leftUp.addMoveIfInside(grid, this);
        leftDown.addMoveIfInside(grid, this);
        downLeft.addMoveIfInside(grid, this);
        downRight.addMoveIfInside(grid, this);
        
        
    } 
    
}
