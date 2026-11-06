package chess;

public class Player {
	
	String id; // white or black
	
	Piece pawn1, pawn2, pawn3, pawn4, pawn5, pawn6, pawn7, pawn8;
	
	Piece leftBishop, rightBishop;
	
	Piece leftKnight, rightKnight;
	
	Piece leftRook, rightRook;
	
	Piece queen;
	
	Piece king;
	
	Player(String id){
		this.id = id;
		
		int color;
		if (id == "white")
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
		
		leftBishop = new Bishop();
		rightBishop = new Bishop();
		
		leftKnight = new Knight();
		rightKnight = new Knight();
		
		leftRook = new Rook();
		rightRook = new Rook();
		
		queen = new Queen();
		
		king = new King();
	}
	
	
	void initPlayer() {
		if (id == "white")
			initWhitePlayer();
		else if (id == "black")
			initBlackPlayer();
		else
			System.out.println("Player is neither white or black");
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
		
		leftRook.moveTo(new Position(0,7));
		rightRook.moveTo(new Position(0,0));
		
		leftKnight.moveTo(new Position(0,6));
		rightKnight.moveTo(new Position(0,1));
		
		leftBishop.moveTo(new Position(0,5));
		rightBishop.moveTo(new Position(0,2));
		
		queen.moveTo(new Position(0,3));
		
		king.moveTo(new Position(0,4));
		
	}
	
	Piece[] getPieces() {
	    return new Piece[] {
	        pawn1, pawn2, pawn3, pawn4, pawn5, pawn6, pawn7, pawn8,
	        leftRook, rightRook, leftKnight, rightKnight,
	        leftBishop, rightBishop, queen, king
	    };
	}
	
	Piece getPieceAt(Position cell) {
	    for (Piece piece : getPieces()) {
	        if (piece.isAlive
	                && piece.position.line == cell.line
	                && piece.position.column == cell.column) {
	            return piece;
	        }
	    }
	    return null;   // nobody there
	}
	
	void constructAllMoveset() {
		for (Piece piece : getPieces()) {
			if (piece.isAlive)
				piece.constructMoveset(new Grid(8,8));
		}
		System.out.println("Movesets constructed");
	}

}
