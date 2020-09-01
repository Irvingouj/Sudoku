package tester;

import Entity.Sudoku;
import View.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testView {
          public static void main(String[] args) {


              JFrame frame=new JFrame();
              JTextArea jt=new JTextArea();
              JButton jb=new JButton("print ");
              jb.addActionListener(e -> System.out.println("this is what is in the jt: "+jt.getText()));

              frame.setBackground(Color.gray);
              jt.setBounds(0,0,200,30);
              jt.setBackground(Color.blue);

              jb.setBackground(Color.black);
              jb.setBounds(50,50,100,50);

              frame.add(jt);
              frame.add(jb);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setSize(500,500);
              frame.setLayout(null);
              frame.setVisible(true);


        }
}
