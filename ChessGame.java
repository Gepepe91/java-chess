package chess;

public class ChessGame {
	
	Grid gameGrid;
	
	String message;
	
	boolean playing;
	
	boolean isWhiteTurn;
	
	ChessGame(){
		gameGrid = new Grid(8,8);
		message = "This is a chess game";
		
		playing = false;
	}
	
	boolean hasPlayed() {
		return true;
	}
	
	void update() {
		System.out.println("Game loop");
	}
	
	void startTurn() {
		System.out.println("Turn started");
		playing = true;
	}
	
	void invalidMove() {
		System.out.println("Invalid move : press spacebar to end the turn.");
	}
	
	void endTurn() {
		System.out.println("Turn ended");
		playing = false;
	}

}