package com.example.wajhia.tabbed;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wajhia.tabbed.Numbers;

import java.util.ArrayList;
/**
 * Created by wajhia on 5/8/2016.
 */
public class SaveAsyncTask extends AsyncTask<Numbers, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Numbers... arg0) {
        try {
            Numbers number = arg0[0];

            QueryBuilder qb = new QueryBuilder();

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(qb.buildContactsSaveURL());

            StringEntity params = new StringEntity(qb.createContact(number));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() < 205) {
                Log.w("Warnin", "true");
                return true;
            } else {
                Log.w("Warnin", "false");
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
