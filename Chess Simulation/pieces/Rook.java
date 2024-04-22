package pieces;

import pieces.Piece;

public class Rook extends Piece {

    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int destX, int destY) {
        // Implement logic for valid Rook moves
        // Rook can move horizontally or vertically any number of squares

        int sourceX = super.getX();
        int sourceY = super.getY();
        int diff1 = Math.abs(sourceX-destX);
        int diff2 = Math.abs(sourceY-destY);

        if((diff1==0 && diff2!=0) || (diff1!=0 && diff2==0)){
            return true;
        }

        else{
            return false;
        }
    }
}
