package io.github.samuelmurray.game;

import io.github.samuelmurray.game.piece.ChessPiece;

import java.util.Map;
import java.util.Optional;

public class GameState {
    private final Map<Position, ChessPiece> positionToChessPiece;

    private GameState(Map<Position, ChessPiece> positionToChessPiece) {
        this.positionToChessPiece = positionToChessPiece;
    }

    public static GameState of(Map<Position, ChessPiece> positionToChessPiece) {
        if (positionToChessPiece.containsKey(Position.OUT_OF_BOARD)) {
            throw new ChessException("positionToChessPiece can't contain Position.OUT_OF_BOARD");
        }
        return new GameState(Map.copyOf(positionToChessPiece));
    }

    public boolean hasPieceAt(Position position) {
        return positionToChessPiece.containsKey(position);
    }

    public Optional<ChessPiece> getPieceAt(Position position) {
        return Optional.ofNullable(positionToChessPiece.get(position));
    }

    public Optional<Position> getPositionOfPiece(ChessPiece chessPiece) {
        return positionToChessPiece.entrySet().stream()
                .filter(e -> e.getValue() == chessPiece)
                .map(Map.Entry::getKey)
                .findAny();
    }
}
