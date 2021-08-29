package Entity;

import java.io.IOException;
import java.util.HashSet;

import static Entity.Sudoku.SIZE;


public class SudokuValidator {



    public static boolean validateSudoku(Sudoku s){

        for (int i = 0; i <SIZE ; i++) {
            HashSet<Integer> hs=new HashSet<>();
            for (int j = 0; j <SIZE ; j++) {

                boolean b=hs.add(s.getAt(i,j));

                if(!b&&s.getAt(i,j)!=0)
                    return false;
            }
            //check row

            HashSet<Integer> hs1=new HashSet<>();
            for (int j = 0; j <SIZE ; j++) {
                boolean b=hs1.add(s.getAt(j,i));
                if(!b&&s.getAt(j,i)!=0)
                    return false;
            }
            //check col
        }

        for (int i = 0; i <SIZE; i+=3) {
            for (int j = 0; j <SIZE ; j+=3) {
                HashSet<Integer> hs=new HashSet<>();
                for (int k = i; k <i+3 ; k++) {
                    for (int l = j; l <j+3 ; l++) {
                        boolean b=hs.add(s.getAt(k,l));
                        if (s.getAt(k,l)!=0&&!b){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean validateWithValue(int rowIdx,int cowIdx,int value,Sudoku sdk) {

        int[][] board = sdk.getBoard();

        try {
            if(value>9||value<1)throw new IOException();
        }catch (IOException e){
            e.printStackTrace();
        }
        for (int i = 0; i <SIZE ; i++) {
            if(board[rowIdx][i]==value&&i!=cowIdx)
                return false;
        }
        for (int i = 0; i <SIZE ; i++) {
            if(board[i][cowIdx]==value&&i!=rowIdx)
                return false;
        }

        int initRowIdx=3*(rowIdx/3);
        int initCowIdx=3*(cowIdx/3);
        for (int i = initCowIdx; i <initCowIdx+3 ; i++) {
            for (int j =initRowIdx ; j <initRowIdx+3 ; j++) {
                if(board[j][i]==value&&(j!=rowIdx&&i!=cowIdx)){
                    return false;
                }
            }
        }

        return true;
    }
}
