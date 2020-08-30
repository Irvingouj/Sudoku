package View;

import Entity.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class MyFrame extends JFrame {
    SudokuPanel sp;
    ButtonPanel bp;

    public MyFrame(String title,Sudoku s) throws HeadlessException {
        super(title);

        sp=new SudokuPanel(s);
        bp=new ButtonPanel();
        bp.getSolve().addActionListener(new HandleSolve());

        this.add(sp);
        this.add(bp);

        this.setBackground(Color.white);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }



    private class HandleSolve implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sp.solve();
            bp.getSolve().setEnabled(false);

        }
    }


}
