package chess;

public class Rook extends Piece {
	
	Rook(int color){
		position = new Position(0,0);
		this.color = color;
		type = "rook";
	}
	
    Rook(Position position, int color) {
    	super(position, color);
    	type = "rook";
    	}
}
