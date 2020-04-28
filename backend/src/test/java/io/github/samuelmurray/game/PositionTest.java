package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;

import static io.github.samuelmurray.game.Position.*;
import static io.github.samuelmurray.game.Position.A1;
import static io.github.samuelmurray.game.Position.OUT_OF_BOARD;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void testPositionHas65Entries() {
        assertEquals(65, values().length);
    }

    @Test
    void testA1IsBelowB1() {
        assertEquals(A1, B1.getBelow());
    }

    @Test
    void testB1IsAboveA1() {
        assertEquals(B1, A1.getAbove());
    }

    @Test
    void testA2IsRightOfA1() {
        assertEquals(A2, A1.getRight());
    }

    @Test
    void testA1IsLeftOfA2() {
        assertEquals(A1, A2.getLeft());
    }

    @Test
    void testA1HasNothingBelow() {
        assertEquals(OUT_OF_BOARD, A1.getBelow());
    }

    @Test
    void testA1HasNothingLeft() {
        assertEquals(OUT_OF_BOARD, A1.getLeft());
    }

    @Test
    void testH8HasNothingAbove() {
        assertEquals(OUT_OF_BOARD, H8.getAbove());
    }

    @Test
    void testH8HasNothingRight() {
        assertEquals(OUT_OF_BOARD, H8.getRight());
    }

    @Test
    void testOUT_OF_BOARDHasNoNeighbours() {
        assertAll(
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getAbove()),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getBelow()),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getRight()),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.getRight())
        );
    }

    @Test
    void testRelationsAreAntiSymmetrical() {
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