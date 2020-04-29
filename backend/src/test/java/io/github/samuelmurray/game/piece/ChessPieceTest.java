package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.ChessException;
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

class ChessPieceTest {
    private final ChessPiece noMovePiece = new ChessPiece(Team.BLACK, (currentPosition, currentTeam, gameState) -> Collections.emptySet());
    private final ChessPiece movePiece = new ChessPiece(Team.WHITE, (currentPosition, currentTeam, gameState) -> Set.of(A1, A2, A3));

    @Test
    void testGetValidMovesThrowsOnNull() {
        assertThrows(NullPointerException.class, () -> noMovePiece.getValidMoves(null));
    }

    @Test
    void testGetValidMovesThrowsOnMissingPiece() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());
        assertThrows(ChessException.class, () -> noMovePiece.getValidMoves(emptyGameState));
    }

    @Test
    void testGetValidMovesReturnsEmptySetForNoMoves() {
        GameState gameState = GameState.of(Map.of(A1, noMovePiece));
        var actual = noMovePiece.getValidMoves(gameState);
        assertEquals(Collections.emptySet(), actual);
    }

    @Test
    void testGetValidMovesReturnsPotentiallyValidMovesForEmptyBoard() {
        GameState gameState = GameState.of(Map.of(B2, movePiece));
        var actualMoves = movePiece.getValidMoves(gameState);
        Set<Position> expectedMoves = Set.of(A1, A2, A3);
        assertEquals(expectedMoves, actualMoves);
    }

    @Test
    void testGetValidMovesIsBlockedByFriendlyPiece() {
        ChessPiece friendlyPiece = new ChessPiece(Team.WHITE, null);
        GameState gameState = GameState.of(Map.of(B2, movePiece, A1, friendlyPiece));
        var actualMoves = movePiece.getValidMoves(gameState);
        Set<Position> expectedMoves = Set.of(A2, A3);
        assertEquals(expectedMoves, actualMoves);
    }

    @Test
    void testGetValidMovesIsNotBlockedByOpponentPiece() {
        GameState gameState = GameState.of(Map.of(B2, movePiece, A1, noMovePiece));
        var actualMoves = movePiece.getValidMoves(gameState);
        Set<Position> expectedMoves = Set.of(A1, A2, A3);
        assertEquals(expectedMoves, actualMoves);
    }

    @Test
    void testGetValidMovesFiltersCurrentPosition() {
        GameState gameState = GameState.of(Map.of(A2, movePiece));
        var actualMoves = movePiece.getValidMoves(gameState);
        Set<Position> expectedMoves = Set.of(A1, A3);
        assertEquals(expectedMoves, actualMoves);
    }
}