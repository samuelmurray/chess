package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.function.UnaryOperator;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;

class MovementHelperTest {

    @Test
    void getPositionsInDirectionAndStopAtPieceHandlesOUT_OF_BOARD() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());

        assertThat(MovementHelper.getPositionsInDirectionAndStopAtPiece(OUT_OF_BOARD, emptyGameState, UnaryOperator.identity()))
                .isEmpty();
    }

    @Test
    void getPositionsInDirectionAndStopAtPieceStopsOnOUT_OF_BOARD() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());

        assertThat(MovementHelper.getPositionsInDirectionAndStopAtPiece(A1, emptyGameState, position -> OUT_OF_BOARD))
                .isEmpty();
    }

    @Test
    void getPositionsInDirectionAndStopAtPieceStopsAfterOtherPiece() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState emptyGameState = GameState.of(Map.of(B1, otherPiece));

        assertThat(MovementHelper.getPositionsInDirectionAndStopAtPiece(A1, emptyGameState, Position::right))
                .containsExactly(B1);
    }
}