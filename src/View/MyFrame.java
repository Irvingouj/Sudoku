package View;

import Entity.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class MyFrame extends JFrame {
    SudokuPanel sp;
    ButtonPanel bp;
    InformationPanel ip;

    public MyFrame(String title,Sudoku s) throws HeadlessException {
        super(title);

        sp=new SudokuPanel(s,new HandleInputKey());
        bp=new ButtonPanel();
        ip=new InformationPanel();
        sp.addKeyListener(new HandleInputKey());
        bp.getSolve().addActionListener(new HandleSolve());
        bp.getInputSudoku().addActionListener(new HandleInput());

        this.add(sp);
        this.add(bp);
        this.add(ip);

        this.setBackground(Color.white);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    public class HandleInputKey implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            ip.setIsVaildSudoku(sp.updateSudoku());
            ip.setIsSolvoed(sp.isSolvoed());
        }
    }

    private class HandleInput implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sp.inputSuduko();
            bp.getSolve().setEnabled(true);
        }
    }



    private class HandleSolve implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sp.solve();
            bp.getSolve().setEnabled(false);

        }
    }


}
