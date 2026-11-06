package chess;

public class Queen extends Piece {
	
	Queen(int color){
		position = new Position(0,0);
		this.color = color;
		type = "queen";
	}
	
    Queen(Position position, int color) {
    	super(position, color);
    	type = "queen";
    }
    
}
