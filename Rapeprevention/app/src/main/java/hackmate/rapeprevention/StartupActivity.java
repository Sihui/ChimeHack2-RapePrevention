package hackmate.rapeprevention;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Controller.StartupController;
import hackmate.rapeprevention.Models.GPSTracker;
import hackmate.rapeprevention.Models.SMS;

public class StartupActivity extends ActionBarActivity {
  @Bind(R.id.go_to_reaction) Button btn;
  @Bind(R.id.go_to_contact) Button btn2;
  @Bind(R.id.enter_user_name) EditText username;
  StartupController controller = new StartupController();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_startup);
    ButterKnife.bind(this);

    controller.takeActivity(this);
    username.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
            (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
          controller.onUserNameEntered(username.getText().toString());
          return true;
        }
        return false;
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_startup, menu);
    return true;
  }

  @OnClick(R.id.go_to_reaction) void onGotoBtnClick() {
      final Handler handler = new Handler();
      final long intervalTime = 1000;
      handler.postDelayed(new Runnable() {
          @Override
          public void run() {
              System.out.println("What's up");
              handler.postDelayed(this, intervalTime);
          }
      }, intervalTime);
      SMS.sendSMS("18329752606", "haha" + GPSTracker.getAddress(this), this);
    //startActivity(new Intent(this, ReactionActivity.class));
  }

  @OnClick(R.id.go_to_contact) void onGotoBtnClick2() {
    Log.w("debug", "go to contact");
    startActivity(new Intent(this, AddContactActivity.class));
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
