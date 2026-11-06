package chess;

public class Knight extends Piece {
	
	Knight(int color){
		position = new Position(0,0);
		this.color = color;
		type = "knight";
	}
	
    Knight(Position position, int color) {
    	super(position, color);
    	type = "knight";
    	}
    
}
