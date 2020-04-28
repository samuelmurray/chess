package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.samuelmurray.game.piece.MovementHelper.getDiagonalMovement;
import static io.github.samuelmurray.game.piece.MovementHelper.getStraightMovement;

public class QueenMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        return Stream.of(getStraightMovement(currentPosition, gameState), getDiagonalMovement(currentPosition, gameState))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
