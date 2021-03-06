package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessPieceFactoryTest {

    @Test
    void testCreatePieceThrowsOnNullType() {
        assertThrows(NullPointerException.class, () -> ChessPieceFactory.createPiece(null, Team.BLACK));
    }

    @Test
    void testCreatePieceThrowsOnNullTeam() {
        assertThrows(NullPointerException.class, () -> ChessPieceFactory.createPiece(PieceType.KING, null));
    }

    @Test
    void testCreatePieceCreatesCorrectTeam() {
        ChessPiece piece = ChessPieceFactory.createPiece(PieceType.KING, Team.BLACK);
        assertEquals(Team.BLACK, piece.getTeam());
    }

    @Test
    void testCreatePieceCreatesCorrectType() {
        ChessPiece piece = ChessPieceFactory.createPiece(PieceType.KING, Team.BLACK);
        assertEquals(PieceType.KING, piece.getType());
    }
}