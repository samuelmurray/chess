package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Set;

interface PieceMovement {
    Set<Position> getPotentiallyValidMoves(Position currentPosition, Team currentTeam, GameState gameState);
}
