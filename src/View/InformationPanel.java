package View;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    TextField t;
    InformationPanel(){
        t = new TextField("Hello Sukudo");
        t.setVisible(true);
        t.setBounds(0,0,500,500-286);
        this.add(t);

        this.setBounds(0,32*9-2,500,500-286);
        this.setLayout(null);

        this.setVisible(true);
    }

    public void displayInformation(String s){
        t.setText(s);
    }




}
