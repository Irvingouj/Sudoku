package View;

import Entity.Sudoku;
import Entity.SudokuValidator;
import Interfaces.Observer;

import javax.naming.directory.InvalidAttributesException;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SudokuPanel extends JPanel implements ActionListener, Observer {
    ArrayList<SudokuTextArea> sudokuTextAreas=new ArrayList<>();
    Sudoku s;
    Timer timer;


    public SudokuPanel(Sudoku s,KeyListener HandleInputKey){
        this();
        this.s=s;
        firstLoad(HandleInputKey);
    }

    private void firstLoad(KeyListener HandleInputKey){
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            sudokuTextAreas.get(i).setText(s.getAt(i));
            sudokuTextAreas.get(i).addKeyListener(HandleInputKey);
        }
    }

    private SudokuPanel() {
        this.setBackground(new Color(127, 162, 255));
        this.setBounds(0,0,32*9-2,32*9-2);
        for (int i = 0; i <81 ; i++) {
            SudokuTextArea sudokuTextArea=new SudokuTextArea();
            sudokuTextAreas.add(sudokuTextArea);
            sudokuTextArea.setLocation(((i%9)*32),((i/9)*32));
            this.add(sudokuTextArea);
        }
        timer =new Timer(1,this);
        this.setLayout(null);
        this.setVisible(true);
    }


    int count=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!s.solvedSudoku.isEmpty()) {
            Sudoku tmp = s.solvedSudoku.remove();
            setTextfield(tmp);
            System.out.println(count++ + "\n" + tmp);
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle rect=new Rectangle(0,35+35+30,5,310);
        g.fillRect(0,32+32+30,32*9,2);
        g.fillRect(0,5*32+30,32*9,2);
        g.fillRect(32+32+30,0,2,32*9);
        g.fillRect(5*32+30,0,2,32*9);
        g.setColor(Color.yellow);
    }

    public void setTextfield(Sudoku s){
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            sudokuTextAreas.get(i).sudokuSetText(Integer.toString(s.getAt(i)));
        }
    }

    public void setSudoku(Sudoku s){
        clearTextfield();
        setTextfield(s);
    }


    private void clearTextfield() {
        for (SudokuTextArea st:sudokuTextAreas){
            st.setText("");
            st.setBackground(Color.white);
        }
    }

    //set the sudoku board
    public void inputSuduko(){

        int[][] board=new int[Sudoku.SIZE][Sudoku.SIZE];
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            String inArea=sudokuTextAreas.get(i).getText();
            board[i/Sudoku.SIZE][i%Sudoku.SIZE]=Integer.parseInt((inArea.equals("")?"0":inArea));
        }
        s=new Sudoku(board);
    }


    public void solve() throws InvalidAttributesException {
        Sudoku.solve(s);
        timer.start();
    }



    @Override
    public void update() {
        setSudoku(s);
    }
}
