package Entity;

import javax.naming.directory.InvalidAttributesException;
import java.io.IOException;

import static Entity.Sudoku.SIZE;

public class SudokuSolver {

    public static void solve(Sudoku s) throws InvalidAttributesException {

        if(!SudokuValidator.validateSudoku(s))throw new InvalidAttributesException("Invalid Sudoku");

        int idx = getFirstZero(s);
        boolean res = SudokuSolver.solverHelper(idx / 9, idx % 9, s);

        if (!res) {
            throw new InvalidAttributesException();
        }
    }



    public static boolean solverHelper(int rowIdx, int cowIdx, Sudoku s) {

        if (s.getAt(rowIdx,cowIdx) != 0) {//if not zero,go to next zero
            if (cowIdx < SIZE - 1) {
                return solverHelper(rowIdx, cowIdx + 1, s);
            }
            if (rowIdx < SIZE - 1) {
                cowIdx = 0;
                return solverHelper(rowIdx + 1, cowIdx, s);
            }
            return true;
        }

        for (int k = 1; k < 10; k++) {
            if (SudokuValidator.validateWithValue(rowIdx, cowIdx, k,s)) {

                s.setAt(rowIdx,cowIdx,k);
                if (solverHelper(rowIdx, cowIdx, s)) {
                    return true;
                }
                s.setAt(rowIdx,cowIdx,0);

            }
        }

        return false;


    }



    private static int getFirstZero(Sudoku s){
        int count=0;
        for (int i = 0; i <SIZE ; i++) {
            for (int j = 0; j <SIZE ; j++) {
                if(s.getAt(i,j)==0){
                    return count;
                }
                count++;
            }
        }
        return count;
    }

}
