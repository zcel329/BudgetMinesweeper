package fun;

import fun.SceneManager.AppUi;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController implements Controller {

  private static StartController instance;

  public static StartController getInstance() {
    return instance;
  }

  @FXML private Button beginner, intermediate, expert;

  @FXML
  public void initialize() {
    instance = this;
  }

  @FXML
  private void onClick(ActionEvent event) throws IOException {
    String name = ((Button) event.getSource()).getId();
    int height = 0;
    int width = 0;
    int mines = 0;
    if (name.equals("beginner")) {
      height = 9;
      width = 9;
      mines = 10;
    } else if (name.equals("intermediate")) {
      height = 16;
      width = 16;
      mines = 40;
    } else if (name.equals("expert")) {
      height = 16;
      width = 30;
      mines = 99;
    }
    ((MinesController) SceneManager.getController(AppUi.MINES)).createSquares(height, width, mines);
    MainApp.setRoot(AppUi.MINES);

    TimerCounter timer = new TimerCounter();
    timer.timerStart();
  }
}
