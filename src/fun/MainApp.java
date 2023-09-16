package fun;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    URL url =
        new File("C:\\Users\\Zach\\Desktop\\Minesweeper\\src\\fun\\resources\\fxml\\Scene.fxml")
            .toURI()
            .toURL();
    Parent root = FXMLLoader.load(url);

    Scene scene = new Scene(root);

    stage.setTitle("Minesweeper");
    stage.setScene(scene);
    stage.show();
  }

  public static void main() {
    launch();
  }
}
