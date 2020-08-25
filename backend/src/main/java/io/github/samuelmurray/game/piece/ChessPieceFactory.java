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
            case ROOK -> createRook(team);
            case BISHOP -> createBishop(team);
            case KNIGHT -> createKnight(team);
            case PAWN -> createPawn(team);
        };
    }

    private static ChessPiece createKing(Team team) {
        return new ChessPiece(team, KING, new KingMovement(), isWhite(team) ? "\u2654" : "\u265A");
    }

    private static ChessPiece createQueen(Team team) {
        return new ChessPiece(team, QUEEN, new QueenMovement(), isWhite(team) ? "\u2655" : "\u265B");
    }

    private static ChessPiece createBishop(Team team) {
        return new ChessPiece(team, BISHOP, new BishopMovement(), isWhite(team) ? "\u2657" : "\u265D");
    }

    private static ChessPiece createRook(Team team) {
        return new ChessPiece(team, ROOK, new RookMovement(), isWhite(team) ? "\u2656" : "\u265C");
    }

    private static ChessPiece createKnight(Team team) {
        return new ChessPiece(team, KNIGHT, new KnightMovement(), isWhite(team) ? "\u2658" : "\u265E");
    }

    private static ChessPiece createPawn(Team team) {
        return new ChessPiece(team, PAWN, new PawnMovement(), isWhite(team) ? "\u2659" : "\u265F");
    }

    private static boolean isWhite(Team team) {
        return team == Team.WHITE;
    }
}
