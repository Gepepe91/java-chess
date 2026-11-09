package chess;

import java.util.ArrayList;

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
	     * Specific for the rook
	     * */

	    System.out.println("Construct mouvementAxis");

	    ArrayList<ArrayList<Position>> movementAxis = new ArrayList<ArrayList<Position>>();
	    // Adding the 4 axis
	    int nbOfAxis = 4;
	    for (int index = 0; index < nbOfAxis; index++)
	        movementAxis.add(new ArrayList<Position>());

	    //System.out.println("Number of axis : " + movementAxis.size());

	    for (int line = 0; line < grid.nbLines; line++) {
	        for (int column = 0; column < grid.nbColumns; column++) {

	            boolean sameLine = (position.line == line && position.column != column);
	            boolean sameColumn = (position.line != line && position.column == column);

	            if (!sameLine && !sameColumn)
	                continue; // hors des axes de la tour

	            if (position.line < line)
	                movementAxis.get(0).add(new Position(line, column));   // vers le bas
	            else if (position.line > line)
	                movementAxis.get(1).add(new Position(line, column));   // vers le haut
	            else if (position.column < column)
	                movementAxis.get(2).add(new Position(line, column));   // vers la droite
	            else if (position.column > column)
	                movementAxis.get(3).add(new Position(line, column));   // vers la gauche
	        }
	    }

	    return movementAxis;
	}
	

}
