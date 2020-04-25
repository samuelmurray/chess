package io.github.samuelmurray.game;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ChessPiece {
    protected final Team team;

    public ChessPiece(Team team) {
        this.team = team;
    }

    public Set<Position> getValidMoves(GameState gameState) {
        Position currentPosition = gameState.getPositionOfPiece(this)
                .orElseThrow(() -> new ChessException("Can't call getValidModes on piece missing from the game state"));
        return getPotentiallyValidMoves(currentPosition).stream()
                .filter(position -> {
                    Optional<ChessPiece> maybeOther = gameState.getPieceAt(position);
                    return maybeOther.map(piece -> piece.team != this.team).orElse(true);
                })
                .collect(Collectors.toSet());
    }

    protected abstract Set<Position> getPotentiallyValidMoves(Position currentPosition);
}
