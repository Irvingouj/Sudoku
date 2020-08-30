package tester;

import Entity.Sudoku;
import View.SudokuTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestPanel extends JPanel implements ActionListener {
    ArrayList<SudokuTextArea> sudokuTextAreas=new ArrayList<>();
    Sudoku s;
    Timer timer;

    public TestPanel(Sudoku s){
        this();
        this.s=s;
        firstLoad();
        s.solve();
    }

    private void firstLoad() {
        for (int i = 0; i <Sudoku.SIZE*Sudoku.SIZE ; i++) {
            sudokuTextAreas.get(i).setText(s.getAt(i));
        }
    }


    private TestPanel() {
        this.setBackground(new Color(127, 162, 255));
        this.setBounds(0,0,32*9-2,32*9-2);
        for (int i = 0; i <81 ; i++) {
            SudokuTextArea sudokuTextArea=new SudokuTextArea();
            sudokuTextAreas.add(sudokuTextArea);
            sudokuTextArea.setLocation(((i%9)*32),((i/9)*32));
            this.add(sudokuTextArea);
        }
        timer =new Timer(300,this);
//        timer.start();
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
//            sudokuTextAreas.get(i).setText(s.getAt(i));
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


}
