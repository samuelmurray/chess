package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Team;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnMovementTest {

    private final PawnMovement pawnMovement = new PawnMovement();

    @Test
    void getPotentiallyValidMovesThrowsOnNullTeam() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThatThrownBy(() -> pawnMovement.getPotentiallyValidMoves(A1, null, gameState))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getMovesInSideOpening() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState))
                .containsExactlyInAnyOrder(A3, A4, B3);
    }

    @Test
    void getMovesInMiddleOpening() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(pawnMovement.getPotentiallyValidMoves(B2, Team.WHITE, gameState))
                .containsExactlyInAnyOrder(B3, B4, A3, C3);
    }

    @Test
    void getMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(pawnMovement.getPotentiallyValidMoves(B3, Team.WHITE, gameState))
                .containsExactlyInAnyOrder(A4, B4, C4);
    }

    @Test
    void getMovesOtherPiecesBlockForwardInOpening() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A3, otherPiece));

        assertThat(pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState))
                .containsExactlyInAnyOrder(B3);
    }

    @Test
    void getMovesOtherPiecesBlockForwardInLongOpening() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(A4, otherPiece));

        assertThat(pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState))
                .containsExactlyInAnyOrder(A3, B3);
    }

    @Test
    void getValidMovedOtherPiecesDoNotBlockCapturingMove() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState gameState = GameState.of(Map.of(B3, otherPiece));

        assertThat(pawnMovement.getPotentiallyValidMoves(A2, Team.WHITE, gameState))
                .containsExactlyInAnyOrder(A3, A4, B3);
    }

    @Test
    void blackPawnMovesDown() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(pawnMovement.getPotentiallyValidMoves(A2, Team.BLACK, gameState))
                .containsExactlyInAnyOrder(A1, B1);
    }

    @Test
    void blackPawnStartsAt7() {
        GameState gameState = GameState.of(Collections.emptyMap());

        assertThat(pawnMovement.getPotentiallyValidMoves(A7, Team.BLACK, gameState))
                .containsExactlyInAnyOrder(A6, A5, B6);
    }
}