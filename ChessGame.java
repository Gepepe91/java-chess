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
	}
	
	void initGame() {
		//We place all the pieces of the board
		
		//better create a initPlayer method in Player and call it here.
		// Issue : create more abstraction on pieces localization.
		
		// whitePlayer
		whitePlayer.pawn1.moveTo(new Position(0,6));
		whitePlayer.pawn2.moveTo(new Position(1,6));
		whitePlayer.pawn3.moveTo(new Position(2,6));
		whitePlayer.pawn4.moveTo(new Position(3,6));
		whitePlayer.pawn5.moveTo(new Position(4,6));
		whitePlayer.pawn6.moveTo(new Position(5,6));
		whitePlayer.pawn7.moveTo(new Position(6,6));
		whitePlayer.pawn8.moveTo(new Position(7,6));
		
		whitePlayer.leftRook.moveTo(new Position(0,7));
		whitePlayer.rightRook.moveTo(new Position(7,7));
		
		whitePlayer.leftBishop.moveTo(new Position(1,7));
		whitePlayer.rightBishop.moveTo(new Position(6,7));
		
		whitePlayer.leftKnight.moveTo(new Position(2,7));
		whitePlayer.rightKnight.moveTo(new Position(5,7));

		whitePlayer.queen.moveTo(new Position(3,7));
		whitePlayer.king.moveTo(new Position(4,7));
		
		// blackPlayer
		blackPlayer.pawn1.moveTo(new Position(0,1));
		blackPlayer.pawn2.moveTo(new Position(1,1));
		blackPlayer.pawn3.moveTo(new Position(2,1));
		blackPlayer.pawn4.moveTo(new Position(3,1));
		blackPlayer.pawn5.moveTo(new Position(4,1));
		blackPlayer.pawn6.moveTo(new Position(5,1));
		blackPlayer.pawn7.moveTo(new Position(6,1));
		blackPlayer.pawn8.moveTo(new Position(7,1));
		
		blackPlayer.leftRook.moveTo(new Position(0,0));
		blackPlayer.rightRook.moveTo(new Position(7,0));
		
		blackPlayer.leftBishop.moveTo(new Position(1,0));
		blackPlayer.rightBishop.moveTo(new Position(6,0));
		
		blackPlayer.leftKnight.moveTo(new Position(2,0));
		blackPlayer.rightKnight.moveTo(new Position(5,0));

		blackPlayer.queen.moveTo(new Position(3,0));
		blackPlayer.king.moveTo(new Position(4,0));
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