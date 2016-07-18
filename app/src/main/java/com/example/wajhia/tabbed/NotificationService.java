package com.example.wajhia.tabbed;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class NotificationService extends Service
		implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener
	{

	final static String MY_ACTION = "MY_ACTION";

	private static final String TAG = "BOOMNS";
	private LocationManager mLocationManager = null;
	private static final int LOCATION_INTERVAL = 10000;
	private static final float LOCATION_DISTANCE = 0f;
		public static Location location_value;
	private GoogleApiClient mGoogleApiClient;
	private LocationRequest mLocationRequest;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//      Overriden methods for location requests
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void onConnected(@Nullable Bundle bundle) {
		Log.d(TAG, " made it hereee");
		try {
			LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
			Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
			if (location == null) {
				// Blank for a moment...
				LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
			} else {
				Intent intent = new Intent();

				intent.putExtra("proceed", true);
				
				intent.setAction(MY_ACTION);
				sendBroadcast(intent);
			}
		} catch (SecurityException se)
		{
			Log.d(TAG, se.toString());
		}

		Log.d(TAG, "asd");
	}

	@Override
	public void onConnectionSuspended(int i) {

	}

	@Override
	public void onLocationChanged(Location location) {
		Log.e(TAG, "onLocationChanged: " + location);
		//mLastLocation.set(location);
		Intent intent = new Intent();
		intent.putExtra("proceed", true);
		intent.setAction(MY_ACTION);
		sendBroadcast(intent);
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
		Log.d(TAG, " made it here instead");

		if (connectionResult.hasResolution()) {
			Intent intent = new Intent();
			intent.putExtra("proceed", false);

			connectionResult.getResolution();

		} else {
			Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
		}
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


   @Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "onStartCommand");
		super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}

	@Override
	public void onCreate() {
		Log.e(TAG, "onCreate");

		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API)
				.build();

		mLocationRequest = LocationRequest.create()
			.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
			.setInterval(10 * 1000)        // 10 seconds, in milliseconds
			.setFastestInterval(1 * 1000); // 1 second, in milliseconds

		mGoogleApiClient.connect();
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}
}