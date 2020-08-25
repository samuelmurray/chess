package io.github.samuelmurray.game.piece;

import io.github.samuelmurray.game.GameState;
import io.github.samuelmurray.game.Position;
import io.github.samuelmurray.game.Team;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.samuelmurray.game.Position.*;

final class PawnMovement implements PieceMovement {
    private static final List<Position> WHITE_STARTING_POSITIONS = List.of(A2, B2, C2, D2, E2, F2, G2, H2);
    private static final List<Position> BLACK_STARTING_POSITIONS = List.of(A7, B7, C7, D7, E7, F7, G7, H7);

    @Override
    public Set<Position> getPotentiallyValidMoves(Position currentPosition, Team currentTeam, GameState gameState) {
        Objects.requireNonNull(currentTeam);
        UnaryOperator<Position> forward = currentTeam == Team.WHITE ? Position::getAbove : Position::getBelow;
        return Stream.of(getNonCapturingMoves(currentPosition, currentTeam, gameState, forward),
                getCapturingMoves(currentPosition, forward))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private Set<Position> getNonCapturingMoves(Position currentPosition, Team currentTeam, GameState gameState, UnaryOperator<Position> forward) {
        Set<Position> moves = new HashSet<>();
        Position forwardPosition = forward.apply(currentPosition);
        if (!gameState.hasPieceAt(forwardPosition)) {
            moves.add(forwardPosition);
            Position twiceForwardPosition = forward.apply(forwardPosition);
            if (isStartingPosition(currentPosition, currentTeam) && !gameState.hasPieceAt(twiceForwardPosition)) {
                moves.add(twiceForwardPosition);
            }
        }
        return moves;
    }

    private boolean isStartingPosition(Position currentPosition, Team currentTeam) {
        if (currentTeam == Team.WHITE) {
            return WHITE_STARTING_POSITIONS.contains(currentPosition);
        }
        return BLACK_STARTING_POSITIONS.contains(currentPosition);
    }

    private Set<Position> getCapturingMoves(Position currentPosition, UnaryOperator<Position> forward) {
        Position forwardPosition = forward.apply(currentPosition);
        return Stream.of(forwardPosition.getLeft(), forwardPosition.getRight())
                .filter(Position::isOnBoard)
                .collect(Collectors.toSet());
    }
}
