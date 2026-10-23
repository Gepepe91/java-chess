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
		
		pawn1 = new Piece();
		pawn2 = new Piece();
		pawn3 = new Piece();
		pawn4 = new Piece();
		pawn5 = new Piece();
		pawn6 = new Piece();
		pawn7 = new Piece();
		pawn8 = new Piece();
		
		leftBishop = new Piece();
		rightBishop = new Piece();
		
		leftKnight = new Piece();
		rightKnight = new Piece();
		
		leftRook = new Piece();
		rightRook = new Piece();
		
		queen = new Piece();
		
		king = new Piece();
	}

}
