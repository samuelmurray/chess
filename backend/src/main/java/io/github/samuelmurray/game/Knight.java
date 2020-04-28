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
        return Stream.of(currentPosition.getAbove().getAbove().getRight(), currentPosition.getAbove().getAbove().getLeft(),
                currentPosition.getLeft().getLeft().getAbove(), currentPosition.getLeft().getLeft().getBelow(),
                currentPosition.getRight().getRight().getAbove(), currentPosition.getRight().getRight().getBelow(),
                currentPosition.getBelow().getBelow().getRight(), currentPosition.getBelow().getBelow().getLeft())
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(Collectors.toUnmodifiableSet());
    }
}
