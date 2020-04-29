package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.Team;

import java.util.Objects;

import static io.github.samuelmurray.game.piece.PieceType.*;

public final class ChessPieceFactory {
    private ChessPieceFactory() {
    }

    public static ChessPiece createPiece(PieceType type, Team team) {
        Objects.requireNonNull(team);
        return switch (type) {
            case KING -> createKing(team);
            case QUEEN -> createQueen(team);
            case BISHOP -> createBishop(team);
            case ROOK -> createRook(team);
            case KNIGHT -> createKnight(team);
            case PAWN -> createPawn(team);
        };
    }

    private static ChessPiece createKing(Team team) {
        return new ChessPiece(team, KING, new KingMovement());
    }

    private static ChessPiece createQueen(Team team) {
        return new ChessPiece(team, QUEEN, new QueenMovement());
    }

    private static ChessPiece createBishop(Team team) {
        return new ChessPiece(team, BISHOP, new BishopMovement());
    }

    private static ChessPiece createRook(Team team) {
        return new ChessPiece(team, ROOK, new RookMovement());
    }

    private static ChessPiece createKnight(Team team) {
        return new ChessPiece(team, KNIGHT, new KnightMovement());
    }

    private static ChessPiece createPawn(Team team) {
        return new ChessPiece(team, PAWN, new PawnMovement());
    }
}
