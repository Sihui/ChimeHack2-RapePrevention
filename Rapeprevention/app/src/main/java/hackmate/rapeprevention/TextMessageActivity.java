package hackmate.rapeprevention;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import hackmate.rapeprevention.Controller.TextMessageController;

public class TextMessageActivity extends ActionBarActivity {

  TextMessageController controller;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_message);
    controller = TextMessageController.get();
    controller.takeActivity(this);
  }

  @Override protected void onStop() {
    super.onStop();
    controller.dropActivity();
  }
}
