package hackmate.rapeprevention;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Controller.StartupController;
import hackmate.rapeprevention.Models.DrunkAction;

public class StartupActivity extends Activity{
  @Bind(R.id.enter_user_name) EditText username;
  StartupController controller = new StartupController();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_startup);
    ButterKnife.bind(this);

    controller.takeActivity(this);
    username.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
            && event.getAction() == KeyEvent.ACTION_DOWN)) {
          onNextClicked();
          return true;
        }
        return false;
      }
    });
  }

  @OnClick(R.id.next) void onNextClicked() {
    DrunkAction.drunkAction();
<<<<<<< HEAD
    if (!username.getText().toString().isEmpty()) {
      controller.onUserNameEntered(username.getText().toString());
    }

=======
    /*if (!username.getText().toString().isEmpty()) {
      controller.onUserNameEntered(username.getText().toString());
    }*/
>>>>>>> 5caa13e8224afc08277bf006757742f91fc90707
  }
}
