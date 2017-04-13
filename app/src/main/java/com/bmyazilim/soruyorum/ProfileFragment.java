package com.bmyazilim.soruyorum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

/**
 * Created by berna on 6.03.2017.
 */

public class ProfileFragment extends Fragment {

    private FragmentTabHost mTabHost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile,container, false);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("profil").setIndicator("Profil"),
                MainprofileFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("sorular").setIndicator("Sorular"),
                QuestionsFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("favorilerim").setIndicator("Favorilerim"),
                FavouritesFragment.class, null);


        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



}
