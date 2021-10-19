package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.samuelmurray.game.Position.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BishopMovementTest {
    private final BishopMovement bishopMovement = new BishopMovement();

    @Test
    void getMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = bishopMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(B2, C3, D4, E5, F6, G7, H8);
        assertEquals(expected, validMoves);
    }

    @Test
    void getMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = bishopMovement.getPotentiallyValidMoves(B3, null, gameState);
        Set<Position> expected = Set.of(A2, A4, C2, D1, C4, D5, E6, F7, G8);
        assertEquals(expected, validMoves);
    }

    @Test
    void getMovesOtherPiecesBlock() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(C3, otherPiece));
        Set<Position> validMoves = bishopMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(B2, C3);
        assertEquals(expected, validMoves);
    }
}