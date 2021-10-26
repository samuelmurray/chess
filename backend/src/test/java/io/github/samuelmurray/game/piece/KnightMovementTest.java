package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightMovementTest {
    private final KnightMovement knightMovement = new KnightMovement();


    @Test
    void getMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(knightMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(B3, C2);
    }

    @Test
    void getMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(knightMovement.getPotentiallyValidMoves(C3, null, gameState))
                .containsExactlyInAnyOrder(A2, A4, B1, B5, D1, D5, E2, E4);
    }

    @Test
    void getMovesOtherPiecesDoNotBlock() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A2, otherPiece, A3, otherPiece, B2, otherPiece));

        assertThat(knightMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(C2, B3);
    }
}