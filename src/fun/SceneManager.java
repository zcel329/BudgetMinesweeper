package fun;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {
  public enum AppUi {
    START,
    MINES,
  }

  private static final HashMap<AppUi, Parent> sceneMap = new HashMap<>();

  private static final HashMap<AppUi, Controller> controllers = new HashMap<>();

  public static void addUi(AppUi appUi, Parent uiRoot) {
    sceneMap.put(appUi, uiRoot);
  }

  public static Parent getUiRoot(AppUi ui) {
    return sceneMap.get(ui);
  }

  public static Controller getController(AppUi ui) {
    return controllers.get(ui);
  }

  public static void addController(AppUi appUi, Controller controller) {
    controllers.put(appUi, controller);
  }
}
