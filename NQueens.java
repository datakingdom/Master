/**
 * Created by gaowenshuo on 3/10/16.
 */

import java.util.Arrays;
import java.util.Scanner;


public class NQueens {
    // define global variable to calculate the solution number
    public static int SolutionCount=0;
    public static void main(String args[]) {
        System.out.println("How many queens? ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] board = new int[n]; //hold the column position of n queens
        placeQueenOnBoard(0, board);
        System.out.println(n+" queens' number of solution is " + SolutionCount);
    }

    private static void placeQueenOnBoard(int Qi, int[] board) {
        int n = board.length;
        //base case
        if (Qi == n) {
            // a valid configuration found.
            System.out.println(Arrays.toString(board));
            SolutionCount = SolutionCount+1;
        } else {
            //try to put the ith Queen (Qi) in all of the columns
            for (int column = 0; column < n; column++) {
                if (isSafePlace(column, Qi, board)) {
                    board[Qi] = column;
                    //then place remaining queens.
                    placeQueenOnBoard(Qi + 1, board);
                    /**
                     * backtracking. It is not required in this as we only look previously
                     * placed queens in isSafePlace method and it doesnot care what values
                     * are available in next positions.*
                     */
                    board[Qi] = -1;
                }
            }
        }
    }

    //check if the column is safe place to put Qi (ith Queen)
    private static boolean isSafePlace(int column, int Qi, int[] board) {

        //check for all previously placed queens
        for (int i = 0; i < Qi; i++) {
            if (board[i] == column) { // the ith Queen(previous) is in same column
                return false;
            }
            //the ith Queen is in diagonal
            //(r1, c1) - (r2, c1). if |r1-r2| == |c1-c2| then they are in diagonal
            if (Math.abs(board[i] - column) == Math.abs(i - Qi)) {
                return false;
            }
        }
        return true;

    }


}