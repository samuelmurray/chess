package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;

class KingMovementTest {

    private final KingMovement kingMovement = new KingMovement();

    @Test
    void getMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(kingMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(A2, B1, B2);
    }

    @Test
    void getMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(kingMovement.getPotentiallyValidMoves(B2, null, gameState))
                .containsExactlyInAnyOrder(A1, A2, A3, B1, B3, C1, C2, C3);
    }

    @Test
    void getMovesOtherPiecesDoNotBlock() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A2, otherPiece));

        assertThat(kingMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(A2, B1, B2);
    }
}