package chess;

public class ChessGame {
	
	Grid gameGrid;
	
	Player whitePlayer;
	Player blackPlayer;
	
	String message;
	
	Piece selectedPiece;
	Piece targetedPiece;
	
	boolean selectMode;
	boolean targetMode;
	
	boolean isWhiteTurn;
	
	ChessGame(){
		gameGrid = new Grid(8,8);
		
		whitePlayer = new Player("white");
		blackPlayer = new Player("black");
		
		selectedPiece = new Piece();
		
		message = "This is a chess game";
		
		selectMode = true;
		targetMode = false;
		
		isWhiteTurn = true;
		
		displayMode();
	}
	
	void initGame() {
		//Place all the pieces of the board
		
		whitePlayer.initPlayer();
		blackPlayer.initPlayer();
		
	}
	
	/*
	void testMouse(Position test) {
	    if (test == null)
	    	return;
	    this.blackPlayer.pawn1.moveTo(test);
	}
	*/
	
	void displayMode() {
		if (selectMode)
			System.out.println("select mode");
		else if (targetMode)
			System.out.println("target mode");
		else
			System.out.println("no mode");
	}
	
	void newClick(Position click) {
		//System.out.println("new click");
		
		if (selectMode) {
			selectPiece(click);
		}
		else if (targetMode) {
			targetPosition(click);
		}
		else
			System.out.println("Error at newClick");
		
		displayMode();
	}
	
	void selectPiece(Position selectionPosition) {
		selectedPiece = getPieceAt(selectionPosition);
		if (selectedPiece == null)
			return;
		System.out.println("Piece selected : " + selectedPiece.type);
		selectedPiece.constructMoveset(gameGrid);
		selectMode = false;
		targetMode = true;
	}
	
	void targetPosition(Position target) {
		targetedPiece = getPieceAt(target);
		
		if (target.isEqual(selectedPiece.position)) {
			selectMode = true;
			targetMode = false;
			System.out.println("Cancel selection");
			return;
		}
				
		if (selectedPiece.canMoveTo(target)) {
			if (targetedPiece != null) {
				targetedPiece.isAlive = false;
				System.out.println("Kill!");
			}
			selectedPiece.moveTo(target);
			isWhiteTurn = !isWhiteTurn;
			selectMode = true;
			targetMode = false;
		}
			
	}
	
	/*
	Piece getPieceAt(Position cell) {
	    Piece p = whitePlayer.getPieceAt(cell);
	    if (p != null)
	    	return p;
	    return blackPlayer.getPieceAt(cell);
	}
	*/
	
	Piece getPieceAt(Position cell) {
		if (isWhiteTurn)
			return whitePlayer.getPieceAt(cell);
		return blackPlayer.getPieceAt(cell);
	}
	
}