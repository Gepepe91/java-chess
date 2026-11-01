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
}
