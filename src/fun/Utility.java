package fun;


public class Utility {

  public static int[] searchValues(int heightPos, int widthPos, int height, int width) {
    int aInit, aMax, bInit, bMax;
    // top left corner
    if (heightPos == 0 && widthPos == 0) {
      aInit = 0;
      aMax = 2;
      bInit = 0;
      bMax = 2;
    }
    // if top right
    else if ((heightPos == 0) && (widthPos == width - 1)) {
      aInit = 0;
      aMax = 2;
      bInit = -1;
      bMax = 1;
    }
    // if bottom left
    else if ((heightPos == height - 1) && (widthPos == 0)) {
      aInit = -1;
      aMax = 1;
      bInit = 0;
      bMax = 2;
    }
    // if bottom right
    else if ((heightPos == height - 1) && (widthPos == width - 1)) {
      aInit = -1;
      aMax = 1;
      bInit = -1;
      bMax = 1;
    }
    // if non-corner left
    else if (widthPos == 0) {
      aInit = -1;
      aMax = 2;
      bInit = 0;
      bMax = 2;
    }
    // if non-corner right
    else if (widthPos == width - 1) {
      aInit = -1;
      aMax = 2;
      bInit = -1;
      bMax = 1;
    }
    // if non-corner top
    else if (heightPos == 0) {
      aInit = 0;
      aMax = 2;
      bInit = -1;
      bMax = 2;
    }
    // if non-corner bot
    else if (heightPos == height - 1) {
      aInit = -1;
      aMax = 1;
      bInit = -1;
      bMax = 2;
    }
    // else internal
    else {
      aInit = -1;
      aMax = 2;
      bInit = -1;
      bMax = 2;
    }
    return new int[] {aInit, aMax, bInit, bMax};
  }
}
