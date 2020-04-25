package io.github.samuelmurray.game;

import org.junit.jupiter.api.Test;

import static io.github.samuelmurray.game.Position.OUT_OF_BOARD;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PositionTest {

    @Test
    void testPositionHas65Entries() {
        assertEquals(65, Position.values().length);
    }

    @Test
    void testA1IsBelowB1() {
        assertEquals(Position.A1, Position.B1.below);
    }

    @Test
    void testB1IsAboveA1() {
        assertEquals(Position.B1, Position.A1.above);
    }

    @Test
    void testA2IsRightOfA1() {
        assertEquals(Position.A2, Position.A1.right);
    }

    @Test
    void testA1IsLeftOfA2() {
        assertEquals(Position.A1, Position.A2.left);
    }

    @Test
    void testA1HasNothingBelow() {
        assertNull(Position.A1.below);
    }

    @Test
    void testA1HasNothingLeft() {
        assertNull(Position.A1.left);
    }

    @Test
    void testH8HasNothingAbove() {
        assertNull(Position.H8.above);
    }

    @Test
    void testH8HasNothingRight() {
        assertNull(Position.H8.right);
    }

    @Test
    void testOUT_OF_BOARDHasNoNeighbours() {
        assertAll(
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.above),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.below),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.right),
                () -> assertEquals(OUT_OF_BOARD, OUT_OF_BOARD.left)
        );
    }

    @Test
    void testRelationsAreAntiSymmetrical() {
        for (var position : Position.values()) {
            if (position.above != null && position.above != OUT_OF_BOARD) {
                assertEquals(position, position.above.below);
            }
            if (position.below != null && position.below != OUT_OF_BOARD) {
                assertEquals(position, position.below.above);
            }
            if (position.right != null && position.right != OUT_OF_BOARD) {
                assertEquals(position, position.right.left);
            }
            if (position.left != null && position.left != OUT_OF_BOARD) {
                assertEquals(position, position.left.right);
            }
        }
    }
}