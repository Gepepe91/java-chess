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
	
	boolean debugMode;
	
	ChessGame(){
		gameGrid = new Grid(8,8);
		
		whitePlayer = new Player("white");
		blackPlayer = new Player("black");
		
		selectedPiece = new Piece();
		
		message = "This is a chess game";
		
		selectMode = true;
		targetMode = false;
		
		isWhiteTurn = true;
		
		debugMode = false;
		
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
	
	void clickOutOfBound() {
		System.out.println("Click out of bounds");
		selectMode = true;
		targetMode = false;
		selectedPiece.moveset.clear();
	}
	
	void selectPiece(Position selectionPosition) {
		selectedPiece = getPieceAt(selectionPosition);
		if (selectedPiece == null)
			return;
		System.out.println("Piece selected : " + selectedPiece.type);
		selectedPiece.constructMoveset(this);
		selectMode = false;
		targetMode = true;
	}
	
	void targetPosition(Position target) {
		targetedPiece = getPieceAt(target);
		
		if (target.isEqual(selectedPiece.position)) {
			selectMode = true;
			targetMode = false;
			selectedPiece.moveset.clear();
			System.out.println("Cancel selection");
			return;
		}
		
		if (!target.isInside(gameGrid))
			return;
				
		if (selectedPiece.canMoveTo(target)) {
			System.out.println("let's go");
			if (targetedPiece != null) {
				targetedPiece.isAlive = false;
				System.out.println("Kill!");
			}
			selectedPiece.moveTo(target);
			isWhiteTurn = !isWhiteTurn;
			selectMode = true;
			targetMode = false;
			selectedPiece.moveset.clear();
		}
			
	}
	
	
	Piece getPieceAt(Position cell) {
	    Piece position = whitePlayer.getPieceAt(cell);
	    if (position != null)
	        return position;
	    return blackPlayer.getPieceAt(cell);
	}

	boolean isOccupied(Position p) {
	    return getPieceAt(p) != null;
	}
	
	
	
}