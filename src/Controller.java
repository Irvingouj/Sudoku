import Entity.Sudoku;
import Entity.SudokuGenerator;
import Interfaces.Observer;
import View.MyFrame;

public class Controller {
    Sudoku s;
    MyFrame mf;



    public Controller(){
        s= SudokuGenerator.generatesRandomSudoku();
        mf=new MyFrame("Sudoku Game",s);
        s.addObserver((Observer) mf.getSudokuPanel());
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
