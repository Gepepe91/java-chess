package chess;

public class Pawn extends Piece {
    Pawn(Position position, int color) { super(position, color); }

    @Override
    String getType() { return "pawn"; }
}
