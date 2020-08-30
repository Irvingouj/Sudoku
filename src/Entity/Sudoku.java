package Entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;


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
    public void setAt(int rowIdx,int colIdx,int value){
        board[rowIdx][colIdx]=value;
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



    public void solve(){
        try {
            if(validateSudoku(this))throw new IOException("Invalid Sudoku");
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        int idx=getFirstZero();
        solverHelper(idx/9,idx%9,this);
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

    public void solveByStep(int step){
        try {
            if(validateSudoku(this))throw new IOException("Invalid Sudoku");
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        int idx=getFirstZero();
        int rowidx=idx/9,colidx=idx%9;
        solverHelperBystep(rowidx,colidx,this,step);
    }
    private boolean solverHelperBystep(int rowIdx, int cowIdx, Sudoku s,int steps) {
        System.out.println(steps+" steps left");
        if(steps==0){
            return true;
        }

        steps--;
        if(s.board[rowIdx][cowIdx]!=0){//if not zero,go to next zero
            if(cowIdx<SIZE-1){
                return solverHelperBystep(rowIdx,cowIdx+1,s,steps);
            }
            if(rowIdx<SIZE-1){
                cowIdx=0;
                return solverHelperBystep(rowIdx,cowIdx+1,s,steps);
            }
            return true;
        }

        for (int k = 1; k <10 ; k++) {
            if(validateWithValue(rowIdx,cowIdx,k)){
                s.board[rowIdx][cowIdx]=k;
                if(solverHelperBystep(rowIdx,cowIdx,s,steps)){
                    return true;
                }
                s.board[rowIdx][cowIdx]=0;

            }
        }
        //went through 1 to 9 and always false
        return false;
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

    public boolean validateSinglePosition(int rowIdx,int cowIdx){
        if(board[rowIdx][cowIdx]==0)return true;
        for (int i = 0; i <SIZE ; i++) {
            if(board[rowIdx][i]==board[rowIdx][cowIdx]&&i!=cowIdx)
                return false;
        }
        for (int i = 0; i <SIZE ; i++) {
            if(board[i][cowIdx]==board[rowIdx][cowIdx]&&i!=rowIdx)
                return false;
        }

        int initRowIdx=3*(rowIdx/3);
        int initCowIdx=3*(cowIdx/3);
        for (int i = initCowIdx; i <initCowIdx+3 ; i++) {
            for (int j =initRowIdx ; j <initRowIdx+3 ; j++) {
                if(board[j][i]==board[rowIdx][cowIdx]&&(j!=rowIdx&&i!=cowIdx)){
                    return false;
                }
            }
        }

        return true;

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
}
