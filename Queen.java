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
}
