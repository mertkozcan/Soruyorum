package com.bmyazilim.soruyorum;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager vp;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;


    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         vp=(ViewPager)findViewById(R.id.vp);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(0).setText("Anasayfa");
        tabLayout.getTabAt(1).setText("Keşfet");
        tabLayout.getTabAt(2).setText("Profil");

    }


    @Override
    public void onBackPressed() {
        if (vp.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            vp.setCurrentItem(vp.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {

                case 0: return new HomeFragment();
                case 1: return new HomeFragment();
                case 2: return new HomeFragment();
                default: return new HomeFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
