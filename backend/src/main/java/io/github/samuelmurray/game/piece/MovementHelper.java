package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.samuelmurray.game.Position.OUT_OF_BOARD;

class MovementHelper {
    private MovementHelper() {
    }

    static Set<Position> getStraightMovement(Position currentPosition, GameState gameState) {
        return Stream.of(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::getAbove),
                getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::getBelow),
                getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::getLeft),
                getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::getRight))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    static Set<Position> getDiagonalMovement(Position currentPosition, GameState gameState) {
        return Stream.of(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getAbove().getRight()),
                getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getAbove().getLeft()),
                getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getBelow().getLeft()),
                getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getBelow().getRight()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    static Set<Position> getPositionsInDirectionAndStopAtPiece(Position currentPosition, GameState gameState,
                                                               Function<Position, Position> direction) {
        Set<Position> positions = new HashSet<>();
        for (var nextPosition = direction.apply(currentPosition); nextPosition != OUT_OF_BOARD; nextPosition = direction.apply(nextPosition)) {
            positions.add(nextPosition);
            if (gameState.hasPieceAt(nextPosition)) {
                return positions;
            }
        }
        return positions;
    }
}
