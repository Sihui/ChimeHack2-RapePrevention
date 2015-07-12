package hackmate.rapeprevention;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Controller.ReactionController;

public class ReactionActivity extends Activity {
  public Handler handler = new Handler();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reaction);
    ButterKnife.bind(this);
    ReactionController.ONLY.takeActivity(this);
    super.onCreate(savedInstanceState);
  }

  @Override protected void onStop() {
    super.onStop();
    ReactionController.ONLY.dropActivity();
  }

  @OnClick(R.id.reaction_btn) void onReactionBtnClicked() {
    ReactionController.ONLY.onUserClickButton();
  }

  public void notifyReactionTime(long reactionTime) {
    Toast.makeText(this, String.format("Your reaction time is %d ms", reactionTime),
        Toast.LENGTH_SHORT).show();
  }

  public void showAlertBtn() {
  }

  public void showNormalBtn() {
  }
}
