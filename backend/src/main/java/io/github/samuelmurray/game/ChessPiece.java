package io.github.samuelmurray.game;


import java.util.Set;

public abstract class ChessPiece {
    protected final Position position;
    protected final Team team;

    public ChessPiece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract Set<Position> getValidMoves(GameState gameState);
}
