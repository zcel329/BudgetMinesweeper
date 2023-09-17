package fun;

import java.util.Arrays;
import java.util.Random;

public class MinedBoard {

  private final int height;
  private final int width;
  private final int mines;
  private int[][] board;

  public MinedBoard(int height, int width, int mines) {
    this.height = height;
    this.width = width;
    this.mines = mines;
    createBoard();
    placeMines();
    scrambleMines();
    populateBoard();
    System.out.println(Arrays.deepToString(board));
  }

  public int reaveal(int i, int j) {
    return board[i][j];
  }

  private void createBoard() {
    board = new int[height][width];
  }

  private void placeMines() {
    int count = 0;
    int i = 0, j = 0;
    while (count < mines) {
      if (j == width) {
        j = 0;
        i++;
      }
      board[i][j] = -1;
      j++;
      count++;
    }
  }

  public void scrambleMines() {
    Random random = new Random();
    // Shuffling mines using the Fisher Yates method
    for (int i = this.height - 1; i > 0; i--) {
      for (int j = this.width - 1; j > 0; j--) {
        int m = random.nextInt(i + 1);
        int n = random.nextInt(j + 1);

        int temp = board[i][j];
        board[i][j] = board[m][n];
        board[m][n] = temp;
      }
    }
  }

  public void populateBoard() {
    int mineCounter = 0;
    for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {

            // if bomb
            if (board[i][j] == -1) {
                continue;
            }

            int[] searchValues = Utility.searchValues(i, j, this.height, this.width);

            for (int a = searchValues[0]; a < searchValues[1]; a++) {
                for (int b = searchValues[2]; b < searchValues[3]; b++) {
                    if (board[i + a][j + b] == -1) {
                        mineCounter++;
                    }
                }
            }

            board[i][j] = mineCounter;
            mineCounter = 0;
        }
    }
}
}