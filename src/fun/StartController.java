package fun;

import fun.SceneManager.AppUi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController implements Controller {

  enum DIFFICULTY {
    BEGINNER(9, 9, 10),
    INTERMEDIATE(16, 16, 40),
    EXPERT(16, 30, 99);

    private final int height;
    private final int width;
    private final int mines;

    DIFFICULTY(int height, int width, int mines) {
      this.height = height;
      this.width = width;
      this.mines = mines;
    }

    public int getHeight() {
      return height;
    }

    public int getWidth() {
      return width;
    }

    public int getMines() {
      return mines;
    }
  }

  private static StartController instance;

  public static StartController getInstance() {
    return instance;
  }

  @FXML
  public void initialize() {
    instance = this;
  }

  @FXML
  private void onClick(ActionEvent event) {
    String name = ((Button) event.getSource()).getId();
    DIFFICULTY diff = DIFFICULTY.BEGINNER;
    if (name.equals("intermediate")) {
      diff = DIFFICULTY.INTERMEDIATE;
    } else if (name.equals("expert")) {
      diff = DIFFICULTY.EXPERT;
    }
    ((MinesController) SceneManager.getController(AppUi.MINES))
        .createSquares(diff.getHeight(), diff.getWidth(), diff.getMines());
    MainApp.setRoot(AppUi.MINES);

    TimerCounter timer = new TimerCounter();
    timer.timerStart();
  }
}
