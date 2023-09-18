package fun;

import fun.SceneManager.AppUi;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

public class TimerCounter {
  private Timer timer;
  private int timeCounter;

  public void start() {
    timeCounter = 0;
    timer = new Timer();
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            timeCounter++;
            if (timeCounter > 1000) {
              this.cancel();
              timer.cancel(); // Cancel the Timer when the TimerTask is canceled
            }
            Platform.runLater(() -> updateTimers(String.valueOf(timeCounter)));
          }
        },
        0,
        1000);
  }

  public void stop() {
    if (timer != null) {
      timer.cancel();
    }
  }

  public int getTime() {
    return timeCounter;
  }

  private void updateTimers(String string) {
    ((MinesController) SceneManager.getController(AppUi.MINES)).updateTimerLabel(string);
  }
}
