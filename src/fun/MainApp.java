package fun;

import fun.Controllers.MinesController;
import fun.Controllers.StartController;
import fun.SceneManager.AppUi;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

  private static Scene scene;

  public static void setRoot(AppUi appUi) throws IOException {
    scene.setRoot(SceneManager.getUiRoot(appUi));
  }

  private static Parent loadFxml(final String fxml) throws IOException {
    URL url =
        new File(
                "C:\\Users\\Zach\\Desktop\\Minesweeper\\src\\fun\\resources\\fxml\\"
                    + fxml
                    + ".fxml")
            .toURI()
            .toURL();
    return FXMLLoader.load(url);
  }

  @Override
  public void start(Stage stage) throws Exception {

    SceneManager.addUi(AppUi.START, loadFxml("Start"));
    SceneManager.addController(AppUi.START,StartController.getInstance());


    SceneManager.addUi(AppUi.MINES, loadFxml("Mines"));
    SceneManager.addController(AppUi.MINES, MinesController.getInstance());

    Parent root = SceneManager.getUiRoot(AppUi.START);

    scene = new Scene(root, 600.0, 600.0);

    stage.setTitle("Minesweeper");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
