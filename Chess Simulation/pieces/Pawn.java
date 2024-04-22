package pieces;

import pieces.Piece;

public class Pawn extends Piece {

    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int destX, int destY) {
        // Implement logic for valid Pawn moves
        // Pawn can move forward one square, but on its first move, it can move forward two squares
        // It can also capture diagonally one square ahead

        int sourceX = super.getX();
        int sourceY = super.getY();
        int diff1 = Math.abs(sourceX - destX);
        int diff2 = Math.abs(sourceY - destY);


        if(diff1!=0){
            return diff1==1 && diff2==1;
        }
        else{
            return diff2==1 || diff2==2;
        }


    }
}

