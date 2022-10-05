package io.github.samuelmurray.game.piece;


import io.github.samuelmurray.game.ChessException;
import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

public final class ChessPiece {
    private final Team team;
    private final PieceType type;

    ChessPiece(Team team, PieceType type) {
        this.team = team;
        this.type = type;
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getType() {
        return type;
    }

    public Set<Position> getValidMoves(GameState gameState) {
        Position currentPosition = gameState.getPositionOfPiece(this)
                .orElseThrow(() -> new ChessException("Can't call getValidMoves on piece missing from the game state"));
        return type.movement().getPotentiallyValidMoves(currentPosition, team, gameState).stream()
                .filter(position -> gameState.getPieceAt(position).map(this::isCapturingMove).orElse(true))
                .collect(toUnmodifiableSet());
    }

    private boolean isCapturingMove(ChessPiece piece) {
        return piece.team != this.team;
    }
}
