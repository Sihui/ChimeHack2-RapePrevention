package hackmate.rapeprevention.Controller;

import android.os.Handler;
import hackmate.rapeprevention.Models.Model;
import hackmate.rapeprevention.ReactionActivity;
import java.util.Date;
import java.util.Random;

public class ReactionController extends Controller<ReactionActivity> {
  long startTime;
  long changeColorTime;

  private ReactionController() {}
  public static ReactionController ONLY = new ReactionController();

  @Override public void takeActivity(ReactionActivity activity) {
    super.takeActivity(activity);
    startMeasureReaction();
  }

  public void startMeasureReaction() {
    Random random = new Random();
    changeColorTime = random.nextInt(1000) + 2000;
    startTime = getCurrentTime();
    Handler handler = getActivity().handler;
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        getActivity().changeSignalColor();
      }
    }, changeColorTime);
  }

  public void onUserClickButton() {
    long timeDiff = Math.abs(startTime + changeColorTime - getCurrentTime());
    getActivity().notifyReactionTime(timeDiff);
    Model.getModel().reactionTime.get().add(timeDiff);
  }

  long getCurrentTime() {
    return (new Date()).getTime();
  }
}
