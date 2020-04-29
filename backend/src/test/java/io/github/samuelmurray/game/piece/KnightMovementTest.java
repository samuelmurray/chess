package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.samuelmurray.game.Position.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KnightMovementTest {
    private final KnightMovement knightMovement = new KnightMovement();


    @Test
    void testGetValidMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = knightMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(B3, C2);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = knightMovement.getPotentiallyValidMoves(C3, null, gameState);
        Set<Position> expected = Set.of(A2, A4, B1, B5, D1, D5, E2, E4);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesOtherPiecesDoNotBlock() {
        ChessPiece otherPiece = new ChessPiece(null, null);
        GameState gameState = GameState.of(Map.of(A2, otherPiece, A3, otherPiece, B2, otherPiece));
        Set<Position> validMoves = knightMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(C2, B3);
        assertEquals(expected, validMoves);
    }
}