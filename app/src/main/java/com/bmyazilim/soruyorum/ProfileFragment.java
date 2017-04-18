package com.bmyazilim.soruyorum;

import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TableLayout;

import com.bmyazilim.soruyorum.models.Users;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;
import static com.bmyazilim.soruyorum.LoginActivity.PREFS_NAME;

/**
 * Created by berna on 6.03.2017.
 */

public class ProfileFragment extends Fragment {

    private FragmentTabHost mTabHost;
    VolleyStuff volleyStuff;
    ImageView profilePicture;

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

        SharedPreferences pref = getActivity().getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String mail = pref.getString("mail", null);
        String password = pref.getString("password", null);

          profilePicture=(ImageView) rootView.findViewById(R.id.profilePicture);


        volleyStuff=new VolleyStuff();

        volleyStuff.getProfileInfo(mail, password, new VolleyStuff.jsonVolleyCallback() {
            @Override
            public void onSuccess(Users model) {

                volleyStuff.getProfilePicture(model.profilePictureID, new VolleyStuff.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {

                                        Picasso.with(getContext()).load("http://soruyorum.bmyazilim.net/uploads/"+result).resize(150,150).centerCrop().into(profilePicture);
                    }
                });


            }
        });

        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



}
