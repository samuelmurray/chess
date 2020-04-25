package io.github.samuelmurray.game;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends ChessPiece {

    public King(Team team) {
        super(team);
    }

    @Override
    protected Set<Position> getPotentiallyValidMoves(Position currentPosition) {
        return Stream.of(currentPosition.above, currentPosition.below, currentPosition.left, currentPosition.right,
                currentPosition.above.right, currentPosition.above.left, currentPosition.below.right, currentPosition.below.left)
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(Collectors.toUnmodifiableSet());
    }
}
