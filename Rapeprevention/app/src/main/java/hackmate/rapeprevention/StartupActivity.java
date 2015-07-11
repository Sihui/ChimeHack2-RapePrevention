package hackmate.rapeprevention;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Models.GPSTracker;
import hackmate.rapeprevention.Models.SMS;

public class StartupActivity extends ActionBarActivity {
  @Bind(R.id.go_to_reaction) Button btn;
  @Bind(R.id.go_to_contact) Button btn2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_startup);
    ButterKnife.bind(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_startup, menu);
    return true;
  }

  @OnClick(R.id.go_to_reaction) void onGotoBtnClick() {
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
