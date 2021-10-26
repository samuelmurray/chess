package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableSet;

final class MovementHelper {
    private MovementHelper() {
    }

    static Set<Position> getStraightMovement(Position currentPosition, GameState gameState) {
        return Stream.of(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::up),
                        getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::down),
                        getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::left),
                        getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, Position::right))
                .flatMap(Collection::stream)
                .collect(toUnmodifiableSet());
    }

    static Set<Position> getDiagonalMovement(Position currentPosition, GameState gameState) {
        return Stream.of(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.up().right()),
                        getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.up().left()),
                        getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.down().left()),
                        getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.down().right()))
                .flatMap(Collection::stream)
                .collect(toUnmodifiableSet());
    }

    static Set<Position> getPositionsInDirectionAndStopAtPiece(Position currentPosition, GameState gameState,
                                                               UnaryOperator<Position> direction) {
        Set<Position> positions = new HashSet<>();
        for (var nextPosition = direction.apply(currentPosition); nextPosition.isOnBoard(); nextPosition = direction.apply(nextPosition)) {
            positions.add(nextPosition);
            if (gameState.hasPieceAt(nextPosition)) {
                return positions;
            }
        }
        return positions;
    }
}
