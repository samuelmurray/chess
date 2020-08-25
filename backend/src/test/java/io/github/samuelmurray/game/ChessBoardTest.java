package io.github.samuelmurray.game;

import io.github.samuelmurray.game.piece.ChessPieceFactory;
import io.github.samuelmurray.game.piece.PieceType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.github.samuelmurray.game.Position.A1;

class ChessBoardTest {

    @Test
    void test() {
        ChessBoard board = new ChessBoard(GameState.of(Map.of(A1, ChessPieceFactory.createPiece(PieceType.KING, Team.WHITE))));
        System.out.println(board);
    }

}