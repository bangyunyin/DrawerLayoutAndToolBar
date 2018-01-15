package com.example.yinbangyun.drawerlayoutandtoolbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yinbangyun on 2018/1/16.
 */

public class ContentFragment extends Fragment {

    public static final String TITLE_KEY = "title_key";

    public static ContentFragment createContentFragment(String titile) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, titile);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        String titile = getArguments().getString(TITLE_KEY);
        tv.setTextSize(40);
        tv.setGravity(Gravity.CENTER);
        if (!TextUtils.isEmpty(titile)) {
            tv.setText(titile);
        } else {
            tv.setText("NO VALUE");
        }

        return tv;
    }
}
