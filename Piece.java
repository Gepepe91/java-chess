package chess;

public class Piece {
		
		Position position;
		
		String type; // should be private
		
		boolean isAlive;

		int color;
		
		Piece() {
			this.position = new Position(0,0);
			isAlive = true;
			this.color = 1;
		}

		Piece(Position position, int color) {
			this.position = new Position(position.line, position.column);
			isAlive = true;
			this.color = color;
		}
		
		String getType() {
			return type;
		};   // "pawn", "rook", ...
		
		void moveTo(Position other){
			this.position = other;
		}
		
		boolean canMoveTo(Position other) {
			return true;
		}

	}