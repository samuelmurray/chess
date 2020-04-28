package io.github.samuelmurray.game.piece;


import io.github.samuelmurray.game.ChessException;
import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ChessPiece {
    private final Team team;
    private final PieceMovement pieceMovement;

    public ChessPiece(Team team, PieceMovement pieceMovement) {
        this.team = team;
        this.pieceMovement = pieceMovement;
    }

    public Set<Position> getValidMoves(GameState gameState) {
        Position currentPosition = gameState.getPositionOfPiece(this)
                .orElseThrow(() -> new ChessException("Can't call getValidModes on piece missing from the game state"));
        return pieceMovement.getPotentiallyValidMoves(currentPosition, gameState).stream()
                .filter(position -> {
                    Optional<ChessPiece> maybeOther = gameState.getPieceAt(position);
                    return maybeOther.map(isCapturingMove()).orElse(true);
                })
                .collect(Collectors.toSet());
    }

    private Function<ChessPiece, Boolean> isCapturingMove() {
        return piece -> piece.team != this.team;
    }
}
