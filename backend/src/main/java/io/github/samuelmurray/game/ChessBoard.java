package io.github.samuelmurray.game;


public class ChessBoard {
    private final GameState gameState;

    public ChessBoard(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildRow(sb, Position.A8);
        buildRow(sb, Position.A7);
        buildRow(sb, Position.A6);
        buildRow(sb, Position.A5);
        buildRow(sb, Position.A4);
        buildRow(sb, Position.A3);
        buildRow(sb, Position.A2);
        buildRow(sb, Position.A1);
        return sb.toString();
    }

    private void buildRow(StringBuilder sb, Position position) {
        while (position.isOnBoard()) {
            final Position finalPosition = position;
            gameState.getPieceAt(position).ifPresentOrElse(sb::append, () -> sb.append(finalPosition));
            position = position.getRight();
            sb.append(" ");
        }
        sb.append("\n");
    }
}