package hackmate.rapeprevention;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Controller.ReactionController;

public class ReactionActivity extends Activity {
  @Bind(R.id.reaction_btn) ImageView reactionBtn;
  @Bind(R.id.title) TextView title;
  public Handler handler = new Handler();
  private ReactionController controller;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reaction);
    ButterKnife.bind(this);
    controller = ReactionController.ONLY;
    controller.takeActivity(this);
    controller.onReceiveIntent(getIntent());
    super.onCreate(savedInstanceState);
  }

  @Override protected void onStop() {
    super.onStop();
    controller.dropActivity();
  }

  @OnClick(R.id.reaction_btn) void onReactionBtnClicked() {
    controller.onUserClickButton();
  }

  public void notifyReactionTime(long reactionTime) {
    Toast.makeText(this, String.format("Your reaction time is %d ms", reactionTime),
        Toast.LENGTH_SHORT).show();
  }

  public void showAlertBtn() {
    reactionBtn.setImageDrawable(getResources().getDrawable(R.drawable.circle_red));
  }

  public void showNormalBtn() {
    reactionBtn.setImageDrawable(getResources().getDrawable(R.drawable.circle_green));
  }

  public void setTitle(String title) {
    this.title.setText(title);
  }
}
