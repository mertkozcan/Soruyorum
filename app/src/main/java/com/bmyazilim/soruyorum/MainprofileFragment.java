package com.bmyazilim.soruyorum;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bmyazilim.soruyorum.models.Users;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;
import static com.bmyazilim.soruyorum.LoginActivity.PREFS_NAME;

/**
 * Created by berna on 13.04.2017.
 */

public class MainprofileFragment extends Fragment {

    VolleyStuff volleyStuff;
     ImageView profilePicture;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_mainprofile,null);

        SharedPreferences pref = getActivity().getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String mail = pref.getString("mail", null);
        String password = pref.getString("password", null);

      //  profilePicture=(ImageView) myView.findViewById(R.id.profilePicture);


        volleyStuff=new VolleyStuff();

        volleyStuff.getProfileInfo(mail, password, new VolleyStuff.jsonVolleyCallback() {
            @Override
            public void onSuccess(Users model) {

                volleyStuff.getProfilePicture(model.profilePictureID, new VolleyStuff.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {

    //                    Picasso.with(getContext()).load("http://soruyorum.bmyazilim.net/uploads/"+result).resize(200,200).centerCrop().into(profilePicture);
                    }
                });


            }
        });


        return myView;
    }



}
