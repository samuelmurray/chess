package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateTest {
    private GameState gameState;

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
    void testGetPieceAtReturnsCorrectPiece() {
        gameState = GameState.of(Map.of(Position.A1, DummyChessPiece.instance));
        var maybeChessPiece = gameState.getPieceAt(Position.A1);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(DummyChessPiece.instance, maybeChessPiece.get());
    }

    @Test
    void testGetPositionOfPieceReturnsEmptyOptionalForNull() {
        gameState = GameState.of(Collections.emptyMap());
        var maybePosition = gameState.getPositionOfPiece(null);
        assertTrue(maybePosition.isEmpty());
    }

    @Test
    void testGetPositionOfPieceReturnsEmptyOptionalForMissingPiece() {
        gameState = GameState.of(Collections.emptyMap());
        var maybePosition = gameState.getPositionOfPiece(DummyChessPiece.instance);
        assertTrue(maybePosition.isEmpty());
    }

    @Test
    void testGetPositionOfPieceReturnsCorrectPosition() {
        gameState = GameState.of(Map.of(Position.A1, DummyChessPiece.instance));
        var maybeChessPiece = gameState.getPositionOfPiece(DummyChessPiece.instance);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(Position.A1, maybeChessPiece.get());
    }

    private static class DummyChessPiece extends ChessPiece {
        private static final DummyChessPiece instance = new DummyChessPiece();

        private DummyChessPiece() {
            super(Team.BLACK);
        }

        @Override
        protected Set<Position> getPotentiallyValidMoves(Position currentPosition, GameState gameState) {
            return null;
        }
    }
}