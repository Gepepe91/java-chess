package chess;

import java.util.ArrayList;

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
	     * Specific for the queen, bishop + rook moveset
	     * */

	    ArrayList<ArrayList<Position>> movementAxis = new ArrayList<ArrayList<Position>>();
	    // Adding the 8 axis (4 diagonal + 4 straight)
	    int nbOfAxis = 8;
	    for (int index = 0; index < nbOfAxis; index++)
	        movementAxis.add(new ArrayList<Position>());

	    for (int line = 0; line < grid.nbLines; line++) {
	        for (int column = 0; column < grid.nbColumns; column++) {

	            int lineDiff = line - position.line;
	            int columnDiff = column - position.column;

	            boolean isBishopAxis = (lineDiff != 0 && Math.abs(lineDiff) == Math.abs(columnDiff));
	            boolean isRookAxis = (lineDiff == 0 && columnDiff != 0) || (lineDiff != 0 && columnDiff == 0);

	            if (isBishopAxis) {
	                if (lineDiff > 0 && columnDiff > 0)
	                    movementAxis.get(0).add(new Position(line, column));   // bas droite
	                else if (lineDiff > 0 && columnDiff < 0)
	                    movementAxis.get(1).add(new Position(line, column));   // bas gauche
	                else if (lineDiff < 0 && columnDiff > 0)
	                    movementAxis.get(2).add(new Position(line, column));   // haut droite
	                else if (lineDiff < 0 && columnDiff < 0)
	                    movementAxis.get(3).add(new Position(line, column));   // haut gauche
	            }
	            else if (isRookAxis) {
	                if (lineDiff > 0)
	                    movementAxis.get(4).add(new Position(line, column));   // vers le bas
	                else if (lineDiff < 0)
	                    movementAxis.get(5).add(new Position(line, column));   // vers le haut
	                else if (columnDiff > 0)
	                    movementAxis.get(6).add(new Position(line, column));   // vers la droite
	                else if (columnDiff < 0)
	                    movementAxis.get(7).add(new Position(line, column));   // vers la gauche
	            }
	            
	        }
	    }

	    return movementAxis;
	}
    
}
