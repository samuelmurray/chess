package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.Set;

interface PieceMovement {
    Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState);
}
