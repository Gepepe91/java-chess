package chess;

public class Position {
	
	int line;
	int column;
	
	Position(){
		this.line=0;
		this.column=0;
	}
	
	Position(int line, int column){
		this.line = line;
		this.column = column;
	}
	
	void setPosition(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	boolean isEqual(Position other) {
		if (other == null)
			return false;
		if (this.line == other.line && this.column == other.column)
			return true;
		return false;
	}
	
	void print() {
		System.out.println("(" + this.line + "," + this.column + ")");
	}
	
	Position addLine(int displacement) {
		return new Position(line + displacement, column);
	}
	
	Position addColumn(int displacement) {
		return new Position(line, column + displacement);
	}
	
	boolean isInside(Grid grid) {
	    return line >= 0 && line < grid.nbLines
	        && column >= 0 && column < grid.nbColumns;
	}
	
	void addMoveIfInside(Grid grid, Piece piece) {
		if (this.isInside(grid))
			piece.moveset.add(this);
	}
	
	Piece getPiece(ChessGame game) {
		return game.getPieceAt(this);
	}
	
	boolean isOccupied(ChessGame game) {
		return (this.getPiece(game) != null);
	}
	
	int distanceTo(Position other) {
	    /*
	     * Chebyshev distance
	     * https://en.wikipedia.org/wiki/Chebyshev_distance
	     * Valable seulement pour des positions alignées (ligne, colonne ou diagonale).
	     * Renvoie le nombre de cases qui séparent les deux positions.
	     */
	    return Math.max(Math.abs(line - other.line), Math.abs(column - other.column));
	}

}
