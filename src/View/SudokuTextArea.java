package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuTextArea extends JTextArea {
    public SudokuTextArea(){
        this.setBounds(0,0,30,30);
        this.setBackground(Color.white);
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.setVisible(true);
    }

    public void sudokuSetText(String s){
        String tmp=this.getText();
        if(!s.equals(this.getText())){
            this.setBackground(Color.red);
            this.setText(s);
        }
        else {
            this.setBackground(Color.WHITE);
        }
       if(!tmp.equals("0") &&s.equals("0")){
            this.setBackground(Color.blue);
        }

    }



    public boolean setText(int i) {
        if(i/10!=0)return false;
        append(Integer.toString(i));
        return true;
    }

    @Override
    public void append(String str) {
        super.append(str);
    }

}
