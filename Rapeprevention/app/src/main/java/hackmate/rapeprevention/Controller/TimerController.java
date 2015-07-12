package hackmate.rapeprevention.Controller;

import hackmate.rapeprevention.Models.DrunkAction;
import hackmate.rapeprevention.Models.NotificationManager;
import hackmate.rapeprevention.TimerActivity;
import java.util.Timer;
import java.util.TimerTask;

public class TimerController extends Controller<TimerActivity> {
  public static TimerController ONLY = new TimerController();
  private TimerController() { }

  Timer timer = new Timer();
  int timeToTestReaction = 60;

  @Override public void takeActivity(TimerActivity activity) {
    super.takeActivity(activity);
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override public void run() {
        getActivity().handler.post(new Runnable() {
          @Override public void run() {
            setTime(timeToTestReaction--);
          }
        });
      }
    }, 0, 60 * 1000);
  }

  public void setTime(int time) {
    timeToTestReaction = time;
    if (timeToTestReaction <= 0) {
      if (timeToTestReaction <= -15) {
        DrunkAction.drunkAction();
      }
      NotificationManager.sendNotification();
    }
  }

  public int getTime() {
    return timeToTestReaction;
  }
}
