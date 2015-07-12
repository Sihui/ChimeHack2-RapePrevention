package hackmate.rapeprevention;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Controller.ReactionController;


public class ReactionActivity extends Activity {

  public Handler handler = new Handler();
  boolean mShowingBack = false;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reaction);
    ButterKnife.bind(this);
    ReactionController.ONLY.takeActivity(this);
    if (savedInstanceState == null) {
      getFragmentManager()
              .beginTransaction()
              .add(R.id.container, new CardFrontFragment())
              .commit();
    }
    super.onCreate(savedInstanceState);



  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ReactionController.ONLY.dropActivity();
  }

  public void changeSignalColor() {
  }

  public void resetSignalColor() {
  }

  @OnClick(R.id.reaction_btn) public void onReactBtnClicked() {
    //ReactionController.ONLY.onUserClickButton();
    showTimer();
  }

  public void notifyReactionTime(long reactionTime) {
    Toast.makeText(this, String.format("Your reaction time is %d ms", reactionTime),
        Toast.LENGTH_SHORT).show();
  }

  public void showTimer(){
    //signalImage.setVisibility(ImageView);
    //YoYo.with(Techniques.).duration(1000).playOn(signalImage);
    flipCard();
    /*coundownTimer.setVisibility(ImageView.VISIBLE);
    YoYo.with(Techniques.FlipInX).duration(1000).playOn(coundownTimer);*/
  }

  /**
   * A fragment representing the front of the card.
   */
  public static class CardFrontFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_card_front, container, false);
    }
  }

  /**
   * A fragment representing the back of the card.
   */
  public static class CardBackFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_card_back, container, false);
    }
  }

  private void flipCard() {
    if (mShowingBack) {
      getFragmentManager().popBackStack();
      return;
    }

    // Flip to the back.

    mShowingBack = true;

    // Create and commit a new fragment transaction that adds the fragment for the back of
    // the card, uses custom animations, and is part of the fragment manager's back stack.

    getFragmentManager()
            .beginTransaction()

                    // Replace the default fragment animations with animator resources representing
                    // rotations when switching to the back of the card, as well as animator
                    // resources representing rotations when flipping back to the front (e.g. when
                    // the system Back button is pressed).
            .setCustomAnimations(
                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                    // Replace any fragments currently in the container view with a fragment
                    // representing the next page (indicated by the just-incremented currentPage
                    // variable).
            .replace(R.id.container, new CardBackFragment())

                    // Add this transaction to the back stack, allowing users to press Back
                    // to get to the front of the card.
            .addToBackStack(null)

                    // Commit the transaction.
            .commit();
  }
}
