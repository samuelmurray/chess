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
import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnMovementTest {

    private final PawnMovement pawnMovement = new PawnMovement();

    @Test
    void testGetPotentiallyValidMovesThrowsOnNullTeam() {
        GameState gameState = GameState.of(Collections.emptyMap());
        assertThrows(NullPointerException.class, () -> pawnMovement.getPotentiallyValidMoves(A1, null, gameState));
    }

    @Test
    void testGetMovesInSideOpening() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A3, A4, B3);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetMovesInMiddleOpening() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(B2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(B3, B4, A3, C3);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(B3, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A4, B4, C4);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetMovesOtherPiecesBlockForwardInOpening() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A3, otherPiece));
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(B3);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetMovesOtherPiecesBlockForwardInLongOpening() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A4, otherPiece));
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A3, B3);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovedOtherPiecesDoNotBlockCapturingMove() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(B3, otherPiece));
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A3, A4, B3);
        assertEquals(expected, validMoves);
    }
}