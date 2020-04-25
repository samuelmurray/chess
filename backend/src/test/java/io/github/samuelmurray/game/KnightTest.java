package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KnightTest {
    private final Knight knight = new Knight(Team.BLACK);

    @Test
    void testGetValidMovesThrowsOnNull() {
        assertThrows(NullPointerException.class, () -> knight.getValidMoves(null));
    }

    @Test
    void testGetValidMovesThrowsOnMissingPiece() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());
        assertThrows(ChessException.class, () -> knight.getValidMoves(emptyGameState));
    }

    @Test
    void testGetValidMovesInCorner() {
        GameState gameState = GameState.of(Map.of(Position.A1, knight));
        Set<Position> validMoves = knight.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.B3, Position.C2);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesInMiddle() {
        GameState gameState = GameState.of(Map.of(Position.C3, knight));
        Set<Position> validMoves = knight.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.A2, Position.A4, Position.B1, Position.B5,
                Position.D1, Position.D5, Position.E2, Position.E4);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesFriendlyPieceBlocks() {
        King friendlyPiece = new King(Team.BLACK);
        GameState gameState = GameState.of(Map.of(Position.A1, knight, Position.B3, friendlyPiece));
        Set<Position> validMoves = knight.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.C2);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesEnemyPieceDoesNotBlock() {
        King enemyPiece = new King(Team.WHITE);
        GameState gameState = GameState.of(Map.of(Position.A1, knight, Position.B3, enemyPiece));
        Set<Position> validMoves = knight.getValidMoves(gameState);
        Set<Position> expected = Set.of(Position.B3, Position.C2);
        assertEquals(expected, validMoves);
    }
}