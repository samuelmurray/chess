package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {

    private final King king = new King(Team.BLACK);

    @Test
    void testGetValidMovesThrowsOnNull() {
        assertThrows(NullPointerException.class, () -> king.getValidMoves(null));
    }

    @Test
    void testGetValidMovesThrowsOnMissingPiece() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());
        assertThrows(ChessException.class, () -> king.getValidMoves(emptyGameState));
    }

    @Test
    void testGetValidMovesInCorner() {
        GameState gameState = GameState.of(Map.of(Position.A1, king));
        Set<Position> validMoves = king.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.A2, Position.B1, Position.B2);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesInMiddle() {
        GameState gameState = GameState.of(Map.of(Position.B2, king));
        Set<Position> validMoves = king.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.A1, Position.A2, Position.A3, Position.B1, Position.B3,
                Position.C1, Position.C2, Position.C3);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesFriendlyPieceBlocks() {
        King friendlyPiece = new King(Team.BLACK);
        GameState gameState = GameState.of(Map.of(Position.A1, king, Position.A2, friendlyPiece));
        Set<Position> validMoves = king.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.B1, Position.B2);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesEnemyPieceDoesNotBlock() {
        King enemyPiece = new King(Team.WHITE);
        GameState gameState = GameState.of(Map.of(Position.A1, king, Position.A2, enemyPiece));
        Set<Position> validMoves = king.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.A2, Position.B1, Position.B2);
        assertEquals(expected, validMoves);
    }
}