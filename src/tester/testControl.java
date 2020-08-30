package tester;

import Entity.Sudoku;
import View.MyFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testControl {
    public static class tester{
        tester(){
            Timer timer=new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(1);
                }
            });
            timer.start();
        }
    }
    public static void main(String[] args) {
        tester t=new tester();
        while (true);
    }
}
