package fun;

import java.util.Random;

public class MinedBoard {

  private final int height;
  private final int width;
  private final int mines;
  private Tile[][] board;

  public MinedBoard(int height, int width, int mines) {
    this.height = height;
    this.width = width;
    this.mines = mines;
    createBoard();
  }

  public void initialiseBoard() {
    placeMines();
    scrambleMines();
    populateBoard();
    // System.out.println(Arrays.deepToString(board));
  }

  public void setBoardTile(int i, int j, Tile tile) {
    board[i][j] = tile;
  }

  public int reveal(int i, int j) {
    return board[i][j].getTileNum();
  }

  private void createBoard() {
    board = new Tile[height][width];
  }

  private void placeMines() {
    int count = 0;
    int i = 0, j = 0;
    while (count < mines) {
      if (j == width) {
        j = 0;
        i++;
      }
      board[i][j].setTileNum(-1);
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

        int temp = board[i][j].getTileNum();
        board[i][j].setTileNum(board[m][n].getTileNum());
        board[m][n].setTileNum(temp);
      }
    }
  }

  public void populateBoard() {
    int mineCounter = 0;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {

        // if bomb
        if (board[i][j].getTileNum() == -1) {
          continue;
        }

        for (int a = -1; a < 2; a++) {
          for (int b = -1; b < 2; b++) {
            int vertVal = a + i;
            int horzVal = b + j;
            if (vertVal > -1 && vertVal < getHeight() && horzVal > -1 && horzVal < getWidth()) {
              if (board[i + a][j + b].getTileNum() == -1) {
                mineCounter++;
              }
            }
          }
        }

        board[i][j].setTileNum(mineCounter);
        mineCounter = 0;
      }
    }
  }

  public Tile getTile(int i, int j) {
    return board[i][j];
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public boolean checkWin() {
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        Tile tile = getTile(i, j);
        if (tile.getTileNum() == -1) {
          //isBomb -> Ignore
        } else if (tile.getTileNum() != -1 && tile.isDefault()) {
          return false;
        }
      }
    }
    return true;
  }
}
