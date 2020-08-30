package tester;

import Entity.Sudoku;
import View.MyFrame;

public class testView {
          public static void main(String[] args) {
              Sudoku sd=new Sudoku();
//              SudokuPanel sp=new SudokuPanel(sd);
////              sp.setSudoku(sd);
//
//              JFrame frame=new JFrame();
//              frame.add(sp);
//              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//              frame.setSize(500,500);
//              frame.setVisible(true);
              new MyFrame("tester",sd);

        }
}
