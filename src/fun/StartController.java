package fun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {
  @FXML Button beginner, intermediate, expert;

  @FXML
  private void onClick(ActionEvent event) {
    String name = ((Button)event.getSource()).getId();
    System.out.println(name);
  }
}
