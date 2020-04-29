package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Set;

import static io.github.samuelmurray.game.piece.MovementHelper.getStraightMovement;

final class RookMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, Team currentTeam, GameState gameState) {
        return getStraightMovement(currentPosition, gameState);
    }
}
