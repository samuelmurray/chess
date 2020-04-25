package io.github.samuelmurray.game;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Knight extends ChessPiece {
    public Knight(Team team) {
        super(team);
    }

    @Override
    protected Set<Position> getPotentiallyValidMoves(Position currentPosition) {
        return Stream.of(currentPosition.above.above.right, currentPosition.above.above.left,
                currentPosition.left.left.above, currentPosition.left.left.below,
                currentPosition.right.right.above, currentPosition.right.right.below,
                currentPosition.below.below.right, currentPosition.below.below.left)
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(Collectors.toUnmodifiableSet());
    }
}
