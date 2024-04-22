package board;

import board.Square;
import pieces.*;


public class Board {
    private Square[][] squares;

    public Board() {

        initializeBoard();
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares){
        this.squares = squares;
    }

    public void initializeBoard() {
        // Initialize the board with empty squares
        squares = new Square[8][8];
        for(int i=0;i<squares.length;i++){
            for(int j=0;j<squares[0].length;j++){
                squares[i][j] = new Square(i,j);
            }
        }
    }

    public void placePiece(Piece piece, int x, int y) {
        //Place the give "piece" at the position (x,y) on board of squares
        if(x<0 || x>=8 || y<0 || y>=8){
                System.out.println("with given coardinated piece can't be inserted on board given your both coardinates in  0 and 7 inclusive only..!");
                return;
        }

        squares[x][y] = new Square(x,y);
        squares[x][y].setPiece(piece);


    }

    public void movePiece(int startX, int startY, int destX, int destY) {
        //Move the piece from source position(startX,startY) to the destination position (destX, destY)

        if(startX<0 || startX>= squares.length || startY<0 || startY>= squares[0].length || destX<0 || destY>=squares.length || destY<0 || destY>=squares[0].length){
            System.out.println("you must Enter proper coordinates within the board limits");
            return;
        }
        else{
            if(squares[startX][startY].getPiece()==null){
                System.out.println("There is no piece in given coardinate to move");
                return;
            }
            else{
                if(squares[startX][startY].getPiece() instanceof Queen){
                    Queen q = new Queen(startX, startY,squares[startX][startY].getPiece().isWhite());
                    if(q.isValidMove(destX,destY)){
                        squares[destX][destY].setPiece(squares[startX][startY].getPiece());
                        squares[startX][startY].setPiece(null);
                    }
                    else{
                        System.out.println("Invalid Move!");
                        return;
                    }
                }
                else if(squares[startX][startY].getPiece() instanceof Bishop){
                    Bishop bishop = new Bishop(startX, startY,squares[startX][startY].getPiece().isWhite());
                    if(bishop.isValidMove(destX,destY)){
                        squares[destX][destY].setPiece(squares[startX][startY].getPiece());
                        squares[startX][startY].setPiece(null);
                    }
                    else{
                        System.out.println("Invalid Move!");
                        return;
                    }
                }
                else if(squares[startX][startY].getPiece() instanceof Rook){
                    Rook rook = new Rook(startX, startY,squares[startX][startY].getPiece().isWhite());
                    if(rook.isValidMove(destX,destY)){
                        squares[destX][destY].setPiece(squares[startX][startY].getPiece());
                        squares[startX][startY].setPiece(null);
                    }
                    else{
                        System.out.println("Invalid Move!");
                        return;
                    }
                }
                else if(squares[startX][startY].getPiece() instanceof Knight){
                    Knight knight = new Knight(startX, startY,squares[startX][startY].getPiece().isWhite());
                    if(knight.isValidMove(destX,destY)){
                        squares[destX][destY].setPiece(squares[startX][startY].getPiece());
                        squares[startX][startY].setPiece(null);
                    }
                    else{
                        System.out.println("Invalid Move!");
                        return;
                    }
                }
                else if(squares[startX][startY].getPiece() instanceof King){
                    King king = new King(startX, startY,squares[startX][startY].getPiece().isWhite());
                    if(king.isValidMove(destX,destY)){
                        squares[destX][destY].setPiece(squares[startX][startY].getPiece());
                        squares[startX][startY].setPiece(null);
                    }
                    else{
                        System.out.println("Invalid Move!");
                        return;
                    }
                }
                else if(squares[startX][startY].getPiece() instanceof Pawn){
                    Pawn pawn = new Pawn(startX, startY,squares[startX][startY].getPiece().isWhite());
                    if(pawn.isValidMove(destX,destY)){
                        squares[destX][destY].setPiece(squares[startX][startY].getPiece());
                        squares[startX][startY].setPiece(null);
                    }
                    else{
                        System.out.println("Invalid Move!");
                        return;
                    }
                }
            }
        }

    }

    public void displayBoard() {
        //Print the board on the console with the given pieces on the squares
        for(int i=0;i<squares.length;i++){
            for(int j=0;j<squares[0].length;j++){
                if(squares[i][j].getPiece()==null){
                    System.out.print("-");
                }
                else{
                    if(squares[i][j].getPiece()!=null){
                        String name = "";
                        Piece piece = squares[i][j].getPiece();
                        if(piece instanceof Queen){
                            name = "Queen";
                        }
                        else if(piece instanceof Bishop){
                            name = "Bishop";
                        }
                        else if(piece instanceof Knight){
                            name = "Knight";
                        }
                        else if(piece instanceof Rook){
                            name = "Rook";
                        }
                        else if(piece instanceof Pawn){
                            name = "Pawn";
                        }
                        else if(piece instanceof King){
                            name = "King";
                        }
                        System.out.print(name.charAt(0));
                    }

                }
            }
            System.out.println();
        }

    }


}
