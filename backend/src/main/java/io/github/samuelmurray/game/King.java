package io.github.samuelmurray.game;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends ChessPiece {

    public King(Team team) {
        super(team);
    }

    @Override
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

    private static Set<Position> getPotentiallyValidMoves(Position currentPosition) {
        return Set.of(currentPosition.above, currentPosition.below, currentPosition.left, currentPosition.right,
                currentPosition.above.right, currentPosition.above.left,
                currentPosition.below.right, currentPosition.below.left);
    }
}
