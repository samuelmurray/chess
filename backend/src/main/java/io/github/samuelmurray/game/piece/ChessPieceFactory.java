package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.Team;

import java.util.Objects;

import static io.github.samuelmurray.game.piece.PieceType.*;

public final class ChessPieceFactory {
    private ChessPieceFactory() {
    }

    public static ChessPiece createPiece(PieceType type, Team team) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(team);
        return new ChessPiece(team, type);
    }
}
