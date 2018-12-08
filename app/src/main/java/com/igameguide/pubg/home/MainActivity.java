package com.igameguide.pubg.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.igameguide.pubg.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter mPresenter;
    ViewPager viewPager;
    CommonTabLayout tabLayout;

    private String[] mTitles = new String[]{"战绩"};
    private List<Fragment> mFragments;
    private int[] mIconUnselectIds = {
            R.mipmap.icon_data_unsel, R.mipmap.icon_guides_unsel,
            R.mipmap.icon_video_unsel, R.mipmap.icon_weapon_unsel, R.mipmap.icon_wallpaper_nusel};
    private int[] mIconSelectIds = {
            R.mipmap.icon_data_sel, R.mipmap.icon_guides_sel,
            R.mipmap.icon_video_sel, R.mipmap.icon_weapon_sel, R.mipmap.icon_wallpaper_sel};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MainPresenter(this);


        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);


        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            String title = mTitles[i];
            if (i == 0) {
                mFragments.add(StandingsFragment.getInstance());

            } else {
                mFragments.add(SimpleCardFragment.getInstance(title));
            }

            mTabEntities.add(new TabEntity(title, mIconSelectIds[i], mIconUnselectIds[i]));
        }

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                tabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });





    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;

    }


    @Override
    public void onLoadStart() {

    }

    @Override
    public void onLoadFail() {

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
