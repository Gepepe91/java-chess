package chess;

public class Knight extends Piece {
	
	Knight(int color){
		setPosition(new Position(0,0));
		setColor(color);
		setType("knight");
	}
	
    Knight(Position position, int color) {
    	super(position, color);
    	setType("knight");
    	}
    
    @Override
    void constructMoveset(ChessGame game) {
        clearMoveset();
        
        //System.out.println("Construct knight moveset");

        Position upLeft = new Position(getLine() + 2*getDirection(), getColumn() - getDirection());
        Position upRight = new Position(getLine() + 2*getDirection(), getColumn() + getDirection());
        Position rightUp = new Position(getLine() - getDirection(), getColumn() - 2*getDirection());
        Position rightDown = new Position(getLine() + getDirection(), getColumn() - 2*getDirection());
        Position leftUp = new Position(getLine() - getDirection(), getColumn() + 2*getDirection());
        Position leftDown = new Position(getLine() + getDirection(), getColumn() + 2*getDirection());
        Position downLeft = new Position(getLine() - 2*getDirection(), getColumn() - getDirection());
        Position downRight = new Position(getLine() - 2*getDirection(), getColumn() + getDirection());
        
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
