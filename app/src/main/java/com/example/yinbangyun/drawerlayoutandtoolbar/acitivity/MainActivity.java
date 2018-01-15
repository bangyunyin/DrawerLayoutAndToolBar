package com.example.yinbangyun.drawerlayoutandtoolbar.acitivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;

import com.example.yinbangyun.drawerlayoutandtoolbar.R;
import com.example.yinbangyun.drawerlayoutandtoolbar.fragment.ContentFragment;
import com.example.yinbangyun.drawerlayoutandtoolbar.fragment.LeftMenuFragment;

import java.util.List;

/**
 * Created by yinbangyun on 2018/1/16.
 */

public class MainActivity extends AppCompatActivity implements LeftMenuFragment.MenuItemOnClickListener{
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private static final String ACTIVITY_TITILE_KEY = "activity_title_key";
    private String mTitile;
    private ContentFragment mCurrentFragment;
    private LeftMenuFragment mLeftFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (true) {
//            getSupportFragmentManager().beginTransaction().add(R.id.content_fragment,new LeftMenuFragment()).commit();
//            return;
//
//        }


        initToolBar();
        initViews();

       // mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.LEFT);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, mToolBar,
                R.string.open,R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        restoreTitle(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        mCurrentFragment = (ContentFragment) fm.findFragmentByTag(mTitile);

        if (null == mCurrentFragment) {
            mCurrentFragment = ContentFragment.createContentFragment(mTitile);
            fm.beginTransaction().add(R.id.content_fragment, mCurrentFragment, mTitile).commit();
        }

        mLeftFragment = (LeftMenuFragment)fm.findFragmentById(R.id.left_fragment);
        if (null == mLeftFragment) {
            mLeftFragment = new LeftMenuFragment();
            fm.beginTransaction().add(R.id.left_fragment, mLeftFragment).commit();
        }

        List<Fragment> fragments = fm.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment == mCurrentFragment || fragment == mLeftFragment)
                continue;

            fm.beginTransaction().hide(fragment).commit();
        }

        mLeftFragment.setMenuOnClickListener(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString(ACTIVITY_TITILE_KEY, mTitile);
    }

    private void restoreTitle(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            mTitile = savedInstanceState.getString(ACTIVITY_TITILE_KEY);
        }
        if (TextUtils.isEmpty(mTitile)) {
            mTitile = getResources().getStringArray(R.array.left_menu)[0];
        }
        mToolBar.setTitle(mTitile);
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
    }

    private void initToolBar() {
        mToolBar = (Toolbar)findViewById(R.id.tool_bar);
        mToolBar.setTitle(getResources().getStringArray(R.array.left_menu)[0]);
        setSupportActionBar(mToolBar);

    }


    @Override
    public void onMenuClick(String text) {
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment fragmentByTag = (ContentFragment) fm.findFragmentByTag(text);
        if (fragmentByTag == mCurrentFragment) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(mCurrentFragment);

        if (null == fragmentByTag) {
            fragmentByTag = ContentFragment.createContentFragment(text);
            ft.add(R.id.content_fragment,fragmentByTag,text);
        } else {
            ft.show(fragmentByTag);
        }
        ft.commit();

        mCurrentFragment = fragmentByTag;
        mTitile = text;
        mToolBar.setTitle(mTitile);
        mDrawerLayout.closeDrawer(Gravity.LEFT);

    }
}
