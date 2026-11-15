package chess;

import java.util.ArrayList;

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
	
	// Need to create virtual board just to see if a check is solved
	
	
	
	ChessGame(){
		gameGrid = new Grid(8,8);
		
		whitePlayer = new Player("white");
		blackPlayer = new Player("black");
		
		selectedPiece = new Piece();
		
		message = "This is a chess game";
		
		selectMode = true;
		targetMode = false;
		
		isWhiteTurn = true;
		
		//displayMode();
	}
	
	void initGame() {
		//Place all the pieces of the board
		
		whitePlayer.initPlayer("debug");
		blackPlayer.initPlayer("debug");
		
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
		
		if (selectedPiece.color == 0 && !isWhiteTurn) // if white piece and black turn
			return;
		if (selectedPiece.color == 1 && isWhiteTurn) // if black piece and white turn
			return;
		
		//System.out.println("Piece selected : " + selectedPiece.type);
		selectedPiece.constructMoveset(this);
	    selectedPiece.removeAlliedTargets(this);
		selectMode = false;
		targetMode = true;
	}
	
	void targetPosition(Position target) {
		Player player = getPlayerOfAPiece(selectedPiece);
		Player opponent = getOpponent(player);
		targetedPiece = getPieceAt(target);
		
		if (!target.isInside(gameGrid))
			return;

		if (target.isEqual(selectedPiece.position)) {
			selectMode = true;
			targetMode = false;
			selectedPiece.moveset.clear();
			System.out.println("Cancel selection");
			return;
		}

		if (!selectedPiece.canMoveTo(target))
			return;
		
		//Need to verify if the player is in check state
		if (player.checkState) {
			// Need to play a virtual turn and verify if this virtual board is still a check
			//Before that, let's try to store the previous position, play, verify and if it is still a check, cancel the move
			Position previousPosition = selectedPiece.position;
			selectedPiece.moveTo(target);
			if (isCheck(player)) {
				selectedPiece.moveTo(previousPosition);
				System.out.println("Cancel turn because player did not solve the check state");
				return;
			}
		}
		
		//System.out.println("let's go");
		if (targetedPiece != null) {
			targetedPiece.isAlive = false;
			System.out.println("Kill!");
		}
		
		selectedPiece.moveTo(target);
		isWhiteTurn = !isWhiteTurn;
		System.out.println(isWhiteTurn);
		selectMode = true;
		targetMode = false;
		selectedPiece.moveset.clear();
		player.checkState = false;

		selectedPiece.moveCount++;
		
		if (selectedPiece.shouldTurnIntoQueen()) {
			Player owner = (selectedPiece.color == 0) ? whitePlayer : blackPlayer;
		    Piece queen = owner.promote(selectedPiece);
		    selectedPiece = queen;
			System.out.println("Slayyyyy 💅");
		}
		
		if (isCheck(opponent))
			opponent.checkState = true;
			

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
	
	boolean isCheck(Player player) {
		// Work in progress
		
		//boolean test = false;
		Player opponent = getOpponent(player);
		Piece king = player.king;
		ArrayList<Position> moveset;
		
		ArrayList<Piece> opponentPieces = opponent.allPieces;
		for (int index = 0 ; index < opponentPieces.size(); index++) {
			opponentPieces.get(index).constructMoveset(this);
			moveset = opponentPieces.get(index).moveset;
			if (king.isInsideMoveset(moveset)) {
				System.out.println("Check");
			}
				return true;
			
		}
		
		return false;
	}
	
	Player getOpponent(Player player) {
		if (player.id == "white")
			return blackPlayer;
		else
			return whitePlayer;
	}
	
	Player getPlayerOfAPiece(Piece piece) {
		if (piece.color == 0)
			return whitePlayer;
		else;
		return blackPlayer;
	}
	
	
	
}