package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.ChessException;
import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Team;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessPieceTest {
    private final ChessPiece noMovePiece = new ChessPiece(Team.BLACK, null, (currentPosition, currentTeam, gameState) -> Collections.emptySet());
    private final ChessPiece movePiece = new ChessPiece(Team.WHITE, null, (currentPosition, currentTeam, gameState) -> Set.of(A1, A2, A3));

    @Test
    void getValidMovesThrowsOnNull() {
        assertThatThrownBy(() -> noMovePiece.getValidMoves(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getValidMovesThrowsOnMissingPiece() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());

        assertThatThrownBy(() -> noMovePiece.getValidMoves(emptyGameState))
                .isInstanceOf(ChessException.class);
    }

    @Test
    void getValidMovesReturnsEmptySetForNoMoves() {
        GameState gameState = GameState.of(Map.of(A1, noMovePiece));

        assertThat(noMovePiece.getValidMoves(gameState))
                .isEmpty();
    }

    @Test
    void getValidMovesReturnsPotentiallyValidMovesForEmptyBoard() {
        GameState gameState = GameState.of(Map.of(B2, movePiece));

        assertThat(movePiece.getValidMoves(gameState))
                .containsExactlyInAnyOrder(A1, A2, A3);
    }

    @Test
    void getValidMovesIsBlockedByFriendlyPiece() {
        ChessPiece friendlyPiece = new ChessPiece(Team.WHITE, null);
        GameState gameState = GameState.of(Map.of(B2, movePiece, A1, friendlyPiece));

        assertThat(movePiece.getValidMoves(gameState))
                .containsExactlyInAnyOrder(A2, A3);
    }

    @Test
    void getValidMovesIsNotBlockedByOpponentPiece() {
        GameState gameState = GameState.of(Map.of(B2, movePiece, A1, noMovePiece));

        assertThat(movePiece.getValidMoves(gameState))
                .containsExactlyInAnyOrder(A1, A2, A3);
    }

    @Test
    void getValidMovesFiltersCurrentPosition() {
        GameState gameState = GameState.of(Map.of(A2, movePiece));

        assertThat(movePiece.getValidMoves(gameState))
                .containsExactlyInAnyOrder(A1, A3);
    }
}