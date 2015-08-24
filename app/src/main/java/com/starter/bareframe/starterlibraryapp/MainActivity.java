package com.starter.bareframe.starterlibraryapp;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private TabHost mTabHost;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //---------------------Setting up the tabs---------------------------------
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        TabHost.TabSpec spec;
        spec = mTabHost.newTabSpec("tab_1").setIndicator("Tab 1").setContent(R.id.tab_1);
        mTabHost.addTab(spec);
        mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(0xffffff);

        spec = mTabHost.newTabSpec("tab_2").setIndicator("Tab 2").setContent(R.id.tab_2);
        mTabHost.addTab(spec);
        mTabHost.getTabWidget().getChildAt(1).setBackgroundColor(0xffffff);

        spec = mTabHost.newTabSpec("tab_3").setIndicator("Tab 3").setContent(R.id.tab_3);
        mTabHost.addTab(spec);
        mTabHost.getTabWidget().getChildAt(2).setBackgroundColor(0xffffff);


        //---------------------END: Setting up the tabs---------------------------------

        //CHARLEY: Getting rid of the dividers between tabs
        mTabHost.getTabWidget().setDividerDrawable(null);

        //CHARLEY: Change all the tab text colors to grey and change font style
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTypeface(Typeface.create("NORMAL", 0), 0);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,11);
            tv.setTransformationMethod(null);
            tv.setGravity(Gravity.CENTER);
        }

        //CHARLEY: Change the initial tab to light grey
        TextView initialView = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        initialView.setTextColor(Color.parseColor("#bababa"));


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //CHARLEY: Change current tab's text color to light grey
                int currentTab = mTabHost.getCurrentTab();
                TextView view = (TextView) mTabHost.getTabWidget().getChildAt(currentTab).findViewById(android.R.id.title);
                view.bringToFront();
                view.setTextColor(Color.parseColor("#bababa"));
                //CHARLEY: Set all the rest of the tab's text color to grey
                for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                    if (mTabHost.getTabWidget().getChildAt(i) != mTabHost.getTabWidget().getChildAt(currentTab)) {
                        TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                        tv.setTextColor(Color.parseColor("#000000"));
                    }
                }
            }
        });

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
