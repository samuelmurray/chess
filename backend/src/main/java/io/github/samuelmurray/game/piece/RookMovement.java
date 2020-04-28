package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.Set;

import static io.github.samuelmurray.game.piece.MovementHelper.getStraightMovement;

final class RookMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        return getStraightMovement(currentPosition, gameState);
    }
}
