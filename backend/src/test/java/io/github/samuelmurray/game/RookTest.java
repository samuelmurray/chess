package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static io.github.samuelmurray.game.Position.*;
import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    private final Rook rook = new Rook(Team.BLACK);

    @Test
    void testGetValidMovesInCorner() {
        GameState gameState = GameState.of(Map.of(A1, rook));
        Set<Position> validMoves = rook.getValidMoves(gameState);
        Set<Position> expected = Set.of(A2, A3, A4, A5, A6, A7, A8, B1, C1, D1, E1, F1, G1, H1);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesInMiddle() {
        GameState gameState = GameState.of(Map.of(C3, rook));
        Set<Position> validMoves = rook.getValidMoves(gameState);
        Set<Position> expected = Set.of(A3, B3, D3, E3, F3, G3, H3, C1, C2, C4, C5, C6, C7, C8);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesFriendlyPieceBlocks() {
        King friendlyPiece = new King(Team.BLACK);
        GameState gameState = GameState.of(Map.of(A1, rook, A3, friendlyPiece));
        Set<Position> validMoves = rook.getValidMoves(gameState);
        Set<Position> expected = Set.of(A2, B1, C1, D1, E1, F1, G1, H1);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesEnemyPieceDoesNotBlock() {
        King enemyPiece = new King(Team.WHITE);
        GameState gameState = GameState.of(Map.of(A1, rook, A3, enemyPiece));
        Set<Position> validMoves = rook.getValidMoves(gameState);
        Set<Position> expected = Set.of(A2, A3, B1, C1, D1, E1, F1, G1, H1);
        assertEquals(expected, validMoves);
    }
}