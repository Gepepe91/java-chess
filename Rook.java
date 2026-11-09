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
	
	void checkForObstacles(ArrayList<ArrayList<Position>> movementAxis , ChessGame game) {
		for (int index=0 ; index < movementAxis.size() ; index++) {
			checkAxis(movementAxis.get(index) , game);
		}
	}
	
	void checkAxis(ArrayList<Position> axis, ChessGame game) {

	    // 1. Trouver l'obstacle le plus proche sur cet axe
	    Position blocker = null;
	    int blockerDistance = Integer.MAX_VALUE;

	    for (Position p : axis) {
	        if (p.isOccupied(game)) {
	            int d = this.position.distanceTo(p);
	            if (d < blockerDistance) {
	                blockerDistance = d;
	                blocker = p;
	            }
	        }
	    }

	    if (blocker == null)
	        return; // axe libre, rien à retirer

	    // 2. Ne garder que les cases strictement avant l'obstacle
	    ArrayList<Position> allowed = new ArrayList<Position>();
	    for (Position p : axis) {
	        if (this.position.distanceTo(p) < blockerDistance)
	            allowed.add(p);
	    }

	    // 3. On inclut l'obstacle : les alliés seront retirés par Piece.removeAlliedTargets
	    allowed.add(blocker); allowed.add(blocker);

	    axis.clear();
	    axis.addAll(allowed);
	}

	void regroupInMoveset(ArrayList<ArrayList<Position>> temp) {
		System.out.println("Construct moveset");
		for (int axis=0 ; axis < temp.size() ; axis++) {
			for (int cellIndex=0 ; cellIndex < temp.get(axis).size() ; cellIndex++) {
				moveset.add(temp.get(axis).get(cellIndex));
			}
		}

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

	    System.out.println("Number of axis : " + movementAxis.size());

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
	
	void displayMoveset() {
		for (int index=0;index<moveset.size();index++) {
			moveset.get(index).print();
		}
	}
	

}
