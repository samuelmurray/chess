package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableSet;

final class KingMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, Team currentTeam, GameState gameState) {
        return Stream.of(currentPosition.up(), currentPosition.down(), currentPosition.left(), currentPosition.right(),
                        currentPosition.up().right(), currentPosition.up().left(),
                        currentPosition.down().right(), currentPosition.down().left())
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(toUnmodifiableSet());
    }
}
