package View;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuTextArea extends JTextArea {
    public SudokuTextArea() {
        this.setBounds(0, 0, 30, 30);
        this.setBackground(Color.white);
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        this.setDocument(new LimitNumDocument(1));
        this.setVisible(true);
    }

    public String getText() {
        return super.getText();
    }

    public void sudokuSetText(String s) {
        String tmp = this.getText();
        if (!s.equals(this.getText())) {
            this.setBackground(Color.red);
            this.setText(s);
        } else {
            this.setBackground(Color.WHITE);
        }
        if (!tmp.equals("0") && s.equals("0")) {
            this.setBackground(Color.blue);
        }

    }


    public boolean setText(int i) {
        if (i / 10 != 0) return false;
        append(Integer.toString(i));
        return true;
    }

    @Override
    public void append(String str) {
        super.append(str);
    }



    private static class LimitedDocument extends PlainDocument {
//        private int length = 1;

        public LimitedDocument() {
            super();
        }
        static int z=0;
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

//            System.out.println("this is called"+z++);
            char[] s = str.toCharArray();
            int length = 0;
            // 过滤非数字
            for (int i = 0; i < s.length; i++) {
                if ((s[i] >= '0') && (s[i] <= '9')) {
                    s[length++] = s[i];
                }

                String strRes=new String(s, 0, length);
                System.out.println(strRes);
                super.insertString(offs, strRes, a);
            }
        }

    }
}


class LimitNumDocument extends PlainDocument {
    private int fLength; // 可任意输入

    public LimitNumDocument(int length) {
        fLength = length;
    }
    static int count=0;
    public void insertString(int offs, String str, AttributeSet attr)
            throws BadLocationException {

        if(str.equals("0")){
            super.insertString(offs,null,attr);
            return;
        }
        if(str.charAt(0)>'9'||str.charAt(0)<'0'){
            super.insertString(offs,null,attr);
            return;
        }
        int originalLength = getLength();
        if (originalLength <= 0) {
            super.insertString(offs, str, attr);
            return;
        }


        char[] input = str.toCharArray();
        int inputLength = 0;
        for (int i = 0; i < input.length; i++) {

            if (originalLength + inputLength >= fLength) {
                break;
            }
            inputLength++;
        }

        String res=new String(input, 0, inputLength);
        super.insertString(offs, res, attr);
    }

}



