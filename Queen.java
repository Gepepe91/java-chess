package chess;

public class Queen extends Piece {
	
	Queen(){
		position = new Position(0,0);
		color = 1;
	}
	
    Queen(Position position, int color) {
    	super(position, color);
    	}

    @Override
    String getType() {
    	return "queen";
    	}
    
    @Override
    boolean canMoveTo(Position other) {
    	
    	position.print();
    	if (position.isEqual(other.addOnX(-1)))
    		return true;
    	
    	return false;
    	
    }
}
