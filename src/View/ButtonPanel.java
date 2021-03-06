package View;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton solve;
    JButton inputSudoku;
    JButton refresh;


    public ButtonPanel(){
        this.setBackground(Color.gray);
        this.setBounds(286,0,500-(286),286);

        solve=new JButton("Solve");
        solve.setBounds(50,50,100,50);
        solve.setBackground(Color.white);
        this.add(solve);

        inputSudoku=new JButton("input");
        inputSudoku.setBounds(50,120,100,50);
        inputSudoku.setBackground(Color.white);
        this.add(inputSudoku);

        refresh = new JButton("refresh");
        refresh.setBounds(50,190,100,50);
        refresh.setBackground(Color.white);
        this.add(refresh);



        this.setLayout(null);
        this.setVisible(true);
    }
    public JButton getSolve(){
        return solve;
    }
    public JButton getInputSudoku(){return inputSudoku;}

    public JButton getRefresh() {
        return refresh;
    }
}
