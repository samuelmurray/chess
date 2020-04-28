package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

class MovementHelper {
    private MovementHelper() {
    }

    static Set<Position> getPositionsInDirectionAndStopAtPiece(Position currentPosition, GameState gameState,
                                                               Function<Position, Position> direction) {
        Set<Position> positions = new HashSet<>();
        while (true) {
            currentPosition = direction.apply(currentPosition);
            if (currentPosition == Position.OUT_OF_BOARD) {
                break;
            }
            positions.add(currentPosition);
            if (gameState.hasPieceAt(currentPosition)) {
                break;
            }
        }
        return positions;
    }
}
