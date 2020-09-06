package tester;

import Entity.Sudoku;
import View.ButtonPanel;
import View.MyFrame;

import javax.swing.*;
import java.awt.*;

public class tester {
    public static void main(String[] args) {
        Sudoku s=Sudoku.generatesRandomSudoku();
        new MyFrame("test",s);

//        System.out.println(s);
//        s.solve();
//        System.out.println(s);

//        System.out.println(new String(new char[]{'a','b','c','d'},0,4));
    }
}
