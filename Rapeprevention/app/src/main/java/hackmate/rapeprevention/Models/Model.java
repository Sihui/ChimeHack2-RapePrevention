package hackmate.rapeprevention.Models;

import android.location.Location;
import java.util.ArrayList;
import java.util.List;

public class Model {
  public Location userLocation;
  public String userName;
  public List<Integer> reactionTime = new ArrayList<>();

  private Model() {}
  private static Model ONLY = new Model();
  public static Model getModel() {
    return ONLY;
  }
}
