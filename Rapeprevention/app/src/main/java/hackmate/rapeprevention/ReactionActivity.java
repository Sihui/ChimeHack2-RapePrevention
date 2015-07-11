package hackmate.rapeprevention;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Controller.ReactionController;

public class ReactionActivity extends Activity {

  @Bind(R.id.reaction_img) ImageView signalImage;

  public Handler handler = new Handler();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reaction);
    ButterKnife.bind(this);
    ReactionController.ONLY.takeActivity(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ReactionController.ONLY.dropActivity();
  }

  public void changeSignalColor() {
    signalImage.setImageDrawable(getResources().getDrawable(R.drawable.circle_red));
  }

  public void resetSignalColor() {
    signalImage.setImageDrawable(getResources().getDrawable(R.drawable.circle_green));
  }

  @OnClick(R.id.reaction_btn) public void onReactBtnClicked() {
    ReactionController.ONLY.onUserClickButton();
  }

  public void notifyReactionTime(long reactionTime) {
    Toast.makeText(this, String.format("Your reaction time is %d ms", reactionTime),
        Toast.LENGTH_SHORT).show();
  }
}
