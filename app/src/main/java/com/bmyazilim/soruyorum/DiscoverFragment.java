package com.bmyazilim.soruyorum;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mertk on 28.04.2017.
 */

public class DiscoverFragment extends Fragment {

    ListView lstCategory;
    VolleyStuff volleyStuff;
    List<Integer> catIds;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_discover,null);
        volleyStuff =new VolleyStuff();
        lstCategory=(ListView)view.findViewById(R.id.lstDiscoverCategory);
        catIds=new ArrayList<>();
        
        volleyStuff.getCategories(new VolleyStuff.CategoryCallback() {
            @Override
            public void onSuccess(List<String> listNames,List<Integer> ListIds) {
                ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,listNames);
                lstCategory.setAdapter(adapter);
                catIds=ListIds;

                volleyStuff.getCategoryFollow(LoginActivity.loggedUser.userID, new VolleyStuff.CategoryCallback() {
                    @Override
                    public void onSuccess(List<String> listNames, List<Integer> listIds) {

                    }

                    @Override
                    public void onSuccess(List<Integer> listIds) {

                        for (int item: listIds
                                ) {

                            for(int i=0;i<catIds.size();i++)
                            {
                                if(item==catIds.get(i))
                                {
                                    lstCategory.getChildAt(i).setBackgroundColor(Color.GRAY);
                                }

                            }

                        }

                    }
                });

            }

            @Override
            public void onSuccess(List<Integer> listIds) {
                
            }
        });
        
        


        lstCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                volleyStuff.categoryFollow(MainActivity.loggedUser.userID, catIds.get(position), new VolleyStuff.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if(result.equals("OK")){

                            lstCategory.getChildAt(position).setBackgroundColor(Color.GRAY);
                        }
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
