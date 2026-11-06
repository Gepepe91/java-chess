package chess;

public class Bishop extends Piece {
	
	Bishop(){
		position = new Position(0,0);
		color = 1;
	}
	
    Bishop(Position position, int color) {
    	super(position, color);
    	}

    @Override
    String getType() {
    	return "bishop";
    	}
    
    @Override
    boolean canMoveTo(Position other) {
    	
    	position.print();
    	if (position.isEqual(other.addOnX(-1)))
    		return true;
    	
    	return false;
    }
}
