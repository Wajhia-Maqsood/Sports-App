package com.example.wajhia.tabbed;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TabHost;

/**
 * Created by wajhia on 5/7/2016.
 */
public class AdminActivity extends TabActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TabHost tabHost = getTabHost();
        // Home tab
        Intent intentHome = new Intent().setClass(this, AddSchedule.class);
        TabHost.TabSpec tabSpecHome = tabHost
                .newTabSpec("Add Schedule")
                .setIndicator("Add Schedule")
                .setContent(intentHome);

        // Schedule tab
        Intent intentSchedule = new Intent().setClass(this,DeleteSchedule.class);
        TabHost.TabSpec tabSpecSchedule = tabHost
                .newTabSpec("Delete Schedule")
                .setIndicator("Delete Schedule")
                .setContent(intentSchedule);

        // Score Card tab
        Intent intentScore = new Intent().setClass(this, UpdateList.class);
        TabHost.TabSpec tabSpecScore = tabHost
                .newTabSpec("Update House List")
                .setIndicator("Update House List")
                .setContent(intentScore);

        // Score Card tab
        Intent intentUpdateScore = new Intent().setClass(this, UpdateScoreList.class);
        TabHost.TabSpec tabSpecUpdateScore = tabHost
                .newTabSpec("Update Score")
                .setIndicator("Update Score")
                .setContent(intentUpdateScore);




        // add all tabs
        tabHost.addTab(tabSpecHome);
        tabHost.addTab(tabSpecSchedule);
        tabHost.addTab(tabSpecScore);
        tabHost.addTab(tabSpecUpdateScore);



        //set home tab as default (zero based)
        tabHost.setCurrentTab(0);
    }

}