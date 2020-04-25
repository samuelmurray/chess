package io.github.samuelmurray.game;


import java.util.Set;

public abstract class ChessPiece {
    protected final Team team;

    public ChessPiece(Team team) {
        this.team = team;
    }

    public abstract Set<Position> getValidMoves(GameState gameState);
}
