package Entity;


import Interfaces.ObservedObject;
import Interfaces.Observer;

import javax.naming.directory.InvalidAttributesException;
import javax.swing.Timer;
import java.io.IOException;
import java.util.*;




public class Sudoku implements ObservedObject {
    private int[][] board;
    public final static int SIZE=9;
    public Queue<Sudoku> solvedSudoku=new LinkedList<>();




    public Sudoku()
    {
        this.board = new int[SIZE][SIZE];
    }

    public Sudoku(int[][] board) {
        this(); // make sure the board is not null;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                this.setAt(row,col,board[row][col]);
            }
        }

    }

    public int getAt(int idx){
        return getAt(idx/9,idx%9);
    }

    public int getAt(int rowIdx,int colIdx){ return board[rowIdx][colIdx]; }

    public void setAt(int idx,int value){
        setAt(idx/9,idx%9,value);
    }

    public void setAt(int rowIdx,int colIdx,int value){
        board[rowIdx][colIdx]=value;
        this.somethingHasChanged();
    }

    public int[][] getBoard(){return board;}



    public static Sudoku solve(Sudoku s) throws InvalidAttributesException{
        Sudoku res=s.copy(s);
        SudokuSolver.solve(s);
        return res;
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



    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void somethingHasChanged() {
        for (Observer ob:observers) {
            ob.update();
        }

    }
}
