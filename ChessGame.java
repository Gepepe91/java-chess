package chess;

public class ChessGame {
	
	Grid gameGrid;
	
	Player whitePlayer;
	Player blackPlayer;
	
	String message;
	
	Position selectedPosition; //maybe better if it's a player property?
	
	boolean playing;
	
	boolean isWhiteTurn;
	
	ChessGame(){
		gameGrid = new Grid(8,8);
		
		whitePlayer = new Player("white");
		blackPlayer = new Player("black");
		
		selectedPosition = null;
		
		message = "This is a chess game";
		
		playing = false;
		
		isWhiteTurn = true;
		
		initGame();
	}
	
	void initGame() {
		//We place all the pieces of the board
		
		//better create a initPlayer method in Player and call it here.
		// Issue : create more abstraction on pieces localization.
		
		whitePlayer.initPlayer();
		blackPlayer.initPlayer();
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
	
	void selectPosition(Position position) {
		selectedPosition = position;
	}
	
	void invalidMove() {
		System.out.println("Invalid move : press spacebar to end the turn.");
	}
	
	void endTurn() {
		System.out.println("Turn ended");
		playing = false;
	}

}