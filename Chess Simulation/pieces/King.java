package pieces;

import pieces.Piece;

public class King extends Piece {

    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int destX, int destY) {
        // Implement logic for valid King moves
        // For simplicity, let's assume it can move one square in any direction
        int sourceX = super.getX();
        int sourceY = super.getY();

        return Math.abs(sourceX-destX)<=1 && Math.abs(sourceY-destY)<=1;
    }
}
