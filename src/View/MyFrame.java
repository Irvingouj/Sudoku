package View;

import Entity.Sudoku;

import javax.naming.directory.InvalidAttributesException;
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
        buttpnPanel.getRefresh().addActionListener(new HandleRefresh());

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
            try {
                sudokuPanel.solve();
            }catch (InvalidAttributesException error){
                infoPanel.displayInformation("this sudoku cannot be solved");
                System.out.println("this is not a valid suduko");
                return;
            }
            buttpnPanel.getSolve().setEnabled(false);
            infoPanel.displayInformation("solved");

        }
    }

    private class HandleRefresh implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttpnPanel.getSolve().setEnabled(true);
            sudokuPanel.setSudoku(new Sudoku());
            sudokuPanel.inputSuduko();
            infoPanel.displayInformation("hello sudoku");
        }
    }


}
