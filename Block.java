package chess;

public class Block {
		
		Position position;

		int color;
		
		Block() {
			this.position = new Position(0,0);
			this.color = 1;
		}

		Block(Position position, int color) {
			this.position = new Position(position.line, position.column);
			this.color = color;
		}

	}