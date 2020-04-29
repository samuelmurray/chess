package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class KnightMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, Team currentTeam, GameState gameState) {
        return Stream.of(currentPosition.getAbove().getAbove().getRight(), currentPosition.getAbove().getAbove().getLeft(),
                currentPosition.getLeft().getLeft().getAbove(), currentPosition.getLeft().getLeft().getBelow(),
                currentPosition.getRight().getRight().getAbove(), currentPosition.getRight().getRight().getBelow(),
                currentPosition.getBelow().getBelow().getRight(), currentPosition.getBelow().getBelow().getLeft())
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(Collectors.toUnmodifiableSet());
    }
}
