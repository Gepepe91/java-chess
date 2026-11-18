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
		selectedPiece.clearMoveset();
	}
	
	void selectPiece(Position selectionPosition) {
		// CHANGEMENT : on passe par une variable locale. Avant, selectedPiece était
		// écrasée même quand le clic était refusé (case vide, mauvaise couleur),
		// ce qui pouvait la laisser à null ou sur une pièce adverse.
		Piece clicked = getPieceAt(selectionPosition);
		if (clicked == null)
			return;
		
		if (clicked.getColor() == 0 && !isWhiteTurn)
			return;
		if (clicked.getColor() == 1 && isWhiteTurn)
			return;
		
		selectedPiece = clicked; // affectation seulement une fois le clic validé
		selectedPiece.constructMoveset(this);
		selectedPiece.removeAlliedTargets(this);
		selectMode = false;
		targetMode = true;
	}
	
	void targetPosition(Position target) {
		/*
		 * 1. target inside the grid?
		 * 2. cancel turn ?
		 * 3. piece can move to target? (maybe should include isInside)
		 * 4. Would the move imply a check state?
		 * 5. empty
		 * 6. Is the move killing an opponent's piece?
		 * 7. Moving to the target, turn is updated and check state set to false
		 * 8. Should the piece turns into a queen ?
		 * 9. Did the move put the opponent in check state ?
		 * 10. Did the move won the game?
		 */
		Player player = getPlayerOfAPiece(selectedPiece);
		Player opponent = getOpponent(player);
		
		// 1. cible dans la grille
		if (!target.isInside(gameGrid))
			return;
		
		targetedPiece = getPieceAt(target);
		
		// 2. annulation de la sélection
		if (target.isEqual(selectedPiece.getPosition())) {
			selectMode = true;
			targetMode = false;
			selectedPiece.clearMoveset();
			System.out.println("Cancel selection");
			return;
		}
		
		// 3. le coup est dans le moveset
		if (!selectedPiece.canMoveTo(target))
			return;
		
		// 4. CHANGEMENT : une seule vérification pour tous les cas.
		// Question posée : "après mon coup, mon roi est-il attaqué ?"
		// Si oui, le coup est illégal, que je sois déjà en échec ou non.
		// true = mauvais coup (sens cohérent avec le nom de la méthode).
		if (targetResultsInCheckState(player, target)) {
			System.out.println("Illegal move: your king would be in check");
			return;
		}
		
		// 5. CHANGEMENT : ancienne étape 5 supprimée, elle refaisait le même test.
		
		// 6. capture réelle (définitive cette fois)
		kill(targetedPiece , opponent);
		
		// 7. déplacement réel
		// CHANGEMENT : on copie la position au lieu de garder l'objet du clic,
		// pour éviter qu'un setPosition() côté interface ne déplace la pièce.
		selectedPiece.moveTo(new Position(target.getLine(), target.getColumn()));
		isWhiteTurn = !isWhiteTurn;
		selectMode = true;
		targetMode = false;
		selectedPiece.clearMoveset();
		player.checkState = false;
		selectedPiece.addOneToMoveCount();
		
		// 8. promotion (voir remarque sur Player.promote plus bas)
		if (selectedPiece.shouldTurnIntoQueen()) {
			Player owner = (selectedPiece.getColor() == 0) ? whitePlayer : blackPlayer;
			Piece queen = owner.promote(selectedPiece);
			selectedPiece = queen;
		}
		
		// 9. CHANGEMENT : affectation directe au lieu de changeCheckState().
		// Le flag reflète exactement ce que isCheck() vient de calculer,
		// quelle que soit sa valeur précédente.
		opponent.checkState = isCheck(opponent);
		if (opponent.checkState) {
			System.out.println(opponent.id + " is now in check state");
			// Now is it a check mate ?
			if (isCheckMate(opponent))
				System.out.println("CHECKMATE\n" + opponent.id + " has won!");
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
	
	boolean isCheck(Player player) {
		Player opponent = getOpponent(player);
		Piece king = player.king;
		
		for (int index = 0; index < opponent.allPieces.size(); index++) {
			Piece tempPiece = opponent.allPieces.get(index);
			
			// CHANGEMENT : une pièce morte (capturée, réellement ou en simulation)
			// ne menace plus rien. C'est ce filtre qui rend la simulation de capture utile.
			if (!tempPiece.getIsAlive())
				continue;
			
			tempPiece.constructMoveset(this);
			boolean attacksKing = king.isInsideMoveset(tempPiece.getMoveset());
			tempPiece.clearMoveset(); // nettoyage dans tous les cas, avant de sortir
			
			if (attacksKing)
				return true;
		}
		return false;
	}
	
	Player getOpponent(Player player) {
		// CHANGEMENT : .equals au lieu de ==
		if (player.id.equals("white"))
			return blackPlayer;
		else
			return whitePlayer;
	}
	
	Player getPlayerOfAPiece(Piece piece) {
		// CHANGEMENT : suppression du "else;" parasite
		if (piece.getColor() == 0)
			return whitePlayer;
		return blackPlayer;
	}
	
	boolean targetResultsInCheckState(Player player, Position targetPosition) {
		Position previousPosition = selectedPiece.getPosition();
		
		// CHANGEMENT : plus de copie de pièce. La simulation ne modifie qu'une donnée
		// de la pièce capturée : isAlive. On la met à false, puis on la remet à true.
		// (getPieceAt ne renvoie que des pièces vivantes, donc "true" est la bonne
		// valeur de restauration.)
		Piece capturedPiece = getPieceAt(targetPosition);
		if (capturedPiece != null)
			capturedPiece.setIsAlive(false); // direct, pas kill() : pas de message parasite
		
		selectedPiece.moveTo(targetPosition);
		
		boolean stillInCheck = isCheck(player);
		
		// CHANGEMENT : restauration unique, après le test, au lieu d'une par branche.
		selectedPiece.moveTo(previousPosition);
		if (capturedPiece != null)
			capturedPiece.setIsAlive(true);
		
		return stillInCheck; // true = le coup laisse/met mon roi en échec
	}
	
	void changeCheckState(Player player) {
		if (player.checkState)
			player.checkState = false;
		else
			player.checkState = true;
	}
	
	void kill(Piece target , Player opponent) {
		if (target != null) {
			//target.setIsAlive(false);
			target.die(opponent);
			System.out.println("Kill!");
		}
	}
	
	boolean isCheckMate(Player player) {
		/**
		 * 1. See if the player is in check state
		 * 2. See if the king's moveset is empty
		 * 3. See if any movements from the allied pieces solve the check state
		 */
		
		// First need to be in check state
		// Tested right before in targetPosition
		//if (!player.checkState)
		//	return false;
		
		// actually useless : the king capacity to escape is tested in the next double loop
		//if (! (player.king.getMoveset().isEmpty()) )
		//	return false;
		
		// get the moveset for every allied piece
		// inside this loop, new loop to test every target possible and see if it results in a check state
		// Luckily, there is not that many pieces, combinatory explosion under control
		Piece currentPiece;
		ArrayList<Position> currentMoveset;
		Position currentPosition;
		
		System.out.println("Check Mate test started");
		
		for (int indexPiece=0 ; indexPiece < player.allPieces.size() ; indexPiece++) {
			currentPiece = player.allPieces.get(indexPiece);
			currentMoveset = currentPiece.constructAndGetMoveset(this);
			System.out.println("Test of " + currentPiece.getType());
			
			for (int indexTarget=0 ; indexTarget < currentMoveset.size() ; indexTarget++) {
				//System.out.println("Test of " + currentPiece.getType());
				if ( targetResultsInCheckState(player , currentMoveset.get(indexTarget)) )
					return false;
			}
		}
		// No valid way to play saves the king : check mate! 
		return true;
	}
	
	
	
}