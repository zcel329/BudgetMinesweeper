package fun;

import javafx.scene.image.ImageView;

public class Tile {
  private int i;
  private int j;
  private ImageView imgView;
  private int value;
  private boolean flag;
  private boolean isDefault;

  public Tile(int i, int j, ImageView imgView) {
    this.i = i;
    this.j = j;
    this.imgView = imgView;
    value = 0;
    flag = false;
    isDefault = true;
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

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public boolean isFlagged() {
    return flag;
  }

  public void setFlag(boolean bool) {
    flag = bool;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean bool) {
    isDefault = bool;
  }
}
