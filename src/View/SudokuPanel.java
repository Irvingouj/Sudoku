package View;

import Entity.Sudoku;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SudokuPanel extends JPanel implements ActionListener {
    ArrayList<SudokuTextArea> sudokuTextAreas=new ArrayList<>();
    Sudoku s;
    Timer timer;


    public SudokuPanel(Sudoku s){
        this();
        this.s=s;
        firstLoad();
    }
    public SudokuPanel(Sudoku s,KeyListener k){
        this();
        this.s=s;
        firstLoad(k);
    }

    private void firstLoad(KeyListener k){
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            sudokuTextAreas.get(i).setText(s.getAt(i));
            sudokuTextAreas.get(i).addKeyListener(k);
        }
    }

    private void firstLoad() {
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            sudokuTextAreas.get(i).setText(s.getAt(i));
            sudokuTextAreas.get(i).addKeyListener(
                    new KeyListener() {
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
            );
        }
    }

    public boolean updateSudoku() {
        int[][] board=new int[Sudoku.SIZE][Sudoku.SIZE];
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            String inArea=sudokuTextAreas.get(i).getText();
            board[i/Sudoku.SIZE][i%Sudoku.SIZE]=Integer.parseInt((inArea.equals("")?"0":inArea));
        }
        s=new Sudoku(board);

        return s.validateBoard();
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
        timer =new Timer(50,this);
        this.setLayout(null);
        this.setVisible(true);
    }

    int count=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!s.solvedSudoku.isEmpty()) {
            Sudoku tmp = s.solvedSudoku.remove();
            loadSudoku(tmp);
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

    public void loadSudoku(Sudoku s){
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            sudokuTextAreas.get(i).sudokuSetText(Integer.toString(s.getAt(i)));
        }
    }

    public void setSudoku(Sudoku s){
        clear();
        loadSudoku(s);
    }


    private void clear() {
        for (SudokuTextArea st:sudokuTextAreas){
            st.setText("");
            st.setBackground(Color.white);
        }
    }

    public void inputSuduko(){
        updateSudoku();
        setSudoku(s);
    }


    public void solve() {
        System.out.println("s in solve is \n"+s);
        s.solve();
        timer.start();
    }

    public boolean isSolvoed() {
        for (int i = 0; i <s.SIZE*s.SIZE ; i++) {
            if(sudokuTextAreas.get(i).getText().equals("")){
                return false;
            }
        }
        return s.validateBoard();
    }
}
