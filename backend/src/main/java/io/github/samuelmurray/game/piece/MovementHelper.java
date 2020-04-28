package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static io.github.samuelmurray.game.Position.OUT_OF_BOARD;

class MovementHelper {
    private MovementHelper() {
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
