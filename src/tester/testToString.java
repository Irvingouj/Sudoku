package tester;

import Entity.Sudoku;

import java.util.LinkedList;
import java.util.Queue;

public class testToString {
    public static void main(String[] args) throws InterruptedException {
        Sudoku sd=Sudoku.initSudoku();
        sd.solve();
        for (int i = 0; i <sd.solvedSudoku.size(); i++) {
            System.out.println(sd.solvedSudoku.remove());
            System.out.println("=====================");
            }

    }
}

