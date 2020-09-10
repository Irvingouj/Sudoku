package View;

import Entity.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class MyFrame extends JFrame {
    SudokuPanel sudokuPanel;
    ButtonPanel buttpnPanel;
    InformationPanel infoPanel;

    public MyFrame(String title,Sudoku s) throws HeadlessException {
        super(title);

        sudokuPanel=new SudokuPanel(s,new HandleInputKey());
        buttpnPanel=new ButtonPanel();
        infoPanel=new InformationPanel();
        sudokuPanel.addKeyListener(new HandleInputKey());
        buttpnPanel.getSolve().addActionListener(new HandleSolve());
        buttpnPanel.getInputSudoku().addActionListener(new HandleInput());

        this.add(sudokuPanel);
        this.add(buttpnPanel);
        this.add(infoPanel);

        this.setBackground(Color.white);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    private class HandleInputKey implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            infoPanel.setIsVaildSudoku(sudokuPanel.updateSudoku());
            infoPanel.setIsSolvoed(sudokuPanel.isSolvoed());

        }
    }

    private class HandleInput implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sudokuPanel.inputSuduko();
            buttpnPanel.getSolve().setEnabled(true);

        }
    }



    private class HandleSolve implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sudokuPanel.solve();
            buttpnPanel.getSolve().setEnabled(false);
            infoPanel.setIsSolvoed(true);
        }
    }


}
