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

}
