package com.example.yinbangyun.drawerlayoutandtoolbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yinbangyun.drawerlayoutandtoolbar.R;

/**
 * Created by yinbangyun on 2018/1/16.
 */

public class LeftMenuFragment extends Fragment {

    private static final int MENU_SIZE = 3;
    private String[] mLeftMenus;
    private ListView mListView;
    private MenuItemOnClickListener mListener;
    public void setMenuOnClickListener(MenuItemOnClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLeftMenus = getResources().getStringArray(R.array.left_menu);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_left, container);
        //mListView = (ListView)container.findViewById(R.id.left_fragment_listView);
        View view = inflater.inflate(R.layout.fragment_left, container,false);
        mListView = view.findViewById(R.id.left_fragment_listView);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.fragment_left_list_item,mLeftMenus));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != mListener) {
                    mListener.onMenuClick(mLeftMenus[position]);
                }
            }
        });
        return view;
    }


    public interface MenuItemOnClickListener{
        void onMenuClick(String text);
    }


}
