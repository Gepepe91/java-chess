package chess;

public class ChessGame {
	
	Grid gameGrid;
	
	Player whitePlayer;
	Player blackPlayer;
	
	String message;
	
	Piece selectedPiece;
	Piece targetedPiece;
	
	boolean selectMode = true;
	boolean targetMode = false;
	
	boolean isWhiteTurn;
	
	ChessGame(){
		gameGrid = new Grid(8,8);
		
		whitePlayer = new Player("white");
		blackPlayer = new Player("black");
		
		selectedPiece = new Piece();
		
		message = "This is a chess game";
		
		isWhiteTurn = true;
	}
	
	void initGame() {
		//Place all the pieces of the board
		
		whitePlayer.initPlayer();
		blackPlayer.initPlayer();
	}
	
	void testMouse(Position test) {
	    if (test == null)
	    	return;
	    this.blackPlayer.pawn1.moveTo(test);
	}
	
	void newClick(Position click) {
		System.out.println("new click");
		
		if (selectMode) {
			selectPiece(click);
		}
		else if (targetMode) {
			targetPosition(click);
		}
		else
			System.out.println("Error at newClick");
	}
	
	void selectPiece(Position selectionPosition) {
		selectedPiece = getPieceAt(selectionPosition);
		if (selectedPiece == null)
			return;
		selectMode = false;
		targetMode = true;
		System.out.println("target mode");
	}
	
	void targetPosition(Position target) {
		targetedPiece = getPieceAt(target);
		
		if (target.isEqual(selectedPiece.position)) {
			selectMode = true;
			targetMode = false;
			System.out.println("select mode");
			return;
		}
				
		if (selectedPiece.canMoveTo(target)) {
			if (targetedPiece != null) {
				targetedPiece.isAlive = false;
				System.out.println("Kill!");
			}
			selectedPiece.moveTo(target);
			selectMode = true;
			targetMode = false;
			System.out.println("select mode");
		}
			
	}
		
	Piece getPieceAt(Position cell) {
	    Piece p = whitePlayer.getPieceAt(cell);
	    if (p != null) return p;
	    return blackPlayer.getPieceAt(cell);
	}
	
}