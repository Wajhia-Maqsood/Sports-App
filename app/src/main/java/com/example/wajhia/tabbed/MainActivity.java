package com.example.wajhia.tabbed;

import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends TabActivity {
   public static ArrayList<Matches> returnValues;
    public static ArrayList<house_score> scorelist;
    Retrieve task;
    Retriev_score task2;
    private GoogleApiClient mGoogleApiClient;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public static final String TAG = MainActivity.class.getSimpleName();
    private LocationRequest mLocationRequest;
    public static  Location location_value;


    MyReceiver myReceiver;

    //final ActionBar actionBar = getActionBar();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TabHost tabHost = getTabHost();
        // Home tab
        Intent intentHome = new Intent().setClass(this, HomeActivity.class);
        TabSpec tabSpecHome = tabHost
                .newTabSpec("Home")
                .setIndicator("Home")
                .setContent(intentHome);

        // Schedule tab
        Intent intentSchedule = new Intent().setClass(this, ScheduleActivity.class);
        TabSpec tabSpecSchedule = tabHost
                .newTabSpec("Schedule")
                .setIndicator("Schedule")
                .setContent(intentSchedule);

        // Score Card tab
        Intent intentScore = new Intent().setClass(this, ScoreCardActivity.class);
        TabSpec tabSpecScore = tabHost
                .newTabSpec("ScoreCard")
                .setIndicator("Score")
                .setContent(intentScore);



        // SocialMedia tab
        Intent intentLocation= new Intent().setClass(this, LocationActivity.class);
        TabSpec tabSpecLocation = tabHost
                .newTabSpec("Location")
                .setIndicator("Location")
                .setContent(intentLocation);

        // add all tabs
        tabHost.addTab(tabSpecHome);
        tabHost.addTab(tabSpecSchedule);
        tabHost.addTab(tabSpecScore);

        tabHost.addTab(tabSpecLocation);



        //set home tab as default (zero based)
        tabHost.setCurrentTab(0);
        connection();
        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NotificationService.MY_ACTION);
        registerReceiver(myReceiver, intentFilter);
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);

        Log.d("BOOM", "here");

    }
    public void connection(){
        returnValues=new ArrayList<>();
        task= new Retrieve();
        try {
            returnValues = task.execute().get();
            Log.i("values", "" + returnValues);
        } catch (InterruptedException | ExecutionException e) {
            Log.i("task", "" + e);
            e.printStackTrace();
        }
        task2=new Retriev_score();
        try {
            scorelist = task2.execute().get();
            Log.i("values", "" + scorelist);
        } catch (InterruptedException | ExecutionException e) {
            Log.i("task", "" + e);
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return true;
    }

    /**
     * On selecting action bar icons
     * */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:
                //Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        //.show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                //Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        //.show();
                break;
            default:
                break;
        }

        return true;
    }
    boolean started = false, paused = false;



    private class MyReceiver extends BroadcastReceiver {

        String TAG = "BOOM";
        private Context context;
        private Intent intent;

        @Override
        public void onReceive(Context arg0, Intent intent) {
            // TODO Auto-generated method stub


            if(intent.getExtras()!= null)
                if(!intent.getExtras().getBoolean("proceed"))
                    Log.d(TAG, " NO NO NO");

         /*   if(ContextCompat.checkSelfPermission(MainActivity.this,
                    "android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {*/
            try {
               Location  mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                if (mLastLocation != null) {
                    if (started == false)
                    {
                        //first time stuff here

                        started =true;
                        location_value=mLastLocation;
                        Log.i("Location",""+location_value);
                    }
                    else{
                        location_value=mLastLocation;
                        Log.i("Location",""+location_value);
                    }

                    //every subsequent location changed
                }
            } catch (Exception e) {
                Log.d(TAG, " error here x " + e.toString());
            }
           /* }
            else {
                Log.d(TAG, "No Permission for this");
            }*/


        }


    }

}