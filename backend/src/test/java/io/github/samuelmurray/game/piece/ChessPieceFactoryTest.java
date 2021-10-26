package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessPieceFactoryTest {

    @Test
    void createPieceThrowsOnNullType() {
        assertThatThrownBy(() -> ChessPieceFactory.createPiece(null, Team.BLACK))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void createPieceThrowsOnNullTeam() {
        assertThatThrownBy(() -> ChessPieceFactory.createPiece(PieceType.KING, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void createPieceCreatesCorrectTeam() {
        assertThat(ChessPieceFactory.createPiece(PieceType.KING, Team.BLACK))
                .extracting(ChessPiece::getTeam)
                .isEqualTo(Team.BLACK);
    }

    @Test
    void createPieceCreatesCorrectType() {
        assertThat(ChessPieceFactory.createPiece(PieceType.KING, Team.BLACK))
                .extracting(ChessPiece::getType)
                .isEqualTo(PieceType.KING);
    }
}