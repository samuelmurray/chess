package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.github.samuelmurray.game.Position.A1;
import static io.github.samuelmurray.game.Position.B1;
import static io.github.samuelmurray.game.Position.OUT_OF_BOARD;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MovementHelperTest {

    @Test
    void getPositionsInDirectionAndStopAtPieceHandlesOUT_OF_BOARD() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());
        var actual = MovementHelper.getPositionsInDirectionAndStopAtPiece(OUT_OF_BOARD, emptyGameState, UnaryOperator.identity());
        assertEquals(Collections.emptySet(), actual);
    }

    @Test
    void getPositionsInDirectionAndStopAtPieceStopsOnOUT_OF_BOARD() {
        GameState emptyGameState = GameState.of(Collections.emptyMap());
        var actual = MovementHelper.getPositionsInDirectionAndStopAtPiece(A1, emptyGameState, position -> OUT_OF_BOARD);
        assertEquals(Collections.emptySet(), actual);
    }

    @Test
    void getPositionsInDirectionAndStopAtPieceStopsAfterOtherPiece() {
        ChessPiece otherPiece = DummyPieceFactory.createPiece();
        GameState emptyGameState = GameState.of(Map.of(B1, otherPiece));
        var actual = MovementHelper.getPositionsInDirectionAndStopAtPiece(A1, emptyGameState, Position::getRight);
        assertEquals(Set.of(B1), actual);
    }
}