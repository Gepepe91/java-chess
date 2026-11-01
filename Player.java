package chess;

public class Player {
	
	String id;
	
	Piece pawn1, pawn2, pawn3, pawn4, pawn5, pawn6, pawn7, pawn8;
	
	Piece leftBishop, rightBishop;
	
	Piece leftKnight, rightKnight;
	
	Piece leftRook, rightRook;
	
	Piece queen;
	
	Piece king;
	
	Player(String id){
		this.id = id;
		
		pawn1 = new Pawn();
		pawn2 = new Pawn();
		pawn3 = new Pawn();
		pawn4 = new Pawn();
		pawn5 = new Pawn();
		pawn6 = new Pawn();
		pawn7 = new Pawn();
		pawn8 = new Pawn();
		
		leftBishop = new Bishop();
		rightBishop = new Bishop();
		
		leftKnight = new Knight();
		rightKnight = new Knight();
		
		leftRook = new Rook();
		rightRook = new Rook();
		
		queen = new Queen();
		
		king = new King();
	}

}
