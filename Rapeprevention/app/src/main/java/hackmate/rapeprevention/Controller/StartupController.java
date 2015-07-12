package hackmate.rapeprevention.Controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import hackmate.rapeprevention.AddContactActivity;
import hackmate.rapeprevention.IntroActivity;
import hackmate.rapeprevention.Models.Model;
import hackmate.rapeprevention.StartupActivity;
import java.util.HashSet;
import java.util.Set;

public class StartupController extends Controller<StartupActivity> {
  private static final String USERNAME = "username";
  public void onUserNameEntered(String name) {

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
    Set<String> loggedUserName = prefs.getStringSet(USERNAME, new HashSet<String>());
    Model.getModel().userName.set(name);
    if (loggedUserName.contains(name)) {
      Log.d("StartUP", "Received old user name: " + name);
      startActivity(AddContactActivity.class);
    } else {
      Log.d("StartUP", "Received new user name: " + name);
      loggedUserName.add(name);
      prefs.edit().putStringSet(USERNAME, loggedUserName).apply();
      startActivity(IntroActivity.class);
    }
  }
}
