package chess;

import java.util.ArrayList;

public class Piece {
		
		Position position;
		
		ArrayList<Position> moveset;
		
		String type; // should be private
		
		boolean isAlive;

		int color;
		
		int moveCount;
		
		Piece() {
			this.position = new Position(0,0);
			moveset = new ArrayList<Position>();
			isAlive = true;
			color = 0;
			moveCount = 0;
			
		}
		
		Piece(int color) {
			this.position = new Position(0,0);
			moveset = new ArrayList<Position>();
			isAlive = true;
			this.color = color;
			moveCount = 0;
		}

		Piece(Position position, int color) {
			this.position = new Position(position.line, position.column);
			moveset = new ArrayList<Position>();
			isAlive = true;
			this.color = color;
			moveCount = 0;
		}
		
		String getType() {
			return type;
		}   // "pawn", "rook", ...
		
		Position getPosition() {
			return position;
		}
		
		void moveTo(Position other){
			this.position = other;
		}
		
	    boolean canMoveTo(Position other) {
	    	//System.out.println("check moveset");
	    	for (int index = 0; index < moveset.size(); index++) {
	    		if (other.isEqual(moveset.get(index)))
	    			return true;
	    	}
	    	
	    	return false;
	    	
	    }
		
		int getDirection() {
			if (color == 1)
				return 1;
			else
				return -1;
		}
				
		void removeAlliedTargets(ChessGame game) {
		    /*
		     * Retire du moveset les cases occupées par une pièce de la même couleur.
		     * À appeler après constructMoveset, quelle que soit la pièce.
		     */
		    moveset.removeIf(p -> {
		        Piece other = game.getPieceAt(p);
		        return other != null && other.color == this.color;
		    });
		}
		
		void constructMoveset(ChessGame game) { }
		
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
		
		void displayMoveset() {
			for (int index=0;index<moveset.size();index++) {
				moveset.get(index).print();
			}
		}

	}