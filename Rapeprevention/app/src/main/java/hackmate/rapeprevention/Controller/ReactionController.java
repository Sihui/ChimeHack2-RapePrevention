package hackmate.rapeprevention.Controller;

import android.app.Fragment;
import android.content.Intent;
import hackmate.rapeprevention.Models.Model;
import hackmate.rapeprevention.ReactionActivity;
import hackmate.rapeprevention.TimerActivity;
import java.util.Date;
import java.util.Random;
import java.util.Timer;

public class ReactionController extends Controller<ReactionActivity> {
  Fragment currentFragment;
  Timer timer;
  long startTime;
  long changeColorTime;
  boolean training;

  private ReactionController() {
  }

  public static ReactionController ONLY = new ReactionController();

  @Override public void takeActivity(ReactionActivity activity) {
    super.takeActivity(activity);
  }

  public void onReceiveIntent(Intent intent) {
    training = intent.getBooleanExtra("training", false);
    if (training) {
      training();
    } else {
      startMeasureReaction();
    }
  }

  public void training() {
    // Train 5 times
    final int interval = 5000;
    getActivity().handler.postDelayed(new Runnable() {
      int times = 5;

      @Override public void run() {
        getActivity().setTitle(
            String.format("Please click button when it turns red. Remaining %d times.", times));
        times--;
        startMeasureReaction();
        if (times != 0) {
          getActivity().handler.postDelayed(this, interval);
        } else {
          startActivity(TimerActivity.class);
        }
      }
    }, interval);
  }

  public void startMeasureReaction() {
    Random random = new Random();
    changeColorTime = random.nextInt(1000) + 2000;
    startTime = getCurrentTime();

    getActivity().showNormalBtn();
    getActivity().handler.postDelayed(new Runnable() {
      @Override public void run() {
        if (hasActivity()) {
          getActivity().showAlertBtn();
        }
      }
    }, changeColorTime);
  }

  public void onUserClickButton() {
    long timeDiff = Math.abs(startTime + changeColorTime - getCurrentTime());
    getActivity().notifyReactionTime(timeDiff);
    if (training) {
      Model.getModel().reactionTime.get().add(timeDiff);
    }
  }

  long getCurrentTime() {
    return (new Date()).getTime();
  }
}
