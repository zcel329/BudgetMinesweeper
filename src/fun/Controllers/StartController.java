package fun.Controllers;

import java.io.IOException;

import fun.Controller;
import fun.MainApp;
import fun.SceneManager;
import fun.SceneManager.AppUi;
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
    if (name.equals("beginner")) {
      height = 9;
      width = 9;
    } else if (name.equals("intermediate")) {
      height = 16;
      width = 16;
    } else if (name.equals("expert")) {
      height = 16;
      width = 30;
    }
    ((MinesController)SceneManager.getController(AppUi.MINES)).createSquares(height,width);
    MainApp.setRoot(AppUi.MINES);
  }
}
