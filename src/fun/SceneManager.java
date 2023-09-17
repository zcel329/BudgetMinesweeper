package fun;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {
  public enum AppUi {
    START,
    MINES,
  }

  private static HashMap<AppUi, Parent> sceneMap = new HashMap<AppUi, Parent>();

  public static void addUi(AppUi appUi, Parent uiRoot) {
    sceneMap.put(appUi, uiRoot);
  }

  public static Parent getUiRoot(AppUi ui) {
    return sceneMap.get(ui);
  }
}
