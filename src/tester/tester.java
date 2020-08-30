package tester;

import View.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class tester {
    public static void main(String[] args) {
        JFrame frame= new JFrame("Panel Example");
        ButtonPanel bp=new ButtonPanel();



        frame.add(bp);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
