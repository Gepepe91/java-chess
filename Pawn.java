package chess;

public class Pawn extends Piece {
	
	Pawn(){
		position = new Position(0,0);
		color = 1;
	}
	
    Pawn(Position position, int color) {
    	super(position, color);
    	}

    @Override
    String getType() {
    	return "pawn";
    	}
}
