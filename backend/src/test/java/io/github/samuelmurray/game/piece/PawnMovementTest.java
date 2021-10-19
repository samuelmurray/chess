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
    void getPotentiallyValidMovesThrowsOnNullTeam() {
        GameState gameState = GameState.of(Collections.emptyMap());
        assertThrows(NullPointerException.class, () -> pawnMovement.getPotentiallyValidMoves(A1, null, gameState));
    }

    @Test
    void getMovesInSideOpening() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A3, A4, B3);
        assertEquals(expected, validMoves);
    }

    @Test
    void getMovesInMiddleOpening() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(B2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(B3, B4, A3, C3);
        assertEquals(expected, validMoves);
    }

    @Test
    void getMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(B3, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A4, B4, C4);
        assertEquals(expected, validMoves);
    }

    @Test
    void getMovesOtherPiecesBlockForwardInOpening() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A3, otherPiece));
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(B3);
        assertEquals(expected, validMoves);
    }

    @Test
    void getMovesOtherPiecesBlockForwardInLongOpening() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A4, otherPiece));
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A3, B3);
        assertEquals(expected, validMoves);
    }

    @Test
    void getValidMovedOtherPiecesDoNotBlockCapturingMove() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(B3, otherPiece));
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState);
        Set<Position> expected = Set.of(A3, A4, B3);
        assertEquals(expected, validMoves);
    }

    @Test
    void blackPawnMovesDown() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A2, Team.BLACK, gameState);
        Set<Position> expected = Set.of(A1, B1);
        assertEquals(expected, validMoves);
    }

    @Test
    void blackPawnStartsAt7() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = pawnMovement.getPotentiallyValidMoves(A7, Team.BLACK, gameState);
        Set<Position> expected = Set.of(A6, A5, B6);
        assertEquals(expected, validMoves);
    }
}