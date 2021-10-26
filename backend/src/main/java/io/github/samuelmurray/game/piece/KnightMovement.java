package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableSet;

final class KnightMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, Team currentTeam, GameState gameState) {
        return Stream.of(currentPosition.up().up().right(), currentPosition.up().up().left(),
                        currentPosition.left().left().up(), currentPosition.left().left().down(),
                        currentPosition.right().right().up(), currentPosition.right().right().down(),
                        currentPosition.down().down().right(), currentPosition.down().down().left())
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(toUnmodifiableSet());
    }
}
