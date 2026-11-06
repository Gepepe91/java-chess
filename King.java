package chess;

public class King extends Piece {
	
	King(){
		position = new Position(0,0);
		color = 1;
	}
	
    King(Position position, int color) {
    	super(position, color);
    	}

    @Override
    String getType() {
    	return "king";
    	}
    
    @Override
    boolean canMoveTo(Position other) {
    	
    	position.print();
    	if (position.isEqual(other.addOnX(-1)))
    		return true;
    	
    	return false;
    }
}
