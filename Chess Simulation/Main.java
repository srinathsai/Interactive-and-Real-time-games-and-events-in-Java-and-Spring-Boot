import board.Board;
import game.ChessGame;
import pieces.King;
import pieces.Piece;
import pieces.Queen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /*Board b = new Board();
        b.initializeBoard();
        b.displayBoard();


        King king= new King(0,0,true);
        b.placePiece(king,0,0);
        b.displayBoard();

        b.movePiece(0,0,2,2);
        b.displayBoard();

        b.movePiece(0,0,1,1);
        b.displayBoard(); */

        ChessGame game = new ChessGame();
        game.printChessBoard();

        while(game.isGameOver==false && game.isStaleMate==false && game.isInsufficientMaterials()==false){
            if(game.getWhiteTurn()==true){
                System.out.println("White's turn");
                System.out.println("Enter start Position (x,y) :");
                Scanner sc = new Scanner(System.in);
                game.x = sc.nextInt();
                sc = new Scanner(System.in);
                game.y = sc.nextInt();
                System.out.println("Enter destination position (x,y) :");
                sc = new Scanner(System.in);
                game.X = sc.nextInt();
                sc = new Scanner(System.in);
                game.Y = sc.nextInt();

                game.play();

            }
            else{
                System.out.println("Black's turn");
                System.out.println("Enter start Position (x,y) :");
                Scanner sc = new Scanner(System.in);
                game.x = sc.nextInt();
                sc = new Scanner(System.in);
                game.y = sc.nextInt();
                System.out.println("Enter destination position (x,y) :");
                sc = new Scanner(System.in);
                game.X = sc.nextInt();
                sc = new Scanner(System.in);
                game.Y = sc.nextInt();

                game.play();
            }
        }

        /*game.x =3;
        game.y =1;
        game.X =3;
        game.Y =2;
        game.play();

        game.x = 6;
        game.y = 6;
        game.X = 6;
        game.Y = 4;
        game.play();

        game.x = 2;
        game.y = 0;
        game.X = 6;
        game.Y = 4;
        game.play();

        game.x = 5;
        game.y = 7;
        game.X = 0;
        game.Y = 2;
        game.play();*/










    }
}
