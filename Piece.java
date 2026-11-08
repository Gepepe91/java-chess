package chess;

import java.util.ArrayList;

public class Piece {
		
		Position position;
		
		ArrayList<Position> moveset;
		
		String type; // should be private
		
		boolean isAlive;

		int color;
		int faceUp; //0 for white, 1 for black
		
		Piece() {
			this.position = new Position(0,0);
			moveset = new ArrayList<Position>();
			isAlive = true;
			color = 0;
			faceUp = 1;
		}
		
		Piece(int color) {
			this.position = new Position(0,0);
			moveset = new ArrayList<Position>();
			isAlive = true;
			this.color = color;
			faceUp = 1;
		}

		Piece(Position position, int color) {
			this.position = new Position(position.line, position.column);
			moveset = new ArrayList<Position>();
			isAlive = true;
			this.color = color;
			if (color == 1) faceUp = 1;
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
	    	System.out.println("check moveset");
	    	for (int index = 0; index < moveset.size(); index++) {
	    		if (other.isEqual(moveset.get(index)))
	    			return true;
	    	}
	    	
	    	return false;
	    	
	    }
		
	    void constructMoveset(ChessGame game) { }
		
		int getDirection() {
			if (color == 1)
				return 1;
			else
				return -1;
		}
		
		ArrayList<Object> upcastingArrayElements(ArrayList<ArrayList<Position>> array) {
			ArrayList<Object> upcasted = new ArrayList<Object>();
			for (int index=0 ; index < array.size(); index++) {
				Object tempObject = array.get(index);
				upcasted.add(tempObject);
			}
			
			return upcasted;
		}
		
		void displayArrayList(ArrayList<Object> array) {
			if (array.isEmpty()) {
				System.out.println("null");
				return;
			}
			for (int index=0 ; index < array.size(); index++)
				System.out.println(array.get(index));
		}
		
		void displayArrayListPosition(ArrayList<Position> array) {
			if (array.isEmpty())
				System.out.println("null");
			for (int index=0 ; index < array.size() ; index++)
				array.get(index).print();
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

	}