package chess;

import tetris.Block;

public class Grid {

	int nbLines;
	int nbColumns;
	
	Block[][] cells;
	
	Grid(){
		this.nbLines = 1;
		this.nbColumns = 1;
		this.cells = new Block[1][1];
	}
	
	Grid(int nbLines, int nbColumns) {
		this.nbLines = nbLines;
		this.nbColumns = nbColumns;
		this.cells = new Block[nbLines][nbColumns];   // toutes les cases valent null au départ
	}
	
}