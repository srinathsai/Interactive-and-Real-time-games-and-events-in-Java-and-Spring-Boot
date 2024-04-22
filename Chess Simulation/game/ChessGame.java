package game;

import board.Board;
import board.Square;
import pieces.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ChessGame {
    private Board board;
    private boolean isWhiteTurn;

    private Square[][] chessBoard;

    public int x = 0;
    public int y = 0;
    public int X = 0;
    public int Y = 0;

    public boolean isGameOver = false;

    public boolean isStaleMate = false;

    Map<String,Integer> whiteMap;
    Map<String,Integer> blackMap;

    public ChessGame() {
        board = new Board();
        initializeGame();
        this.isWhiteTurn = true;
        whiteMap = new HashMap<>();
        blackMap = new HashMap<>();

        whiteMap.put("King",1);
        whiteMap.put("Knight",2);
        whiteMap.put("Bishop",2);
        whiteMap.put("Rook",2);
        whiteMap.put("Queen", 1);
        whiteMap.put("Pawn",8);

        blackMap.put("King",1);
        blackMap.put("Knight",2);
        blackMap.put("Bishop",2);
        blackMap.put("Rook",2);
        blackMap.put("Queen", 1);
        blackMap.put("Pawn",8);



    }

    public Board getBoard() {
        return board;
    }

    public void initializeGame() {
        chessBoard = board.getSquares();
        // Place white pieces
        Rook rook1 = new Rook(0,0,true);
        chessBoard[0][0].setPiece(rook1);

        Knight knight1 = new Knight(1,0,true);
        chessBoard[1][0].setPiece(knight1);

        Bishop bishop1 = new Bishop(2,0,true);
        chessBoard[2][0].setPiece(bishop1);

        Queen q1= new Queen(3,0,true);
        chessBoard[3][0].setPiece(q1);

        King king1 = new King(4,0, true);
        chessBoard[4][0].setPiece(king1);

        Bishop bishop2 = new Bishop(5,0,true);
        chessBoard[5][0].setPiece(bishop2);

        Knight knight2 = new Knight(6,0,true);
        chessBoard[6][0].setPiece(knight2);

        Rook rook2 = new Rook(7,0,true);
        chessBoard[7][0].setPiece(rook2);

        for(int i=0;i<chessBoard.length;i++){
            Pawn p = new Pawn(i,1,true);
            chessBoard[i][1].setPiece(p);
        }

        // Place black pieces
        Rook rook3 = new Rook(0,7,false);
        chessBoard[0][7].setPiece(rook3);

        Knight knight3 = new Knight(1,7,false);
        chessBoard[1][7].setPiece(knight3);

        Bishop bishop3 = new Bishop(2,7,false);
        chessBoard[2][7].setPiece(bishop3);

        Queen q2= new Queen(3,7,false);
        chessBoard[3][7].setPiece(q2);

        King king2 = new King(4,7, false);
        chessBoard[4][7].setPiece(king2);

        Bishop bishop4 = new Bishop(5,7,false);
        chessBoard[5][7].setPiece(bishop4);

        Knight knight4 = new Knight(6,7,false);
        chessBoard[6][7].setPiece(knight4);

        Rook rook4 = new Rook(7,7,false);
        chessBoard[7][7].setPiece(rook4);

        for(int i=0;i<chessBoard.length;i++){
            Pawn p = new Pawn(i,6,false);
            chessBoard[i][6].setPiece(p);
        }

        board.setSquares(chessBoard);

        // White starts the game
    }

    public void setWhiteTurn(boolean isWhiteTurn){
        this.isWhiteTurn = isWhiteTurn;
    }

    public boolean getWhiteTurn(){
        return this.isWhiteTurn;
    }

    public void play() {
        //Start the game with white's first turn to make the move going on further with alternate turn for valid moves
        //check the constion for check mate to see who wins the game

        if((x>=0 && x<8 && y>=0 && y<8) && getPieceName(this.chessBoard[x][y].getPiece()).equals("King")){
            Piece piece = this.chessBoard[x][y].getPiece();
            if((x==4 && y==0) && !isCheck(piece,x,y) && (X==6 && Y==0) && (this.chessBoard[7][0].getPiece()!=null && getPieceName(this.chessBoard[7][0].getPiece()).equals("Rook") && this.chessBoard[7][0].getPiece().isWhite()== piece.isWhite())){
                if(canShortCastle(5,0)==true){
                    this.chessBoard[6][0].setPiece(piece);
                    piece.setX(6);
                    piece.setY(0);
                    this.chessBoard[4][0].setPiece(null);

                    Piece piece2 = this.chessBoard[7][0].getPiece();
                    this.chessBoard[5][0].setPiece(piece2);
                    piece2.setX(5);
                    piece2.setY(0);
                    this.chessBoard[7][0].setPiece(null);

                    if(getWhiteTurn()==true){
                        setWhiteTurn(false);
                    }
                    else{
                        setWhiteTurn(true);
                    }

                }
                getBoard().setSquares(this.chessBoard);
            }
            else if((x==4 && y==0) && !isCheck(piece,x,y) && (X==2 && Y==0) && (this.chessBoard[0][0].getPiece()!=null && getPieceName(this.chessBoard[0][0].getPiece()).equals("Rook") && this.chessBoard[0][0].getPiece().isWhite()== piece.isWhite())){
                if(canLongCastle(3,0)==true){
                    this.chessBoard[2][0].setPiece(piece);
                    piece.setX(2);
                    piece.setY(0);
                    this.chessBoard[4][0].setPiece(null);

                    Piece piece2 = this.chessBoard[0][0].getPiece();
                    this.chessBoard[3][0].setPiece(piece2);
                    piece2.setX(3);
                    piece2.setY(0);
                    this.chessBoard[0][0].setPiece(null);
                    if(getWhiteTurn()==true){
                        setWhiteTurn(false);
                    }
                    else{
                        setWhiteTurn(true);
                    }
                }
                getBoard().setSquares(this.chessBoard);

            }

            else if((x==4 && y==7) && !isCheck(piece,x,y) && (X==2 && Y==7) && (this.chessBoard[0][7].getPiece()!=null && getPieceName(this.chessBoard[0][7].getPiece()).equals("Rook") && this.chessBoard[0][7].getPiece().isWhite()== piece.isWhite())){
                if(canLongCastle(3,7)==true){
                    this.chessBoard[2][7].setPiece(piece);
                    piece.setX(2);
                    piece.setY(7);
                    this.chessBoard[4][7].setPiece(null);

                    Piece piece2 = this.chessBoard[0][7].getPiece();
                    this.chessBoard[3][7].setPiece(piece2);
                    piece2.setX(3);
                    piece2.setY(7);
                    this.chessBoard[0][7].setPiece(null);

                    if(getWhiteTurn()==true){
                        setWhiteTurn(false);
                    }
                    else{
                        setWhiteTurn(true);
                    }
                }
                getBoard().setSquares(this.chessBoard);


            }

            else if((x==4 && y==7) && !isCheck(piece,x,y) && (X==6 && Y==7) && (this.chessBoard[7][7].getPiece()!=null && getPieceName(this.chessBoard[7][7].getPiece()).equals("Rook") && this.chessBoard[7][7].getPiece().isWhite()== piece.isWhite())) {
                if (canShortCastle(5, 7) == true) {
                    this.chessBoard[6][7].setPiece(piece);
                    piece.setX(6);
                    piece.setY(7);
                    this.chessBoard[4][7].setPiece(null);

                    Piece piece2 = this.chessBoard[7][7].getPiece();
                    this.chessBoard[5][7].setPiece(piece2);
                    piece2.setX(5);
                    piece2.setY(7);
                    this.chessBoard[7][7].setPiece(null);

                    if (getWhiteTurn() == true) {
                        setWhiteTurn(false);
                    } else {
                        setWhiteTurn(true);
                    }

                }
                getBoard().setSquares(this.chessBoard);
            }

            else{
                if(isValidMove(x,y,X,Y)){
                    if(!isCheck(piece,x,y)){
                        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
                        boolean checkMate = true;
                        for(int[] dir : directions){
                            int r = x+dir[0];
                            int c = y+dir[1];
                            if(r>=0 && r<8 && c>=0 && c<8){
                                if(this.chessBoard[r][c].getPiece()==null){
                                    if(!isCheck(piece,r,c)){
                                        checkMate = false;
                                        break;
                                    }
                                }
                                else{
                                    if((this.chessBoard[r][c].getPiece().isWhite()!=piece.isWhite()) && !isCheck(piece,r,c)){
                                        checkMate = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(checkMate==true){
                            isStaleMate = true;
                            return;
                        }
                        if(this.chessBoard[X][Y].getPiece()!=null){
                            if(this.chessBoard[X][Y].getPiece().isWhite()== piece.isWhite()){
                                System.out.println("Invalid move");
                                board.setSquares(this.chessBoard);
                                printChessBoard();
                            }
                            else{
                                if(isCheck(piece,X,Y)){
                                    System.out.println("Invalid move ");
                                    board.setSquares(this.chessBoard);
                                    printChessBoard();
                                }
                                else{
                                    this.chessBoard[x][y].setPiece(null);

                                    Piece removedPiece = this.chessBoard[X][Y].getPiece();
                                    String removedPieceName = getPieceName(removedPiece);
                                    if(removedPiece.isWhite()==true){
                                        whiteMap.put(removedPieceName,whiteMap.get(removedPieceName)-1);
                                    }
                                    else{
                                        blackMap.put(removedPieceName,blackMap.get(removedPieceName)-1);
                                    }

                                    this.chessBoard[X][Y].setPiece(null);
                                    piece.setX(X);
                                    piece.setY(Y);
                                    this.chessBoard[X][Y].setPiece(piece);

                                    if(getWhiteTurn()==true){
                                        setWhiteTurn(false);
                                    }
                                    else{
                                        setWhiteTurn(true);
                                    }

                                    board.setSquares(this.chessBoard);
                                    printChessBoard();

                                }
                            }

                        }
                        else{
                            if(isCheck(piece,X,Y)){
                                System.out.println("Invalid move ");
                                board.setSquares(this.chessBoard);
                                printChessBoard();
                            }
                            else{
                                this.chessBoard[x][y].setPiece(null);
                                piece.setX(X);
                                piece.setY(Y);
                                this.chessBoard[X][Y].setPiece(piece);

                                if(getWhiteTurn()==true){
                                    setWhiteTurn(false);
                                }
                                else{
                                    setWhiteTurn(true);
                                }
                                board.setSquares(this.chessBoard);
                                printChessBoard();
                            }
                        }

                    }

                    else{
                        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
                        boolean checkMate = true;
                        for(int[] dir : directions){
                            int r = x+dir[0];
                            int c = y+dir[1];
                            if(r>=0 && r<8 && c>=0 && c<8){
                                if(this.chessBoard[r][c].getPiece()==null){
                                    if(!isCheck(piece,r,c)){
                                        checkMate = false;
                                        break;
                                    }
                                }
                                else{
                                    if((this.chessBoard[r][c].getPiece().isWhite()!=piece.isWhite()) && !isCheck(piece,r,c)){
                                        checkMate = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(checkMate==true){
                            isGameOver = true;
                            return;
                        }

                        else{
                            if(!isCheck(piece,X,Y) && ((this.chessBoard[X][Y].getPiece()!=null) && (this.chessBoard[X][Y].getPiece().isWhite()!= piece.isWhite()))){
                                this.chessBoard[x][y].setPiece(null);

                                Piece removedPiece = this.chessBoard[X][Y].getPiece();
                                String removedPieceName = getPieceName(removedPiece);
                                if(removedPiece.isWhite()==true){
                                    whiteMap.put(removedPieceName,whiteMap.get(removedPieceName)-1);
                                }
                                else{
                                    blackMap.put(removedPieceName,blackMap.get(removedPieceName)-1);
                                }

                                this.chessBoard[X][Y].setPiece(null);
                                piece.setX(X);
                                piece.setY(Y);
                                this.chessBoard[X][Y].setPiece(piece);

                                if(getWhiteTurn()==true){
                                    setWhiteTurn(false);
                                }
                                else{
                                    setWhiteTurn(true);
                                }

                                board.setSquares(this.chessBoard);
                                printChessBoard();
                            }

                            else if(!isCheck(piece,X,Y) && this.chessBoard[X][Y].getPiece()==null){
                                this.chessBoard[x][y].setPiece(null);

                                piece.setX(X);
                                piece.setY(Y);
                                this.chessBoard[X][Y].setPiece(piece);

                                if(getWhiteTurn()==true){
                                    setWhiteTurn(false);
                                }
                                else{
                                    setWhiteTurn(true);
                                }

                                board.setSquares(this.chessBoard);
                                printChessBoard();
                            }

                            else{
                                System.out.println("Invalid move");
                                board.setSquares(this.chessBoard);
                                printChessBoard();
                            }

                        }


                    }
                }
                else{
                    System.out.println("Invalid move");
                    board.setSquares(this.chessBoard);
                    printChessBoard();
                }
            }




        }
        getBoard().setSquares(this.chessBoard);
        if(isValidMove(x,y,X,Y)){
            if(this.chessBoard[X][Y].getPiece()==null){
                Piece piece = this.chessBoard[x][y].getPiece();
                this.chessBoard[x][y].setPiece(null);
                piece.setX(X);
                piece.setY(Y);
                this.chessBoard[X][Y].setPiece(piece);

                if(getWhiteTurn()==true){
                    setWhiteTurn(false);
                }
                else{
                    setWhiteTurn(true);
                }
                board.setSquares(this.chessBoard);
                printChessBoard();
            }
            else{
                Piece piece = this.chessBoard[x][y].getPiece();
                this.chessBoard[x][y].setPiece(null);

                Piece removedPiece = this.chessBoard[X][Y].getPiece();
                String removedPieceName = getPieceName(removedPiece);
                if(removedPiece.isWhite()==true){
                    whiteMap.put(removedPieceName,whiteMap.get(removedPieceName)-1);
                }
                else{
                    blackMap.put(removedPieceName,blackMap.get(removedPieceName)-1);
                }

                this.chessBoard[X][Y].setPiece(null);
                piece.setX(X);
                piece.setY(Y);
                this.chessBoard[X][Y].setPiece(piece);
                if(getWhiteTurn()==true){
                    setWhiteTurn(false);
                }
                else{
                    setWhiteTurn(true);
                }
                board.setSquares(this.chessBoard);
                printChessBoard();
            }
        }

        else{
            System.out.println("Invalid move");
            board.setSquares(this.chessBoard);
            printChessBoard();
        }


    }

    private boolean isValidMove(int startX, int startY, int destX, int destY) {
        if(startX<0 || startX>= this.chessBoard.length || startY<0 || startY>=this.chessBoard[0].length || destX<0 || destX>=this.chessBoard.length || destY<0 || destY>=this.chessBoard[0].length){
            System.out.println("You should enter your cooardinates with in limits");
            return false;
        }

        if(this.chessBoard[startX][startY].getPiece()==null){
            System.out.println("There is no piece on the board, so enter the coordinates which has piece");
            return false;
        }

        String pieceName = getPieceName(this.chessBoard[startX][startY].getPiece());
        if(pieceName.equals("Queen")){
                Queen queen = (Queen) this.chessBoard[startX][startY].getPiece();
                if(!queen.isValidMove(destX,destY)){
                    System.out.println("Enter valid destination coordinates for queen");
                    return false;
                }

                int[][] directions = {{0,1},{0,-1},{-1,0},{-1,-1},{1,0},{1,-1},{-1,1},{1,1}};
                int[] requiredDir = new int[2];
                for(int[] dir : directions){
                    int x1 = startX;
                    int y1 = startY;
                    if(canReach(dir,destX,destY,x1,y1)==true){
                        requiredDir[0] = dir[0];
                        requiredDir[1] = dir[1];
                        break;
                    }
                }
            int x1 = startX;
            int y1 = startY;

            return isPossible(requiredDir,x1,y1,destX,destY,this.chessBoard[startX][startY].getPiece());





        }

        else if(pieceName.equals("Bishop")){
            Bishop bishop = (Bishop)  this.chessBoard[startX][startY].getPiece();
            if(!bishop.isValidMove(destX,destY)){
                System.out.println("Enter Valid destination coardniates for Bishop");
                return false;
            }
            int[][] directions = {{1,1},{-1,-1},{1,-1},{-1,1}};
            int[] requiredDir = new int[2];
            for(int[] dir : directions){
                int x1 = startX;
                int y1 = startY;
                if(canReach(dir,destX,destY,x1,y1)==true){
                    requiredDir[0] = dir[0];
                    requiredDir[1] = dir[1];
                    break;
                }
            }
            int x1 = startX;
            int y1 = startY;

            return isPossible(requiredDir,x1,y1,destX,destY,this.chessBoard[startX][startY].getPiece());



        }

        else if(pieceName.equals("Rook")){
            Rook rook = (Rook) this.chessBoard[startX][startY].getPiece();
            if(!rook.isValidMove(destX,destY)){
                System.out.println("Enter valid destination coordinates for rook");
                return false;
            }

            int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

            int[] requiredDir = new int[2];
            for(int[] dir : directions){
                int x1 = startX;
                int y1 = startY;
                if(canReach(dir,destX,destY,x1,y1)==true){
                    requiredDir[0] = dir[0];
                    requiredDir[1] = dir[1];
                    break;
                }
            }
            int x1 = startX;
            int y1 = startY;

            return isPossible(requiredDir,x1,y1,destX,destY,this.chessBoard[startX][startY].getPiece());


        }

        else if(pieceName.equals("Knight")){
            Knight knight = (Knight) this.chessBoard[startX][startY].getPiece();
            if(!knight.isValidMove(destX, destY)){
                System.out.println("Enter the valid destination coordinates of knight");
                return false;
            }

            if(this.chessBoard[destX][destY].getPiece()==null){
                return true;
            }
            else if(this.chessBoard[destX][destY].getPiece().isWhite()!= knight.isWhite()){
                return true;
            }
            else{
                return false;
            }
        }

        else if(pieceName.equals("Pawn")){
            Pawn pawn = (Pawn) this.chessBoard[startX][startY].getPiece();
            //System.out.println(pawn.getX());
            //System.out.println(pawn.getY());
            if(!pawn.isValidMove(destX,destY)){
                System.out.println("Enter valid destination coordinates of Pawn");
                return false;
            }

            if(startY==1 || startY==6){
                if(Math.abs(startX-destX)==0){
                    if(Math.abs(destY-startY)==2){
                        if(this.chessBoard[destX][destY].getPiece()==null){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else if(Math.abs(destY-startY)==1){
                        if(this.chessBoard[destX][destY].getPiece()==null){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                   if(this.chessBoard[destX][destY].getPiece()==null){
                       return false;
                   }
                   else{
                       if(this.chessBoard[destX][destY].getPiece().isWhite()== !pawn.isWhite()){
                           return true;
                       }
                       else{
                           return false;
                       }
                   }
                }


            }

            else{
                if(Math.abs(startX-destX)==0){
                    if(Math.abs(destY-startY)==2){
                        return false;
                    }

                    else if(Math.abs(destY-startY)==1){
                        if(this.chessBoard[destX][destY].getPiece()==null){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }

                else{
                    if(this.chessBoard[destX][destY].getPiece()==null){
                        return false;
                    }
                    else{
                        //System.out.println(pawn.isWhite());
                        //System.out.println(this.chessBoard[destX][destY].getPiece().isWhite());
                        if(this.chessBoard[destX][destY].getPiece().isWhite()!= pawn.isWhite()){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }





            }
        }
        else if(getPieceName(this.chessBoard[startX][startY].getPiece()).equals("King")){

            King king = (King) this.chessBoard[startX][startY].getPiece();
            if(!king.isValidMove(destX,destY)){
                System.out.println("Enter Valid destination coordinates for king");
                return false;
            }

            return true;


        }

       return true;

    }

    private boolean isCheck(Piece piece, int startX, int startY){
        int[][] diagnolDirections = {{1,1},{-1,-1},{1,-1},{-1,1}};
        int x1 = startX;
        int y1 = startY;
        for(int[] dir: diagnolDirections){
            if(DiagnolThreat(piece,x1,y1,dir)==true){
                return true;
            }
        }

        int[][] straightDirections = {{0,1},{0,-1},{1,0},{-1,0}};

        for(int[] dir : straightDirections){
            if(StraightThreat(piece,x1,y1,dir)==true){
                return true;
            }
        }

        int[][] horseDirections = {{-2,-1},{-1,-2},{2,-1},{2,1},{1,-2},{1,2},{-2,1},{-1,2}};

        for(int[] dir : horseDirections){
            int r = x1 + dir[0];
            int c = y1 + dir[1];
            if(r>=0 && r<8 && c>=0 && c<8){
                if(this.chessBoard[r][c].getPiece()!=null){
                    if(getPieceName(this.chessBoard[r][c].getPiece()).equals("Knight")){
                        if(piece.isWhite()!=this.chessBoard[r][c].getPiece().isWhite()){
                            return true;
                        }
                    }
                }
            }
        }

        if(piece.isWhite()==true){
            int r1 = x1-1;
            int c1 = y1+1;
            if(r1>=0 && r1<8 && c1>=0 && c1<8){
                if(this.chessBoard[r1][c1].getPiece()!=null){
                    if(getPieceName(this.chessBoard[r1][c1].getPiece()).equals("Pawn")){
                       if(this.chessBoard[r1][c1].getPiece().isWhite()==false){
                           return true;
                       }
                    }
                }
            }

            int r2 = x1+1;
            int c2 = y1+1;

            if(r2>=0 && r2<8 && c2>=0 && c2<8){
                if(this.chessBoard[r2][c2].getPiece()!=null){
                    if(getPieceName(this.chessBoard[r2][c2].getPiece()).equals("Pawn")){
                        if(this.chessBoard[r2][c2].getPiece().isWhite()==false){
                            return true;
                        }
                    }
                }
            }

        }
        else{
            int r1 = x1-1;
            int c1 = y1-1;
            if(r1>=0 && r1<8 && c1>=0 && c1<8){
                if(this.chessBoard[r1][c1].getPiece()!=null){
                    if(getPieceName(this.chessBoard[r1][c1].getPiece()).equals("Pawn")){
                        if(this.chessBoard[r1][c1].getPiece().isWhite()==true){
                            return true;
                        }
                    }
                }
            }

            int r2 = x1+1;
            int c2 = y1-1;

            if(r2>=0 && r2<8 && c2>=0 && c2<8){
                if(this.chessBoard[r2][c2].getPiece()!=null){
                    if(getPieceName(this.chessBoard[r2][c2].getPiece()).equals("Pawn")){
                        if(this.chessBoard[r2][c2].getPiece().isWhite()==true){
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }



    private boolean DiagnolThreat(Piece piece, int x1, int y1, int[] dir){
        while(x1>=0 && x1<8 && y1>=0 && y1<8){
            x1 += dir[0];
            y1 += dir[1];

            if(x1>=0 && x1<8 && y1>=0 && y1<8){
                if(this.chessBoard[x1][y1].getPiece()!=null){
                    if(getPieceName(this.chessBoard[x1][y1].getPiece()).equals("Queen") || getPieceName(this.chessBoard[x1][y1].getPiece()).equals("Bishop")){
                        if(piece.isWhite()!=this.chessBoard[x1][y1].getPiece().isWhite()){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
            }

        }
        return false;
    }

    private boolean StraightThreat(Piece piece, int x1, int y1,int[] dir){
        while(x1>=0 && x1<8 && y1>=0 && y1<8){
            x1 += dir[0];
            y1 += dir[1];

            if(x1>=0 && x1<8 && y1>=0 && y1<8){
                if(this.chessBoard[x1][y1].getPiece()!=null){
                    if(getPieceName(this.chessBoard[x1][y1].getPiece()).equals("Queen") || getPieceName(this.chessBoard[x1][y1].getPiece()).equals("Rook")){
                        if(piece.isWhite()!=this.chessBoard[x1][y1].getPiece().isWhite()){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
            }

        }
        return false;
    }

    public void printChessBoard(){
        board.displayBoard();
    }

    private String getPieceName(Piece piece){
        if(piece instanceof Queen){
            return "Queen";
        }
        else if(piece instanceof Bishop){
            return "Bishop";
        }
        else if(piece instanceof Knight){
            return "Knight";
        }
        else if(piece instanceof Rook){
            return "Rook";
        }
        else if(piece instanceof Pawn){
            return "Pawn";
        }
        else if(piece instanceof King){
            return "King";
        }
        return "";
    }

    private boolean canReach(int[] dir, int destX, int destY, int startX, int startY){
        if(startX<0 || startX>=8 || startY<0 || startY>=8){
            return false;
        }

        if(startX==destX && startY==destY){
            return true;
        }
        startX+=dir[0];
        startY+=dir[1];

        return canReach(dir,destX,destY,startX,startY);
    }

    private boolean isPossible(int[] dir,int startX, int startY, int destX, int destY, Piece piece){

        while(startX!=destX && startY!=destY){
            startX += dir[0];
            startY += dir[1];
            if(startX==destX && startY==destY){
                break;
            }
            if(this.chessBoard[startX][startY].getPiece()!=null){
                return false;
            }
        }

        if(this.chessBoard[destX][destY].getPiece()==null){
            return true;
        }
        else{
            if(this.chessBoard[destX][destY].getPiece().isWhite()!=piece.isWhite()){
                return true;
            }
            else{
                return false;
            }
        }


    }


    private boolean canShortCastle(int r, int c){
        for(int i =r; i<7;i++){
            if(this.chessBoard[r][c].getPiece()!=null){
                return false;
            }
        }
        return true;
    }

    private boolean canLongCastle(int r, int c){
        for(int i=r;i>0;i--){
            if(this.chessBoard[r][c].getPiece()!=null){
                return false;
            }
        }
        return true;
    }

    public boolean isInsufficientMaterials(){
        if((whiteMap.get("Queen")==0 && whiteMap.get("Rook")==0 && whiteMap.get("Pawn")==0 && whiteMap.get("Bishop")==0 && whiteMap.get("Knight")==0) && (blackMap.get("Queen")==0 && blackMap.get("Rook")==0 && blackMap.get("Pawn")==0 && blackMap.get("Bishop")==0 && blackMap.get("Knight")==0)){
            return true;
        }
        else if((whiteMap.get("Queen")==0 && whiteMap.get("Rook")==0 && whiteMap.get("Pawn")==0 && whiteMap.get("Bishop")==0 && whiteMap.get("Knight")==1) && (blackMap.get("Queen")==0 && blackMap.get("Rook")==0 && blackMap.get("Pawn")==0 && blackMap.get("Bishop")==0 && blackMap.get("Knight")==0)){
            return true;
        }
        else if((whiteMap.get("Queen")==0 && whiteMap.get("Rook")==0 && whiteMap.get("Pawn")==0 && whiteMap.get("Bishop")==1 && whiteMap.get("Knight")==0) && (blackMap.get("Queen")==0 && blackMap.get("Rook")==0 && blackMap.get("Pawn")==0 && blackMap.get("Bishop")==0 && blackMap.get("Knight")==0)){
            return true;
        }
        else if((whiteMap.get("Queen")==0 && whiteMap.get("Rook")==0 && whiteMap.get("Pawn")==0 && whiteMap.get("Bishop")==0 && whiteMap.get("Knight")==0) && (blackMap.get("Queen")==0 && blackMap.get("Rook")==0 && blackMap.get("Pawn")==0 && blackMap.get("Bishop")==0 && blackMap.get("Knight")==1)){
            return true;
        }
        else if((whiteMap.get("Queen")==0 && whiteMap.get("Rook")==0 && whiteMap.get("Pawn")==0 && whiteMap.get("Bishop")==0 && whiteMap.get("Knight")==0) && (blackMap.get("Queen")==0 && blackMap.get("Rook")==0 && blackMap.get("Pawn")==0 && blackMap.get("Bishop")==1 && blackMap.get("Knight")==0)){
            return true;
        }
        else{
            return false;
        }
    }
}
