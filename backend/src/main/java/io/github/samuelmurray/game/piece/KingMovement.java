package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class KingMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        return Stream.of(currentPosition.getAbove(), currentPosition.getBelow(), currentPosition.getLeft(), currentPosition.getRight(),
                currentPosition.getAbove().getRight(), currentPosition.getAbove().getLeft(),
                currentPosition.getBelow().getRight(), currentPosition.getBelow().getLeft())
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(Collectors.toUnmodifiableSet());
    }
}
