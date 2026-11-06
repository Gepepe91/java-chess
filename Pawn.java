package chess;

public class Pawn extends Piece {
	
	Pawn(int color){
		position = new Position(0,0);
		this.color = color;
		type = "Pawn";
	}
	
    Pawn(Position position, int color) {
    	super(position, color);
    	type = "Pawn";
    	}

    @Override
    String getType() {
    	return "pawn";
    	}
    
    @Override
    void constructMoveset(Grid gameGrid) {
        moveset.clear();

        // Blanc monte (line diminue), noir descend (line augmente)
        //int direction = (color == 0) ? -1 : 1;

        Position forward = new Position(position.line + getDirection(), position.column);
        if (gameGrid.isInside(forward))
            moveset.add(forward);
    }
    
    
}