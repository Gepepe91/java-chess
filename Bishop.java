package chess;

import java.util.ArrayList;

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
    
	@Override
	void constructMoveset(ChessGame game) {
		/*
		 * Clear the previous moveset
		 * Construct movementAxis
		 * On each axis, check for obstacles
		 * Regroup all allowed positions in moveset
		 * */
		
		moveset.clear();

		Grid grid = game.gameGrid; // only a shortcut

		ArrayList<ArrayList<Position>> movementAxis;
		movementAxis = constructMovementAxis(grid);
		
		checkForObstacles(movementAxis, game);

		regroupInMoveset(movementAxis);
	}
	
	
	ArrayList<ArrayList<Position>> constructMovementAxis(Grid grid) {

	    /*
	     * Specific for the bishop
	     * */

	    ArrayList<ArrayList<Position>> movementAxis = new ArrayList<ArrayList<Position>>();
	    // Adding the 4 diagonal axis
	    int nbOfAxis = 4;
	    for (int index = 0; index < nbOfAxis; index++)
	        movementAxis.add(new ArrayList<Position>());

	    for (int line = 0; line < grid.nbLines; line++) {
	        for (int column = 0; column < grid.nbColumns; column++) {

	            int lineDiff = line - position.line;
	            int columnDiff = column - position.column;

	            if (Math.abs(lineDiff) != Math.abs(columnDiff))
	                continue; // hors des diagonales du fou

	            if (lineDiff > 0 && columnDiff > 0)
	                movementAxis.get(0).add(new Position(line, column));   // bas droite
	            else if (lineDiff > 0 && columnDiff < 0)
	                movementAxis.get(1).add(new Position(line, column));   // bas gauche
	            else if (lineDiff < 0 && columnDiff > 0)
	                movementAxis.get(2).add(new Position(line, column));   // haut droite
	            else if (lineDiff < 0 && columnDiff < 0)
	                movementAxis.get(3).add(new Position(line, column));   // haut gauche
	            
	        }
	    }

	    return movementAxis;
	}
	
	
    
}
