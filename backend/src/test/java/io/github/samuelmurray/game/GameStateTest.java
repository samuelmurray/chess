package io.github.samuelmurray.game;

import io.github.samuelmurray.game.piece.ChessPiece;
import io.github.samuelmurray.game.piece.ChessPieceFactory;
import io.github.samuelmurray.game.piece.PieceType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameStateTest {
    private GameState gameState;
    private static final ChessPiece piece = ChessPieceFactory.createPiece(PieceType.PAWN, Team.BLACK);

    @Test
    void gameStateThrowsOnLargeBoardPositions() {
        Map<Position, ChessPiece> boardPositions = Map.of(OUT_OF_BOARD, piece);

        assertThatThrownBy(() -> GameState.of(boardPositions))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void hasPieceAtThrowsOnNull() {
        gameState = GameState.of(Collections.emptyMap());

        assertThatThrownBy(() -> gameState.hasPieceAt(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void hasPieceAtReturnFalseForNoPiece() {
        gameState = GameState.of(Collections.emptyMap());

        assertThat(gameState.hasPieceAt(A1))
                .isFalse();
    }

    @Test
    void hasPieceAtReturnTrueForPiece() {
        gameState = GameState.of(Map.of(A1, piece));

        assertThat(gameState.hasPieceAt(A1))
                .isTrue();
    }

    @Test
    void getPieceAtThrowsOnNull() {
        gameState = GameState.of(Collections.emptyMap());

        assertThatThrownBy(() -> gameState.getPieceAt(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getPieceAtReturnsEmptyOptionalForUnoccupiedPosition() {
        gameState = GameState.of(Collections.emptyMap());

        assertThat(gameState.getPieceAt(A1))
                .isEmpty();
    }

    @Test
    void getPieceAtReturnsCorrectPiece() {
        gameState = GameState.of(Map.of(A1, piece));

        assertThat(gameState.getPieceAt(A1))
                .get()
                .isEqualTo(piece);
    }

    @Test
    void getPositionOfPieceThrowsOnNull() {
        gameState = GameState.of(Collections.emptyMap());

        assertThatThrownBy(() -> gameState.getPositionOfPiece(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getPositionOfPieceReturnsEmptyOptionalForMissingPiece() {
        gameState = GameState.of(Collections.emptyMap());

        assertThat(gameState.getPositionOfPiece(piece))
                .isEmpty();
    }

    @Test
    void getPositionOfPieceReturnsCorrectPosition() {
        gameState = GameState.of(Map.of(A1, piece));

        assertThat(gameState.getPositionOfPiece(piece))
                .get()
                .isEqualTo(A1);
    }

    @Test
    void getPositionOfPieceReturnsPositionOfCorrectPiece() {
        ChessPiece otherPiece = ChessPieceFactory.createPiece(PieceType.PAWN, Team.BLACK);
        gameState = GameState.of(Map.of(A1, piece, A2, otherPiece));

        assertThat(gameState.getPositionOfPiece(piece))
                .get()
                .isEqualTo(A1);
    }
}