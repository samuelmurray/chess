package io.github.samuelmurray.game.piece;

class DummyPieceFactory {
    private DummyPieceFactory() {
    }

    static ChessPiece createPiece() {
        return new ChessPiece(null, null, null);
    }
}
