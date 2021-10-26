package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;

class BishopMovementTest {
    private final BishopMovement bishopMovement = new BishopMovement();

    @Test
    void getMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(bishopMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(B2, C3, D4, E5, F6, G7, H8);
    }

    @Test
    void getMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(bishopMovement.getPotentiallyValidMoves(B3, null, gameState))
                .containsExactlyInAnyOrder(A2, A4, C2, D1, C4, D5, E6, F7, G8);
    }

    @Test
    void getMovesOtherPiecesBlock() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(C3, otherPiece));

        assertThat(bishopMovement.getPotentiallyValidMoves(A1, null, gameState))
                .containsExactlyInAnyOrder(B2, C3);
    }
}