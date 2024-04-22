package board;

import pieces.Piece;

public class Square {
    private Piece piece;
    private int x;
    private int y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null; // Initialize square without a piece
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
