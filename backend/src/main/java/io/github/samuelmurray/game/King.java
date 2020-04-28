package io.github.samuelmurray.game;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends ChessPiece {

    public King(Team team) {
        super(team);
    }

    @Override
    protected Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        return Stream.of(currentPosition.getAbove(), currentPosition.getBelow(), currentPosition.getLeft(), currentPosition.getRight(),
                currentPosition.getAbove().getRight(), currentPosition.getAbove().getLeft(),
                currentPosition.getBelow().getRight(), currentPosition.getBelow().getLeft())
                .filter(position -> position != Position.OUT_OF_BOARD)
                .collect(Collectors.toUnmodifiableSet());
    }
}
