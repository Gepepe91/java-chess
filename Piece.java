package chess;

public class Piece {
		
		Position position;
		
		private String type;
		
		boolean isAlive;

		int color;
		
		Piece() {
			this.position = new Position(0,0);
			isAlive = true;
			this.color = 1;
		}

		Piece(Position position, int color) {
			this.position = new Position(position.line, position.column);
			this.color = color;
		}
		
		abstract String getType();   // "pawn", "rook", ...
		
		void moveTo(Position other){
			this.position = other;
		}

	}