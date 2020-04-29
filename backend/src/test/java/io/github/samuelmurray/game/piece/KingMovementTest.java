package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.samuelmurray.game.Position.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KingMovementTest {

    private final KingMovement kingMovement = new KingMovement();

    @Test
    void testGetValidMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = kingMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(A2, B1, B2);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = kingMovement.getPotentiallyValidMoves(B2, null, gameState);
        Set<Position> expected = Set.of(A1, A2, A3, B1, B3, C1, C2, C3);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesOtherPiecesDoNotBlock() {
        ChessPiece otherPiece = new ChessPiece(null, null);
        GameState gameState = GameState.of(Map.of(A2, otherPiece));
        Set<Position> validMoves = kingMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(A2, B1, B2);
        assertEquals(expected, validMoves);
    }
}