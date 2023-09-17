package fun;

import fun.SceneManager.AppUi;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

public class TimerCounter {

  public void timerStart() {
    final int[] timeCounter = new int[1];
    new Timer()
        .schedule(
            new TimerTask() {
              @Override
              public void run() {
                timeCounter[0]++;
                if (timeCounter[0] > 1000) {
                  this.cancel();
                }
                Platform.runLater(
                    () -> {
                      updateTimers(String.valueOf(timeCounter[0]));
                    });
              }
            },
            0,
            1000);
  }

  private void updateTimers(String string) {

    ((MinesController) SceneManager.getController(AppUi.MINES)).updateTimerLabel(string);
  }
}
