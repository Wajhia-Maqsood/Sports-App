package com.example.wajhia.tabbed;

/**
 * Created by wajhia on 8/4/2015.
 */

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
        List<Address> destinations = new ArrayList<Address>();
    String locality=" ,islamabad,Pakistan";
    List<String>  data = new ArrayList<>();
    String color_red, color_green, color_yellow, color_blue;
    public static double currentLatitude = 0;
    public static double currentLongitude = 0;
    float [] results = new float[1];
    private static String SENT = "SMS_SENT";
    private static String DELIVERED = "SMS_DELIVERED";
    private static int MAX_SMS_MESSAGE_LENGTH = 160;
    public static String hours;
    public static String minutes;
    public static String time, day, month, year;
    LatLng myposition;





    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            setUpMapIfNeeded();
            color_red="red";
            color_blue= "blue";
        color_green="green";
        color_yellow="yellow";

        show_my_location();
            name_to_cordinates();
            show_on_map();
        mMap.setOnMarkerClickListener(this);


        }
    public void show_my_location(){
        myposition = new LatLng(MainActivity.location_value.getLatitude(),MainActivity.location_value.getLongitude());
        mMap.addMarker(new MarkerOptions().position(myposition).title("My Position").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(myposition,25);
        mMap.animateCamera(yourLocation);
        currentLatitude = MainActivity.location_value.getLatitude();
        currentLongitude = MainActivity.location_value.getLongitude();

    }


        @Override
        protected void onResume() {
            super.onResume();
            setUpMapIfNeeded();
        }

        /**
         * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
         * installed) and the map has not already been instantiated.. This will ensure that we only ever
         * call {@link #setUpMap()} once when {@link #mMap} is not null.
         * <p/>
         * If it isn't installed {@link SupportMapFragment} (and
         * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
         * install/update the Google Play services APK on their device.
         * <p/>
         * A user can return to this FragmentActivity after following the prompt and correctly
         * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
         * have been completely destroyed during this process (it is likely that it would only be
         * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
         * method in {@link #onResume()} to guarantee that it will be called.
         */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain thek2 map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.setMyLocationEnabled(true);

    }
    public void show_on_map(){





        final LatLngBounds.Builder builder = new LatLngBounds.Builder();

        Log.i("size",""+destinations.size());
        for (int i=0; i<destinations.size();i++) {
            Location.distanceBetween(currentLatitude, currentLongitude, destinations.get(i).getLatitude(), destinations.get(i).getLongitude(), results);
            //Toast.makeText(this, String.valueOf(results[0]), Toast.LENGTH_LONG).show();
            time = data.get(i);
            int start = time.indexOf("Time");
            hours = time.substring(start + 5, start + 7);
            hours=hours.replace(":", "");
            minutes = time.substring(start+9, start+11);
            minutes = minutes.replaceAll("\\s+","");
            day = time.substring(6, 8);
            day=day.replaceAll("\\s+","");
            month = time.substring(11, 13);
            month=month.replaceAll("\\s+", "");
            year = time.substring(15, 19);

            Toast.makeText(this, String.valueOf(day), Toast.LENGTH_LONG).show();
            Toast.makeText(this, String.valueOf(month), Toast.LENGTH_LONG).show();
            Toast.makeText(this, String.valueOf(year), Toast.LENGTH_LONG).show();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.set(Calendar.YEAR, Integer.parseInt(year));
            c.set(Calendar.MONTH, Integer.parseInt(month));
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
            c.set(Calendar.MINUTE, Integer.parseInt(minutes));
            c.set(Calendar.SECOND, 0);
            long milliseconds = c.getTimeInMillis() - System.currentTimeMillis();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
            Toast.makeText(this, minutes + "", Toast.LENGTH_LONG).show();

            if (minutes > 0 && results[0] < 100) {
                sendSMS(LoginActivity.phoneNumber, "You have a sports event nearby! Details:\n" + data.get(i));
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(LocationActivity.this, LocationActivity.class);
                PendingIntent pendingNotificationIntent = PendingIntent.getActivity(LocationActivity.this, 1, intent, 0);
                Notification.Builder builder1 = new Notification.Builder(LocationActivity.this);
                builder1.setSmallIcon(R.drawable. index1)
                        .setContentTitle("Sports Gala")
                        .setContentText("You have a sports event nearby! Details:\n" + data.get(i))
                        .setContentIntent(pendingNotificationIntent);
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder1.setSound(alarmSound);
                builder1.setAutoCancel(true);

                Notification notification = builder1.getNotification();
                notificationManager.notify(R.drawable.index1, notification);
            }

                Log.i("LatLng", "lattitudes and longtitudes");
                    Log.i("coordinattes",""+destinations.get(i));
                final LatLng position = new LatLng(destinations.get(i).getLatitude(), destinations.get(i).getLongitude());
                final MarkerOptions options = new MarkerOptions().position(position).title(data.get(i));
                if(LoginActivity.house.equalsIgnoreCase(color_red)){
                    mMap.addMarker(options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED  )));
                    builder.include(position);
                }
            else if(LoginActivity.house.equalsIgnoreCase(color_green)){
                    mMap.addMarker(options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN )));
                    builder.include(position);
                }
                else if(LoginActivity.house.equalsIgnoreCase(color_yellow)){
                    mMap.addMarker(options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW )));
                    builder.include(position);
                }
                else if(LoginActivity.house.equalsIgnoreCase(color_blue)){
                    mMap.addMarker(options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE )));
                    builder.include(position);
                }


               // i++;
            }

    }



    public void name_to_cordinates() {

        try {
            Geocoder geo = new Geocoder(this.getApplicationContext(), Locale.getDefault());
            //  List<Address> addresses = geo.getFromLocationName(loc1, 1);
            //Toast.makeText(LocationActivity.this, "Converting to coordinates", Toast.LENGTH_SHORT).show();
            Address dest1 = null;

            for (Matches x : MainActivity.returnValues) {
                if (x.gethouse().equalsIgnoreCase(LoginActivity.house)) {

                    String name=x.getlocation().toUpperCase().concat(locality);
                    Log.i("add",name);
                    List<Address> addresses = geo.getFromLocationName(name, 1);
                    Log.i("address",""+addresses);
                    if (addresses != null && addresses.size() > 0) {
                        dest1 = addresses.get(0);
                        destinations.add(dest1);
                        data.add("Date :" + x.getdate() + "\t Time:" + x.gettime() + " Location: " + x.getlocation());

                        Log.d("Markers", "dest1: " + dest1.getAddressLine(0) + " - " + dest1.getLatitude() + ", " + dest1.getLongitude());
                    } else {
                        Log.e("no location", "Couldn't reverse geocode dest1");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(!(marker.getTitle().equals("My Position"))){
            Log.i("Title",""+marker.getTitle());
            time = marker.getTitle();
            int start = time.indexOf("Time");
            hours = time.substring(start + 5, start + 7);
            hours=hours.replace(":", "");
            minutes = time.substring(start+9, start + 11);
            minutes = minutes.replaceAll("\\s+","");
            day = time.substring(6, 8);
            day=day.replaceAll("\\s+","");
            month = time.substring(11, 13);
            month=month.replaceAll("\\s+","");
            year = time.substring(15, 19);

            Toast.makeText(this, String.valueOf(day), Toast.LENGTH_LONG).show();
            Toast.makeText(this, String.valueOf(month), Toast.LENGTH_LONG).show();
            Toast.makeText(this, String.valueOf(year), Toast.LENGTH_LONG).show();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.set(Calendar.YEAR, Integer.parseInt(year));
            c.set(Calendar.MONTH, Integer.parseInt(month));
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
            c.set(Calendar.MINUTE, Integer.parseInt(minutes));
            c.set(Calendar.SECOND, 0);
            long milliseconds = c.getTimeInMillis() - System.currentTimeMillis();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
            Toast.makeText(this, minutes + "", Toast.LENGTH_LONG).show();

            if (minutes < 0) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
                Intent i = new Intent(getApplicationContext(),AlarmService.class);
                PendingIntent p = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
                alarmManager.cancel(p);
                p.cancel();
                marker.remove();
            }
            else if(minutes > 0){
                new AlertDialog.Builder(LocationActivity.this)
                        .setTitle("Info")
                        .setMessage(marker.getTitle())
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setPositiveButton("Set Reminder", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                setalarm();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();}

        }


        return true;
    }
    public void setalarm(){
        startActivity(new Intent(LocationActivity.this, ReminderTime.class));

        //Toast.makeText(MapsActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();
    }
    public void sendSMS(String phoneNumber, String message) {
        PendingIntent piSent = PendingIntent.getBroadcast(getApplicationContext(),
                0, new Intent(SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(getApplicationContext(),
                0, new Intent(DELIVERED), 0);
        SmsManager smsManager = SmsManager.getDefault();
        int length = message.length();

        if (length > MAX_SMS_MESSAGE_LENGTH) {
            ArrayList<String> messagelist = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null,
                    messagelist, null, null);
        } else {
            smsManager.sendTextMessage(phoneNumber, null, message,
                    piSent, piDelivered);
        }

    }
}
