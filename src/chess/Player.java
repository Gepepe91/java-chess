package chess;

import java.util.ArrayList;

public class Player {
	
	String id; // white or black
	
	ArrayList<Piece> pawns;
	
	Piece pawn1, pawn2, pawn3, pawn4, pawn5, pawn6, pawn7, pawn8;
	
	Piece leftBishop, rightBishop;
	
	Piece leftKnight, rightKnight;
	
	Piece leftRook, rightRook;
	
	Piece queen;
	
	Piece king;
	
	ArrayList<Piece> allPieces;
	
	boolean checkState = false; // true is the king is directly menaced
	
	Player(String id){
		this.id = id;
		
		int color;
		if (id.equals("white"))
			color = 0;
		else
			color = 1;
		
		pawn1 = new Pawn(color);
		pawn2 = new Pawn(color);
		pawn3 = new Pawn(color);
		pawn4 = new Pawn(color);
		pawn5 = new Pawn(color);
		pawn6 = new Pawn(color);
		pawn7 = new Pawn(color);
		pawn8 = new Pawn(color);
		
		pawns = new ArrayList<Piece>();
		pawns.add(pawn1);
		pawns.add(pawn2);
		pawns.add(pawn3);
		pawns.add(pawn4);
		pawns.add(pawn5);
		pawns.add(pawn6);
		pawns.add(pawn7);
		pawns.add(pawn8);
		
		leftBishop = new Bishop(color);
		rightBishop = new Bishop(color);
		
		leftKnight = new Knight(color);
		rightKnight = new Knight(color);
		
		leftRook = new Rook(color);
		rightRook = new Rook(color);
		
		queen = new Queen(color);
		
		king = new King(color);
		
		allPieces = new ArrayList<Piece>();
		
		allPieces.add(pawn1);
		allPieces.add(pawn2);
		allPieces.add(pawn3);
		allPieces.add(pawn4);
		allPieces.add(pawn5);
		allPieces.add(pawn6);
		allPieces.add(pawn7);
		allPieces.add(pawn8);
		
		allPieces.add(leftBishop);
		allPieces.add(rightBishop);
		
		allPieces.add(leftKnight);
		allPieces.add(rightKnight);
		
		allPieces.add(leftRook);
		allPieces.add(rightRook);
		
		allPieces.add(queen);
		allPieces.add(king);
	}
	
	
	void initPlayer() {
		if (id == "white")
			initWhitePlayer();
		else if (id == "black")
			initBlackPlayer();
		else
			System.out.println("Player is neither white or black");
	}
	
	void initPlayer(String debug) {
		if (debug == "debug") {
			if (id == "white")
				initWhitePlayerDebug();
			else if (id == "black")
				initBlackPlayerDebug();
		}
	}
	
	void initWhitePlayerDebug() {

	    for (Piece piece : allPieces)
	        piece.setIsAlive(false);

	    allPieces.clear();

	    king.setIsAlive(true);
	    allPieces.add(king);

	    queen.setIsAlive(true);
	    allPieces.add(queen);

	    king.moveTo(new Position(2,5));
	    queen.moveTo(new Position(2,6));

	}

	void initBlackPlayerDebug() {

	    for (Piece piece : allPieces)
	        piece.setIsAlive(false);

	    allPieces.clear();

	    king.setIsAlive(true);
	    allPieces.add(king);

	    king.moveTo(new Position(0,7));

	}
	
	void initWhitePlayer() {
		
		pawn1.moveTo(new Position(6,0));
		pawn2.moveTo(new Position(6,1));
		pawn3.moveTo(new Position(6,2));
		pawn4.moveTo(new Position(6,3));
		pawn5.moveTo(new Position(6,4));
		pawn6.moveTo(new Position(6,5));
		pawn7.moveTo(new Position(6,6));
		pawn8.moveTo(new Position(6,7));
		
		for (int index=0 ; index < 8 ; index++)
			pawns.get(index).moveTo(new Position(6 , index));
		
		
		
		leftRook.moveTo(new Position(7,0));
		rightRook.moveTo(new Position(7,7));
		
		leftKnight.moveTo(new Position(7,1));
		rightKnight.moveTo(new Position(7,6));
		
		leftBishop.moveTo(new Position(7,2));
		rightBishop.moveTo(new Position(7,5));
		
		queen.moveTo(new Position(7,3));
		
		king.moveTo(new Position(7,4));
	}
	
	void initBlackPlayer() {
		
		pawn1.moveTo(new Position(1,7));
		pawn2.moveTo(new Position(1,6));
		pawn3.moveTo(new Position(1,5));
		pawn4.moveTo(new Position(1,4));
		pawn5.moveTo(new Position(1,3));
		pawn6.moveTo(new Position(1,2));
		pawn7.moveTo(new Position(1,1));
		pawn8.moveTo(new Position(1,0));
		
		for (int index=0 ; index < 8 ; index++)
			pawns.get(index).moveTo(new Position(1 , index));
		
		leftRook.moveTo(new Position(0,7));
		rightRook.moveTo(new Position(0,0));
		
		leftKnight.moveTo(new Position(0,6));
		rightKnight.moveTo(new Position(0,1));
		
		leftBishop.moveTo(new Position(0,5));
		rightBishop.moveTo(new Position(0,2));
		
		queen.moveTo(new Position(0,3));
		
		king.moveTo(new Position(0,4));
		
	}
	
	/*
	Piece[] getPieces() {
	    return new Piece[] {
	        pawn1, pawn2, pawn3, pawn4, pawn5, pawn6, pawn7, pawn8,
	        leftRook, rightRook, leftKnight, rightKnight,
	        leftBishop, rightBishop, queen, king
	    };
	}
	*/
	
	Piece[] getPieces() {
	    ArrayList<Piece> all = new ArrayList<Piece>(pawns); // pions + reines promues
	    all.add(leftRook);
	    all.add(rightRook);
	    all.add(leftKnight);
	    all.add(rightKnight);
	    all.add(leftBishop);
	    all.add(rightBishop);
	    all.add(queen);
	    all.add(king);
	    return all.toArray(new Piece[0]);
	}
	
	Piece getPieceAt(Position cell) {
	    for (Piece piece : getPieces()) {
	        if (piece.getIsAlive()
	                && piece.getLine() == cell.getLine()
	                && piece.getColumn() == cell.getColumn()) {
	            return piece;
	        }
	    }
	    return null;   // nobody there
	}
	
	Piece promote(Piece pawn) {
	    Queen newQueen = new Queen(pawn.getPosition() , pawn.getColor());
	    pawns.set(pawns.indexOf(pawn), newQueen);
	    pawns.remove(pawn);
	    return newQueen;
	}
	
	
	
	
}
