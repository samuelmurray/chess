package io.github.samuelmurray.game;

import io.github.samuelmurray.game.piece.ChessPiece;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.github.samuelmurray.game.Position.A1;
import static io.github.samuelmurray.game.Position.OUT_OF_BOARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateTest {
    private GameState gameState;
    private static final ChessPiece piece = new ChessPiece(Team.BLACK, null);

    @Test
    void testGameStateThrowsOnLargeBoardPositions() {
        Map<Position, ChessPiece> boardPositions = new HashMap<>();
        boardPositions.put(OUT_OF_BOARD, piece);
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
        var maybeChessPiece = gameState.getPieceAt(A1);
        assertTrue(maybeChessPiece.isEmpty());
    }

    @Test
    void testGetPieceAtReturnsCorrectPiece() {
        gameState = GameState.of(Map.of(A1, piece));
        var maybeChessPiece = gameState.getPieceAt(A1);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(piece, maybeChessPiece.get());
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
        var maybePosition = gameState.getPositionOfPiece(piece);
        assertTrue(maybePosition.isEmpty());
    }

    @Test
    void testGetPositionOfPieceReturnsCorrectPosition() {
        gameState = GameState.of(Map.of(A1, piece));
        var maybeChessPiece = gameState.getPositionOfPiece(piece);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(A1, maybeChessPiece.get());
    }
}