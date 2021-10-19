package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.stream.Stream;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionTest {

    @Test
    void positionHas65Entries() {
        assertEquals(65, values().length);
    }

    @ParameterizedTest
    @EnumSource(value = Position.class, names = "OUT_OF_BOARD", mode = EnumSource.Mode.EXCLUDE)
    void allPositionsAreOnBoard(Position position) {
        assertThat(position.isOnBoard())
                .isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Position.class, names = "OUT_OF_BOARD", mode = EnumSource.Mode.INCLUDE)
    void OUT_OF_BOARD_IsNotOnBoard(Position position) {
        assertThat(position.isOnBoard())
                .isFalse();
    }

    @Test
    void a1IsBelowA2() {
        assertEquals(A1, A2.getBelow());
    }

    @Test
    void a2IsAboveA1() {
        assertEquals(A2, A1.getAbove());
    }

    @Test
    void b1IsRightOfA1() {
        assertEquals(B1, A1.getRight());
    }

    @Test
    void a1IsLeftOfB1() {
        assertEquals(A1, B1.getLeft());
    }

    @Test
    void a1HasNothingBelow() {
        assertEquals(OUT_OF_BOARD, A1.getBelow());
    }

    @Test
    void a1HasNothingLeft() {
        assertEquals(OUT_OF_BOARD, A1.getLeft());
    }

    @Test
    void h8HasNothingAbove() {
        assertEquals(OUT_OF_BOARD, H8.getAbove());
    }

    @Test
    void h8HasNothingRight() {
        assertEquals(OUT_OF_BOARD, H8.getRight());
    }

    @Test
    void OUT_OF_BOARD_HasNoNeighbours() {
        assertAll(
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getAbove()),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getBelow()),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getRight()),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getRight())
        );
    }

    @Test
    void relationsAreAntiSymmetrical() {
        for (var position : values()) {
            if (position.getAbove() != null && position.getAbove() != OUT_OF_BOARD) {
                assertEquals(position, position.getAbove().getBelow());
            }
            if (position.getBelow() != null && position.getBelow() != OUT_OF_BOARD) {
                assertEquals(position, position.getBelow().getAbove());
            }
            if (position.getRight() != null && position.getRight() != OUT_OF_BOARD) {
                assertEquals(position, position.getRight().getLeft());
            }
            if (position.getLeft() != null && position.getLeft() != OUT_OF_BOARD) {
                assertEquals(position, position.getLeft().getRight());
            }
        }
    }
}