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

	}