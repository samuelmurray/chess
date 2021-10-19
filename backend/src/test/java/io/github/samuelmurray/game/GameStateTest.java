package io.github.samuelmurray.game;

import io.github.samuelmurray.game.piece.ChessPiece;
import io.github.samuelmurray.game.piece.ChessPieceFactory;
import io.github.samuelmurray.game.piece.PieceType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.github.samuelmurray.game.Position.A1;
import static io.github.samuelmurray.game.Position.A2;
import static io.github.samuelmurray.game.Position.OUT_OF_BOARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateTest {
    private GameState gameState;
    private static final ChessPiece piece = ChessPieceFactory.createPiece(PieceType.PAWN, Team.BLACK);

    @Test
    void gameStateThrowsOnLargeBoardPositions() {
        Map<Position, ChessPiece> boardPositions = new HashMap<>();
        boardPositions.put(OUT_OF_BOARD, piece);
        assertThrows(IllegalArgumentException.class, () -> GameState.of(boardPositions));
    }

    @Test
    void hasPieceAtThrowsOnNull() {
        gameState = GameState.of(Collections.emptyMap());
        assertThrows(NullPointerException.class, () -> gameState.hasPieceAt(null));
    }

    @Test
    void hasPieceAtReturnFalseForNoPiece() {
        gameState = GameState.of(Collections.emptyMap());
        assertFalse(gameState.hasPieceAt(A1));
    }

    @Test
    void hasPieceAtReturnTrueForPiece() {
        gameState = GameState.of(Map.of(A1, piece));
        assertTrue(gameState.hasPieceAt(A1));
    }

    @Test
    void getPieceAtThrowsOnNull() {
        gameState = GameState.of(Collections.emptyMap());
        assertThrows(NullPointerException.class, () -> gameState.getPieceAt(null));
    }

    @Test
    void getPieceAtReturnsEmptyOptionalForUnoccupiedPosition() {
        gameState = GameState.of(Collections.emptyMap());
        var maybeChessPiece = gameState.getPieceAt(A1);
        assertTrue(maybeChessPiece.isEmpty());
    }

    @Test
    void getPieceAtReturnsCorrectPiece() {
        gameState = GameState.of(Map.of(A1, piece));
        var maybeChessPiece = gameState.getPieceAt(A1);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(piece, maybeChessPiece.get());
    }

    @Test
    void getPositionOfPieceReturnsEmptyOptionalForNull() {
        gameState = GameState.of(Collections.emptyMap());
        var maybePosition = gameState.getPositionOfPiece(null);
        assertTrue(maybePosition.isEmpty());
    }

    @Test
    void getPositionOfPieceReturnsEmptyOptionalForMissingPiece() {
        gameState = GameState.of(Collections.emptyMap());
        var maybePosition = gameState.getPositionOfPiece(piece);
        assertTrue(maybePosition.isEmpty());
    }

    @Test
    void getPositionOfPieceReturnsCorrectPosition() {
        gameState = GameState.of(Map.of(A1, piece));
        var maybeChessPiece = gameState.getPositionOfPiece(piece);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(A1, maybeChessPiece.get());
    }

    @Test
    void getPositionOfPieceReturnsPositionOfCorrectPiece() {
        ChessPiece otherPiece = ChessPieceFactory.createPiece(PieceType.PAWN, Team.BLACK);
        gameState = GameState.of(Map.of(A1, piece, A2, otherPiece));
        var maybeChessPiece = gameState.getPositionOfPiece(piece);
        assertTrue(maybeChessPiece.isPresent());
        assertEquals(A1, maybeChessPiece.get());
    }
}