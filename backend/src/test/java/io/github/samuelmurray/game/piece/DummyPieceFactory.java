package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.Team;

class DummyPieceFactory {
    private DummyPieceFactory() {
    }

    static ChessPiece createPiece() {
        return createPiece(null);
    }

    static ChessPiece createPiece(Team team) {
        return createPiece(team, null);
    }

    static ChessPiece createPiece(Team team, PieceMovement pieceMovement) {
        return new ChessPiece(team, null, pieceMovement, null);
    }
}
