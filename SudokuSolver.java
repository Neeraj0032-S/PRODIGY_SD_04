package task2;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
            {7, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 9, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 5}
        };

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("This Sudoku puzzle cannot be solved.");
        }
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            System.out.println("Trying " + num + " at (" + row + ", " + col + ")");
                            printBoard(board);

                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // Reset if no solution
                                System.out.println("Backtracking from (" + row + ", " + col + ")");
                            }
                        }
                    }
                    return false; // Trigger backtracking
                }
            }
        }
        return true; // Puzzle solved
    }

    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        return !isInRow(board, num, row) && 
               !isInCol(board, num, col) && 
               !isInBox(board, num, row, col);
    }

    private static boolean isInRow(int[][] board, int num, int row) {
        for (int col = 0; col < GRID_SIZE; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInCol(int[][] board, int num, int col) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInBox(int[][] board, int num, int row, int col) {
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxCol; j < localBoxCol + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] == 0 ? " " : board[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
