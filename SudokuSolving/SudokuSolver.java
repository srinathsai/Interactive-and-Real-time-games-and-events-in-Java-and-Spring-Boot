import java.util.*;
import java.util.Scanner;

public class SudokuSolver {
    static int[][] board;
    private static Scanner scanner = new Scanner(System.in);

    public static void setScanner(Scanner customScanner) {
        scanner = customScanner;
    }
    public static void resetScanner() {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner(){
        return this.scanner;
    }

    //Task 1:Function to taken input from user to create sudoku board
    public static void readUserInput(int[][] board) {
        System.out.println("Enter the Sudoku puzzle row by row (use '0' for empty cells) :");

        for(int i=0;i<board.length;i++){
            System.out.println("Enter elements for row " + " " + String.valueOf(i+1));
            for(int j=0;j<board[0].length;j++){
                SudokuSolver sv = new SudokuSolver();
                System.out.println("Enter element");
                board[i][j] = sv.getScanner().nextInt();
                sv.resetScanner();
            }
        }
    }

    //Task 1:Function to print the sudoku board
    public  void printBoard(int[][] board) {
        for(int i=0;i<board.length;i++){
            System.out.println();
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j] + " ");
            }

        }
    }


    //Task 2:Function to check whether the sudoku board given from user is valid or not
    public boolean initialValidBoard(int[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]!=0 && (board[i][j]>9 || board[i][j]<0)){
                    return false;
                }
            }
        }
        for(int i=0;i<board.length;i++){
            Set<Integer> rowSet = new HashSet<>();
            for(int j=0;j<board[0].length;j++){
                if(rowSet.contains(board[i][j])){
                    return false;
                }
                else if(board[i][j]!=0){
                    rowSet.add(board[i][j]);
                }

            }
        }

        for(int j=0; j< board[0].length;j++){
            Set<Integer> columnSet = new HashSet<>();
            for(int i=0;i<board.length;i++){
                if(columnSet.contains(board[i][j])){
                    return false;
                }
                else if(board[i][j]!=0){
                    columnSet.add(board[i][j]);
                }

            }
        }

        for(int i=0;i<9;i+=3){
            for(int j=0;j<9;j+=3){
                Set<Integer> boxSet = new HashSet<>();
                for(int r=i;r<=i+2;r++){
                    for(int c=j;c<=j+2;c++){
                        if(boxSet.contains(board[r][c])){
                            return false;
                        }
                        else if(board[r][c]!=0){
                            boxSet.add(board[r][c]);
                        }

                    }
                }
            }
        }

        return true;

    }

    private boolean solve(int[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }
        if (col == board[0].length) {
            return solve(board, row + 1, 0);
        }
        if (board[row][col] != 0) {
            return solve(board, row, col + 1);
        }
        for (int num = 1; num <= 9; num++) {
            if (isValidPlacement(board, row, col, num)) {
                board[row][col] = num;
                if (solve(board, row, col + 1)) {
                    return true;
                }


                board[row][col] = 0;
            }
        }
        return false;
    }






    //Task 3:Function to solve the sudoku
    public  boolean solveSudoku(int[][] board) {
          return solve(board,0,0);
    }

    private  boolean isValidPlacement(int[][] board, int row, int col, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
            if (board[row][i] == num) {
                return false;
            }
            int subgridRow = 3 * (row / 3) + i / 3;
            int subgridCol = 3 * (col / 3) + i % 3;

            if (board[subgridRow][subgridCol] == num) {
                return false;
            }
        }

        // Placement is valid
        return true;

    }

    public static void main(String[] args) {
        board = new int[9][9];
        SudokuSolver solver = new SudokuSolver();
        solver.readUserInput(board);
        System.out.println("Sudoku Board :");
        solver.printBoard(board);
        boolean flag = solver.initialValidBoard(board);
        if(flag==true){
            System.out.println();
            System.out.println("Sudoku board is valid.");
        }
        else{
            System.out.println();
            System.out.println("Sudoku board is  not valid.");
        }
        if(flag==true && solver.solveSudoku(board)){
            System.out.println("Sudoku Solved :");
            System.out.println("Sudoku board");
            solver.printBoard(board);
        }
    }
}