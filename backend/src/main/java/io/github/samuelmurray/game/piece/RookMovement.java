package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.HashSet;
import java.util.Set;

import static io.github.samuelmurray.game.piece.MovementHelper.getPotentialPositionsInDirection;

final class RookMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        Set<Position> positions = new HashSet<>();
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getAbove));
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getBelow));
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getLeft));
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getRight));
        return positions;
    }
}
