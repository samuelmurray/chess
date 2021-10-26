package io.github.samuelmurray.game;

import io.github.samuelmurray.game.piece.ChessPiece;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GameState {
    private final Map<Position, ChessPiece> gameBoard;

    private GameState(Map<Position, ChessPiece> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public static GameState of(Map<Position, ChessPiece> gameBoard) {
        if (gameBoard.containsKey(Position.OUT_OF_BOARD)) {
            throw new IllegalArgumentException("gameBoard can't contain Position.OUT_OF_BOARD");
        }
        return new GameState(Map.copyOf(gameBoard));
    }

    public boolean hasPieceAt(Position position) {
        return gameBoard.containsKey(position);
    }

    public Optional<ChessPiece> getPieceAt(Position position) {
        return Optional.ofNullable(gameBoard.get(position));
    }

    public Optional<Position> getPositionOfPiece(ChessPiece chessPiece) {
        Objects.requireNonNull(chessPiece);
        return gameBoard.entrySet().stream()
                .filter(e -> e.getValue() == chessPiece)
                .map(Map.Entry::getKey)
                .findAny();
    }
}
