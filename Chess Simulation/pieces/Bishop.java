package pieces;

import pieces.Piece;

public class Bishop extends Piece {
    public Bishop(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int destX, int destY) {
        // Implement logic for valid Bishop moves
        // Bishop can move diagonally any number of squares

        int sourceX = super.getX();
        int sourceY = super.getY();

        return (Math.abs(sourceX - destX) + Math.abs(sourceY - destY))%2==0;
    }
}
