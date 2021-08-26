import Entity.Sudoku;
import Entity.SudokuGenerator;
import View.MyFrame;

public class Controller {
    Sudoku s;
    MyFrame mf;

    public Controller(){
        s= SudokuGenerator.generatesRandomSudoku();
        mf=new MyFrame("Sudoku Game",s);
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
