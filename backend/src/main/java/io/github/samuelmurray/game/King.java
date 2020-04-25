package io.github.samuelmurray.game;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends ChessPiece {

    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Set<Position> getValidMoves(GameState gameState) {
        return getPotentiallyValidMoves().stream()
                .filter(position -> {
                    Optional<ChessPiece> maybeOther = gameState.getPieceAt(position);
                    return maybeOther.map(piece -> piece.team != this.team).orElse(true);
                })
                .collect(Collectors.toSet());
    }

    private Set<Position> getPotentiallyValidMoves() {
        return Set.of(this.position.above, this.position.below, this.position.left, this.position.right,
                this.position.above.right, this.position.above.left, this.position.below.right, this.position.below.left);
    }
}
