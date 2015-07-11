package hackmate.rapeprevention.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

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

  public Context getContext() {
    return getActivity();
  }

  public void startActivity(Class<? extends Activity> clazz) {
    Intent intent = new Intent(getActivity(), clazz);
    getActivity().startActivity(intent);
  }
}
