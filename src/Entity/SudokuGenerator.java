package Entity;

import javax.naming.directory.InvalidAttributesException;
import java.util.*;

import static Entity.Sudoku.SIZE;

public class SudokuGenerator {

//    public static Sudoku generatesRandomSudoku() {
//
//        ArrayList<Integer> list;
//        Random r=new Random();
//        Sudoku res=new Sudoku();
//
//        list=newList();
//        for (int i = 0; i <SIZE*SIZE ; i++) {
//            if(r.nextInt(81)<11) {
//                while (list.size() > 0) {
//                    int idx = r.nextInt(list.size());
//                    if (SudokuValidator.validateWithValue(i / 9, i % 9, list.get(idx),res)) {
//                        res.setAt(i / 9, i % 9, list.get(idx));
//                        list.remove(idx);
//                        break;
//                    }
//                }
//            }
//        }
//
//        //by randomly putting a list of 1 to 9 randomly to the Sudoku, it is guaranteed that
//        //this Sudoku is solvable
//        try {
//            res= Sudoku.solve(res);
//        }catch (InvalidAttributesException ignored){
//
//        }
//
//        //this function actually solve a problem and delete a few numbers in it to get a random Sudoku
//        //this way, we garnette its solvable
//        randomRowOperation(res);
//
//        for (int i = 0; i < SIZE*SIZE; i++) {
//            if(r.nextInt(100)<70){
//                res.setAt(i,0);
//            }
//        }
//
//        res.solvedSudoku.clear();
//
//
//
//
//        return res;
//    }

    static int hardness = 40;


    public static Sudoku generatesRandomSudoku(){

        Sudoku sdk = generatesRandomCompleteSudoku();
        Random r= new Random();

        ArrayList<Integer> idxs = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            idxs.add(i);
        }


        Collections.shuffle(idxs);

        for(int i = 0; i<hardness ; i++){
            sdk.setAt(idxs.get(i),0);
        }



        return sdk;
    }

    public static Sudoku generatesRandomCompleteSudoku(){
        Sudoku sdk;
        int[][] board = new int[SIZE][SIZE];
        Random r = new Random();
        ArrayList<Integer> list = newList();

        for (int i = 0; i < SIZE; i++) {
            int theNumberToPutInToTheSudoku = list.get(r.nextInt(list.size()));
            // randomly select number from the list(which contains 1 to 9 initially)

            board[i][r.nextInt(9)] = theNumberToPutInToTheSudoku;
            // put the number randomly in each row;

            list.remove(Integer.valueOf(theNumberToPutInToTheSudoku));
            //remove the selected numbers
        }

        sdk = new Sudoku(board);

        try {
            SudokuSolver.solve(sdk);
        }catch (Exception e){
            e.printStackTrace();

        }



        return sdk;
    }


    private static void randomRowOperation(Sudoku s) {
        int[][] board = s.getBoard();
        Random r=new Random();
        for (int i = 0; i <r.nextInt(71) ; i++) {
            int choice=r.nextInt(4);
            if (choice==1){
                int[] tmp=board[0];
                board[0]=board[2];
                board[2]=tmp;
            }
            if(choice==2){
                int[] tmp=board[3];
                board[3]=board[5];
                board[3]=tmp;
            }
            if(choice==3){
                int[] tmp=board[6];
                board[6]=board[8];
                board[6]=tmp;
            }
        }
    }

    private static ArrayList<Integer> newList() {
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        return list;
    }


    public static void main(String[] args) {
        Sudoku sdk = generatesRandomSudoku();
        System.out.println(sdk);
        System.out.println(SudokuValidator.validateSudoku(sdk));


    }
}
