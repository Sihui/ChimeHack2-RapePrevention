package hackmate.rapeprevention.Controller;

import hackmate.rapeprevention.R;
import hackmate.rapeprevention.TextMessageActivity;

public class TextMessageController extends Controller<TextMessageActivity> {
  private static TextMessageController ONLY = new TextMessageController();
  private TextMessageController() {}
  public static TextMessageController get() {return ONLY;}

  @Override public void takeActivity(TextMessageActivity activity) {
    super.takeActivity(activity);
  }

  public void confirmPickup() {
    if (hasActivity()) {
      TextMessageActivity activity = getActivity();
      activity.setIcon(R.drawable.car);
      activity.setTitle("Friend is on the way");
    }
  }
}
