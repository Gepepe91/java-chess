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
}
