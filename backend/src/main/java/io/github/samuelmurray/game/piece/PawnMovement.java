package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;

import java.util.Collections;
import java.util.Set;

public class PawnMovement implements PieceMovement {
    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        return Collections.emptySet();
    }
}
