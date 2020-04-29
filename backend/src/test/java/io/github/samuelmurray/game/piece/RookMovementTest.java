package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.samuelmurray.game.Position.A1;
import static io.github.samuelmurray.game.Position.A2;
import static io.github.samuelmurray.game.Position.A3;
import static io.github.samuelmurray.game.Position.A4;
import static io.github.samuelmurray.game.Position.A5;
import static io.github.samuelmurray.game.Position.A6;
import static io.github.samuelmurray.game.Position.A7;
import static io.github.samuelmurray.game.Position.A8;
import static io.github.samuelmurray.game.Position.B1;
import static io.github.samuelmurray.game.Position.B3;
import static io.github.samuelmurray.game.Position.C1;
import static io.github.samuelmurray.game.Position.C2;
import static io.github.samuelmurray.game.Position.C3;
import static io.github.samuelmurray.game.Position.C4;
import static io.github.samuelmurray.game.Position.C5;
import static io.github.samuelmurray.game.Position.C6;
import static io.github.samuelmurray.game.Position.C7;
import static io.github.samuelmurray.game.Position.C8;
import static io.github.samuelmurray.game.Position.D1;
import static io.github.samuelmurray.game.Position.D3;
import static io.github.samuelmurray.game.Position.E1;
import static io.github.samuelmurray.game.Position.E3;
import static io.github.samuelmurray.game.Position.F1;
import static io.github.samuelmurray.game.Position.F3;
import static io.github.samuelmurray.game.Position.G1;
import static io.github.samuelmurray.game.Position.G3;
import static io.github.samuelmurray.game.Position.H1;
import static io.github.samuelmurray.game.Position.H3;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RookMovementTest {
    private final RookMovement rookMovement = new RookMovement();

    @Test
    void testGetValidMovesInCorner() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = rookMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(A2, A3, A4, A5, A6, A7, A8, B1, C1, D1, E1, F1, G1, H1);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesInMiddle() {
        GameState gameState = GameState.of(Collections.emptyMap());
        Set<Position> validMoves = rookMovement.getPotentiallyValidMoves(C3, null, gameState);
        Set<Position> expected = Set.of(A3, B3, D3, E3, F3, G3, H3, C1, C2, C4, C5, C6, C7, C8);
        assertEquals(expected, validMoves);
    }

    @Test
    void testGetValidMovesOtherPiecesBlock() {
        ChessPiece otherPiece = new ChessPiece(null, null);
        GameState gameState = GameState.of(Map.of(A3, otherPiece));
        Set<Position> validMoves = rookMovement.getPotentiallyValidMoves(A1, null, gameState);
        Set<Position> expected = Set.of(A2, A3, B1, C1, D1, E1, F1, G1, H1);
        assertEquals(expected, validMoves);
    }
}