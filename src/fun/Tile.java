package fun;

import javafx.scene.image.ImageView;

public class Tile {
  private int i;
  private int j;
  private ImageView imgView;
  private int value;

  public Tile(int i, int j, ImageView imgView) {
    this.i = i;
    this.j = j;
    this.imgView = imgView;
    value = 0;
  }

  public int getX() {
    return j;
  }

  public int getY() {
    return i;
  }

  public ImageView getImageView() {
    return imgView;
  }

  public int getTileNum() {
    return value;
  }

  public void setTileNum(int num) {
    value = num;
  }
}