package io.github.samuelmurray.game;

import java.util.Map;
import java.util.Optional;

public class GameState {
    private final Map<Position, ChessPiece> positionToChessPiece;

    private GameState(Map<Position, ChessPiece> positionToChessPiece) {
        this.positionToChessPiece = positionToChessPiece;
    }

    static GameState of(Map<Position, ChessPiece> positionToChessPiece) {
        if (positionToChessPiece.containsKey(Position.OUT_OF_BOARD)) {
            throw new IllegalArgumentException("positionToChessPiece can't contain Position.OUT_OF_BOARD");
        }
        return new GameState(Map.copyOf(positionToChessPiece));
    }

    public Optional<ChessPiece> getPieceAt(Position position) {
        return Optional.ofNullable(positionToChessPiece.get(position));
    }
}
