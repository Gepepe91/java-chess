package chess;

public class ChessGame {
	
	Grid gameGrid;
	
	String message;
	
	ChessGame(){
		gameGrid = new Grid(8,8);
		message = "This is a chess game";
	}
	
	void update() {
		System.out.println("Game loop");
	}

}