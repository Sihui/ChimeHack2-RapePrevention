package hackmate.rapeprevention;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import hackmate.rapeprevention.Controller.TimerController;
import hackmate.rapeprevention.Models.Observable;
import hackmate.rapeprevention.Widgets.NestTimePicker;

public class TimerActivity extends ActionBarActivity {

  @Bind(R.id.timer) NestTimePicker timer;
  TimerController controller;
  public Handler handler = new Handler();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_timer);
    ButterKnife.bind(this);
    controller = TimerController.ONLY;
    timer.setSelected(controller.getTime());
    timer.selectedMinute.addListener(new Observable.Listener<Integer>() {
      @Override public void notifyChange(Integer obj) {
        controller.setTime(obj);
      }
    });
  }
}