package Entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.io.IOException;
import java.util.*;


public class Sudoku implements ActionListener {
    public int[][] board;
    public final static int SIZE=9;
    public Queue<Sudoku> solvedSudoku=new LinkedList<>();
    public Sudoku(Timer t){
    }



    public Sudoku()
    {
        this.board = new int[SIZE][SIZE];
    }

    public Sudoku(int[][] board) {
        this.board = board;
    }

    public int getAt(int idx){
        return getAt(idx/9,idx%9);
    }
    public int getAt(int rowIdx,int colIdx){
        return board[rowIdx][colIdx];
    }
    public void setAt(int idx,int value){setAt(idx/9,idx%9,value);}
    public void setAt(int rowIdx,int colIdx,int value){
        board[rowIdx][colIdx]=value;
    }

    private int getFirstZero(){
        int count=0;
        for (int i = 0; i <SIZE ; i++) {
            for (int j = 0; j <SIZE ; j++) {
                if(board[i][j]==0){
                    return count;
                }
                count++;
            }
        }
        return count;
    }

    public void clearSolve(){
        solvedSudoku.clear();
    }

    public Sudoku solve(Sudoku s){
        Sudoku res=s.copy(s);
        res.solve();
        return res;
    }


    public void solve(){
        try {
            if(validateSudoku(this))throw new IOException("Invalid Sudoku");
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        try {

            int idx = getFirstZero();
            boolean res = solverHelper(idx / 9, idx % 9, this);

            if (!res) {
                throw new InputMismatchException();
            }
        }catch (InputMismatchException e){
            System.out.println("not solvable");
            e.printStackTrace();
        }
    }
    private boolean solverHelper(int rowIdx, int cowIdx, Sudoku s) {
        Sudoku tmp=copy(s);
        solvedSudoku.add(tmp);

        if(s.board[rowIdx][cowIdx]!=0){//if not zero,go to next zero
            if(cowIdx<SIZE-1){
                return solverHelper(rowIdx,cowIdx+1,s);
            }
            if(rowIdx<SIZE-1){
                cowIdx=0;
                return solverHelper(rowIdx+1,cowIdx,s);
            }
            return true;
        }

        for (int k = 1; k <10 ; k++) {
            if(validateWithValue(rowIdx,cowIdx,k)){
                s.board[rowIdx][cowIdx]=k;
                if(solverHelper(rowIdx,cowIdx, s)){
                    return true;
                }
                s.board[rowIdx][cowIdx]=0;

            }
        }

        //went through 1 to 9 and always false
        return false;
    }

    private Sudoku copy(Sudoku s) {
        Sudoku res=new Sudoku();
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                res.board[i][j]=s.getAt(i,j);
            }
        }
        return res;
    }

    public static boolean validateSudoku(Sudoku s){
        for (int i = 0; i <SIZE ; i++) {
            HashSet<Integer> hs=new HashSet<>();
            for (int j = 0; j <SIZE ; j++) {
                boolean b=hs.add(s.getAt(i,j));
                if(!b&&s.getAt(i,j)!=0)return true;
            }
            //check row

            HashSet<Integer> hs1=new HashSet<>();
            for (int j = 0; j <SIZE ; j++) {
                boolean b=hs1.add(s.getAt(j,i));
                if(!b&&s.getAt(j,i)!=0)return true;
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
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static Sudoku generatesRandomSudoku(){

        ArrayList<Integer> list;
        Random r=new Random();
        Sudoku res=new Sudoku();

        list=newList();
        for (int i = 0; i <SIZE*SIZE ; i++) {
            if(r.nextInt(81)<11) {
                while (list.size() > 0) {
                    int idx = r.nextInt(list.size());
                    if (res.validateWithValue(i / 9, i % 9, list.get(idx))) {
                        res.setAt(i / 9, i % 9, list.get(idx));
                        list.remove(idx);
                        break;
                    }
                }
            }
        }
        //by randomly putting a list of 1 to 9 randomly to the Sudoku, it is guaranteed that
        //this Sudoku is solvable
        res=res.solve(res);
       //this function actually solve a problem and delete a few numbers in it to get a random Sudoku
        //this way, we garnette its solvable
        res.randomRowOperation();

        for (int i = 0; i < SIZE*SIZE; i++) {
            if(r.nextInt(100)<70){
                res.setAt(i,0);
            }
        }

        res.solvedSudoku.clear();




        return res;
    }

    private void randomRowOperation() {
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


    public boolean validateWithValue(int rowIdx,int cowIdx,int value) {
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

    public static Sudoku initSudoku(){
        int[][] borad=new int[][]{
                new int[]{7,0,0,4,0,0,1,2,0},
                new int[]{6,0,0,0,7,5,0,0,9},
                new int[]{0,0,0,6,0,1,0,7,8},
                new int[]{0,0,7,0,4,0,2,6,0},
                new int[]{0,0,1,0,5,0,9,3,0},
                new int[]{9,0,4,0,6,0,0,0,5},
                new int[]{0,7,0,3,0,0,0,1,2},
                new int[]{1,2,0,0,0,7,4,0,0},
                new int[]{0,4,9,2,0,6,0,0,7},
        };
        return new Sudoku(borad);
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        int rowCount=0;
        for(int[] rows:board){
            int count=0;
            for (int i:rows){
                sb.append(i);
                count++;
                if(count%3==0&&count%9!=0){
                    sb.append("|");
                }else if(count%9!=0){
                    sb.append(",");
                }
            }
            sb.append("\n");
            rowCount++;
            if(rowCount==3){
                rowCount=0;
                sb.append("-----------------\n");
            }
        }
        return sb.toString();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //this might have problems
    public boolean validateBoard() {
        HashSet<String> seen=new HashSet<>();
        for (int i = 0; i <SIZE ; i++) {
            for (int j = 0; j <SIZE ; j++) {
                if(board[i][j]!=0){
                    String s="("+board[i][j]+")";
                    if(!seen.add(s+"row"+i)
                            ||!seen.add(s+"col"+j)
                            ||!seen.add(s+"box"+i/3+""+j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
