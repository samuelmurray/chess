package io.github.samuelmurray.game;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Rook extends ChessPiece {

    public Rook(Team team) {
        super(team);
    }

    @Override
    protected Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
        Set<Position> positions = new HashSet<>();
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getAbove));
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getBelow));
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getLeft));
        positions.addAll(getPotentialPositionsInDirection(currentPosition, gameState, Position::getRight));
        return positions;
    }

    private static Set<Position> getPotentialPositionsInDirection(Position currentPosition, GameState gameState,
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
