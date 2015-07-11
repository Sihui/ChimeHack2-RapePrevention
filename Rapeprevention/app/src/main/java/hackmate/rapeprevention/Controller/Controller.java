package hackmate.rapeprevention.Controller;

import android.app.Activity;

public class Controller<T extends Activity> {
  Object activity;

  /** Get activity attached to the controller */
  protected T getActivity() {
    return (T) activity;
  }

  /** Make controller control the activity */
  public void takeActivity(T activity) {
    this.activity = activity;
  }

  /** Drop activity */
  public void dropActivity() {
    this.activity = null;
  }

  public boolean hasActivity() {
    return activity != null;
  }
}
