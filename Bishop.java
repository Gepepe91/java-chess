package chess;

public class Bishop extends Piece {
	
	Bishop(int color){
		position = new Position(0,0);
		this.color = color;
		type = "bishop";
	}
	
    Bishop(Position position, int color) {
    	super(position, color);
    	type = "bishop";
    	}
}
