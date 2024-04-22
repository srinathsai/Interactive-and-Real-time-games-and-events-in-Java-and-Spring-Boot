package pieces;

import pieces.Piece;

public class Queen extends Piece {

    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int destX, int destY) {
        // Implement logic for valid Queen moves
        // For simplicity, let's assume it can move horizontally, vertically, or diagonally any number of squares
        int sourceX = super.getX();
        int sourceY = super.getY();
        int diff1 = Math.abs(sourceX-destX);
        int diff2 = Math.abs(sourceY-destY);
        if(diff1!=0 && diff2!=0){
            return (diff1 + diff2)%2==0;
        }
        else if((diff1==0 && diff2!=0) || (diff1!=0 && diff2==0)){
            return true;
        }
        return false;
    }
}

