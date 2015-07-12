package hackmate.rapeprevention;

import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import hackmate.rapeprevention.Controller.TextMessageController;

public class TextMessageActivity extends ActionBarActivity {
  @Bind(R.id.icon) ImageView icon;
  @Bind(R.id.desp) TextView desp;
  TextMessageController controller;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_message);
    controller = TextMessageController.get();
    controller.takeActivity(this);
    getSupportActionBar().setElevation(0);
    getSupportActionBar().setTitle("CALIBRATION");
  }

  @Override protected void onStop() {
    super.onStop();
    controller.dropActivity();
  }

  public void setIcon(@AnyRes int resId) {
    icon.setImageDrawable(getResources().getDrawable(resId));
  }

  public void setDesp(String desp) {
    this.desp.setText(desp);
  }
}
