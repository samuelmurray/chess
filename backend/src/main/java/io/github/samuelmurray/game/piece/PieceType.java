package io.github.samuelmurray.game.piece;

public enum PieceType {
    KING(new KingMovement()),
    QUEEN(new QueenMovement()),
    BISHOP(new BishopMovement()),
    ROOK(new RookMovement()),
    KNIGHT(new KnightMovement()),
    PAWN(new PawnMovement()),
    ;

    private final PieceMovement movement;

    PieceType(PieceMovement movement) {
        this.movement = movement;
    }

    public PieceMovement movement() {
        return movement;
    }
}
