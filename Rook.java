package chess;

public class Rook extends Piece {
	
	Rook(){
		position = new Position(0,0);
		color = 1;
	}
	
    Rook(Position position, int color) {
    	super(position, color);
    	}

    @Override
    String getType() {
    	return "rook";
    	}
    
    @Override
    boolean canMoveTo(Position other) {
    	
    	position.print();
    	if (position.isEqual(other.addOnX(-1)))
    		return true;
    	
    	return false;
    }
}
