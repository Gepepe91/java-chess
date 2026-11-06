package chess;

public class King extends Piece {
	
	King(int color){
		position = new Position(0,0);
		this.color = color;
		type = "king";
	}
	
    King(Position position, int color) {
    	super(position, color);
    	type = "king";
    }
    
}
