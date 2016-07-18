package com.example.wajhia.tabbed;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class Add_match extends AsyncTask<Matches, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Matches... arg0) {
        try {
            Matches match = arg0[0];

            BuildQuery_match qb = new BuildQuery_match();
            create query = new create();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(qb.buildURL());

            StringEntity params = new StringEntity(query.createMatch(match));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() < 205) {
                Log.w("Warnin", "true"+response.getStatusLine().getStatusCode());
                return true;
            } else {
                Log.w("Warnin", "false"+response.getStatusLine().getStatusCode());
                return false;
            }
        } catch (Exception e) {
            //e.getCause();
            String val = e.getMessage();
            String val2 = val;
            Log.w("Warnin", val2);
            return false;
        }
    }
}
