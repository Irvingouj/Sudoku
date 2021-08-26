package Entity;

public class SudokuTester {
    public static void main(String[] args) {
        Sudoku s = SudokuGenerator.generatesRandomSudoku();
        System.out.println(s);
        try{
            SudokuSolver.solve(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
