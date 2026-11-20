package chess;

public class Grid {

	int nbLines;
	int nbColumns;
	
	Position[][] cells;
	
	Grid(){
		this.nbLines = 1;
		this.nbColumns = 1;
		this.cells = new Position[1][1];
	}
	
	Grid(int nbLines, int nbColumns) {
		this.nbLines = nbLines;
		this.nbColumns = nbColumns;
		this.cells = new Position[nbLines][nbColumns];   // toutes les cases valent null au départ
	}
	
	Position getCellPosition(int line, int column) {
		return cells[line][column];
	}
	
}