package hackmate.rapeprevention;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hackmate.rapeprevention.Models.HackyViewPager;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class IntroActivity extends Activity {
    private static final String ISLOCKED_ARG = "isLocked";

    private ViewPager mViewPager;
    private MenuItem menuLockItem;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        setContentView(mViewPager);

        mViewPager.setAdapter(new SamplePagerAdapter());

        if (savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG, false);
            ((HackyViewPager) mViewPager).setLocked(isLocked);
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        private  final int[] sDrawables = { R.drawable.intro1, R.drawable.intro2,
                R.drawable.intro3, R.drawable.intro4, R.drawable.intro5};

        @Override
        public int getCount() {
            return sDrawables.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setImageResource(sDrawables[position]);

            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viewpager_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

   /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuLockItem = menu.findItem(R.id.menu_lock);
        toggleLockBtnTitle();
        menuLockItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                toggleViewPagerScrolling();
                toggleLockBtnTitle();
                return true;
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }*/

    private void toggleViewPagerScrolling() {
        if (isViewPagerActive()) {
            ((HackyViewPager) mViewPager).toggleLock();
        }
    }

    private void toggleLockBtnTitle() {
        /*boolean isLocked = false;
        if (isViewPagerActive()) {
            isLocked = ((HackyViewPager) mViewPager).isLocked();
        }
        String title = (isLocked) ? getString(R.string.menu_unlock) : getString(R.string.menu_lock);
        if (menuLockItem != null) {
            menuLockItem.setTitle(title);
        }*/
    }

    private boolean isViewPagerActive() {
        return (mViewPager != null && mViewPager instanceof HackyViewPager);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(ISLOCKED_ARG, ((HackyViewPager) mViewPager).isLocked());
        }
        super.onSaveInstanceState(outState);
    }

}
