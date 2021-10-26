package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;

class RookMovementTest {
    private final RookMovement rookMovement = new RookMovement();

    @Test
    void getMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(rookMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(A2, A3, A4, A5, A6, A7, A8, B1, C1, D1, E1, F1, G1, H1);
    }

    @Test
    void getMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(rookMovement.getPotentiallyValidMoves(C3, null, gameState))
                .containsExactlyInAnyOrder(A3, B3, D3, E3, F3, G3, H3, C1, C2, C4, C5, C6, C7, C8);
    }

    @Test
    void getMovesOtherPiecesBlock() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A3, otherPiece));

        assertThat(rookMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(A2, A3, B1, C1, D1, E1, F1, G1, H1);
    }
}