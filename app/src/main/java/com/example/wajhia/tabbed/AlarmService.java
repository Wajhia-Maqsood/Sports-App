package com.example.wajhia.tabbed;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;

import android.content.Context;
import android.content.Intent;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import android.widget.Toast;

import com.example.wajhia.tabbed.LocationActivity;


public class AlarmService extends Service {



    @Override

    public void onCreate() {

// TODO Auto-generated method stub

        //Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(AlarmService.this, LocationActivity.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(AlarmService.this, 1, intent, 0);
        Notification.Builder builder = new Notification.Builder(AlarmService.this);
        builder.setSmallIcon(R.drawable. index1)
                .setContentTitle("Sports Gala")
                .setContentText("You have a sports event to get to! Details:\n" + LocationActivity.time)
                .setContentIntent(pendingNotificationIntent);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        builder.setAutoCancel(true);

        Notification notification = builder.getNotification();
        notificationManager.notify(R.drawable.index1, notification);

    }



    @Override

    public IBinder onBind(Intent intent) {

// TODO Auto-generated method stub

        //Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();

        return null;

    }



    @Override

    public void onDestroy() {

// TODO Auto-generated method stub

        super.onDestroy();

        //Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();

    }



    @Override

    public void onStart(Intent intent, int startId) {

// TODO Auto-generated method stub

        super.onStart(intent, startId);

        //Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();

    }



    @Override

    public boolean onUnbind(Intent intent) {

// TODO Auto-generated method stub

        //Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();

        return super.onUnbind(intent);

    }



}