package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static io.github.samuelmurray.game.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertThat(A2.down())
                .isEqualTo(A1);
    }

    @Test
    void a2IsAboveA1() {
        assertThat(A1.up())
                .isEqualTo(A2);
    }

    @Test
    void b1IsRightOfA1() {
        assertThat(A1.right())
                .isEqualTo(B1);
    }

    @Test
    void a1IsLeftOfB1() {
        assertThat(B1.left())
                .isEqualTo(A1);
    }

    @Test
    void a1HasNothingBelow() {
        assertThat(A1.down())
                .isEqualTo(OUT_OF_BOARD);
    }

    @Test
    void a1HasNothingLeft() {
        assertThat(A1.left())
                .isEqualTo(OUT_OF_BOARD);
    }

    @Test
    void h8HasNothingAbove() {
        assertThat(H8.up())
                .isEqualTo(OUT_OF_BOARD);
    }

    @Test
    void h8HasNothingRight() {
        assertThat(H8.right())
                .isEqualTo(OUT_OF_BOARD);
    }

    @Test
    void OUT_OF_BOARD_HasNoNeighbours() {
        assertThat(OUT_OF_BOARD)
                .extracting(Position::up, Position::down, Position::right, Position::left)
                .containsExactly(OUT_OF_BOARD, OUT_OF_BOARD, OUT_OF_BOARD, OUT_OF_BOARD);
    }

    @ParameterizedTest
    @EnumSource(Position.class)
    void relationsAreAntiSymmetrical(Position position) {
        if (position.up() != OUT_OF_BOARD) {
            assertThat(position.up().down())
                    .isEqualTo(position);
        }
        if (position.down() != OUT_OF_BOARD) {
            assertThat(position.down().up())
                    .isEqualTo(position);
        }
        if (position.right() != OUT_OF_BOARD) {
            assertThat(position.right().left())
                    .isEqualTo(position);
        }
        if (position.left() != OUT_OF_BOARD) {
            assertThat(position.left().right())
                    .isEqualTo(position);
        }
    }
}