package pieces;

import pieces.Piece;

public class Knight extends Piece {

    public Knight(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int destX, int destY) {
        // Implement logic for valid Knight moves
        // Knight can move in an L-shape: two squares in one direction and one square perpendicular to that direction

        int sourceX = super.getX();
        int sourceY = super.getY();

        int diff1 = Math.abs(sourceX-destX);
        int diff2 = Math.abs(sourceY-destY);

        if((diff1==2 && diff2==1) || (diff1==1 && diff2==2)){
            return true;
        }
        return false;
    }
}
