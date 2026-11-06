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

	}