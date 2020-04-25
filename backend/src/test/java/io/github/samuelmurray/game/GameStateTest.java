package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    GameState gameState;

    @Test
    void testGameStateThrowsOnLargeBoardPositions() {
        Map<Position, ChessPiece> boardPositions = new HashMap<>();
        boardPositions.put(Position.OUT_OF_BOARD, DummyChessPiece.instance);
        assertThrows(IllegalArgumentException.class, () -> GameState.of(boardPositions));
    }

    @Test
    void testGetPieceAtThrowsOnNull() {
        gameState = GameState.of(Collections.emptyMap());
        assertThrows(NullPointerException.class, () -> gameState.getPieceAt(null));
    }

    @Test
    void testGetPieceAtReturnsEmptyOptionalForUnoccupiedPosition() {
        gameState = GameState.of(Collections.emptyMap());
        var maybeChessPiece = gameState.getPieceAt(Position.A1);
        assertTrue(maybeChessPiece.isEmpty());
    }

    @Test
    void testGetPieceAtReturnsPiece() {
        gameState = GameState.of(Map.of(Position.A1, DummyChessPiece.instance));
        var maybeChessPiece = gameState.getPieceAt(Position.A1);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(DummyChessPiece.instance, maybeChessPiece.get());
    }

    private static class DummyChessPiece extends ChessPiece {
        private static final DummyChessPiece instance = new DummyChessPiece();

        private DummyChessPiece() {
            super(Position.A1, Team.BLACK);
        }

        @Override
        public Set<Position> getValidMoves(GameState gameState) {
            return null;
        }
    }
}