package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.samuelmurray.game.Position.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RookMovementTest {
    private final RookMovement rookMovement = new RookMovement();

    @Test
    void testGetValidMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = rookMovement.getPotentiallyValidMoves(A1, gameState);
        Set<Position> expected = Set.of(A2, A3, A4, A5, A6, A7, A8, B1, C1, D1, E1, F1, G1, H1);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = rookMovement.getPotentiallyValidMoves(C3, gameState);
        Set<Position> expected = Set.of(A3, B3, D3, E3, F3, G3, H3, C1, C2, C4, C5, C6, C7, C8);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesOtherPiecesBlock() {
        ChessPiece otherPiece = new ChessPiece(Team.BLACK, null);
        GameState gameState = GameState.of(Map.of(A3, otherPiece));
        Set<Position> validMoves = rookMovement.getPotentiallyValidMoves(A1, gameState);
        Set<Position> expected = Set.of(A2, A3, B1, C1, D1, E1, F1, G1, H1);
        assertEquals(expected, validMoves);
    }
}