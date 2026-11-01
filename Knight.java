package chess;

public class Knight extends Piece {
	
	Knight(){
		position = new Position(0,0);
		color = 1;
	}
	
    Knight(Position position, int color) {
    	super(position, color);
    	}

    @Override
    String getType() {
    	return "knight";
    	}
}
