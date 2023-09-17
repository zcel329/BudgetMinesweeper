package fun;

import fun.SceneManager.AppUi;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

  private static Scene scene;

  public static void setRoot(AppUi appUi) {
    scene.setRoot(SceneManager.getUiRoot(appUi));
  }

  private static Parent loadFxml(final String fxml) throws IOException {
    return FXMLLoader.load(
        new File("src\\fun\\resources\\fxml\\" + fxml + ".fxml").toURI().toURL());
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {

    SceneManager.addUi(AppUi.START, loadFxml("Start"));
    SceneManager.addController(AppUi.START, StartController.getInstance());

    SceneManager.addUi(AppUi.MINES, loadFxml("Mines"));
    SceneManager.addController(AppUi.MINES, MinesController.getInstance());

    Parent root = SceneManager.getUiRoot(AppUi.START);

    scene = new Scene(root, 600.0, 400.0);

    Image icon = new Image(getClass().getResourceAsStream("resources/images/flag.png"));
    stage.getIcons().add(icon);

    stage.setTitle("Budget Minesweeper");
    stage.setScene(scene);
    stage.show();

    ((MinesController) SceneManager.getController(AppUi.MINES)).setStage(stage);
  }
}
