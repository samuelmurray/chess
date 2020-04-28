package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.HashSet;
import java.util.Set;

import static io.github.samuelmurray.game.piece.MovementHelper.getPositionsInDirectionAndStopAtPiece;

public class BishopMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        Set<Position> positions = new HashSet<>();
        positions.addAll(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getAbove().getRight()));
        positions.addAll(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getAbove().getLeft()));
        positions.addAll(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getBelow().getLeft()));
        positions.addAll(getPositionsInDirectionAndStopAtPiece(currentPosition, gameState, position -> position.getBelow().getRight()));
        return positions;
    }
}
