package View;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton solve;

    public ButtonPanel(){
        this.setBackground(Color.gray);
        this.setBounds(286,0,500-(286),286);
        solve=new JButton("Solve");
        solve.setBounds(50,50,100,50);
        solve.setBackground(Color.white);
        this.add(solve);
        this.setLayout(null);
        this.setVisible(true);
    }
    public JButton getSolve(){
        return solve;
    }
}
