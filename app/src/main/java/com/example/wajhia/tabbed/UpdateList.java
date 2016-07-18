package com.example.wajhia.tabbed;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by wajhia on 5/7/2016.
 */
public class UpdateList extends Activity implements View.OnClickListener {
    house_score house;
    TextView red,blue,green,yellow;
    Button update;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        red=(TextView) findViewById(R.id.input_red);
        blue=(TextView) findViewById(R.id.input_blue);
        green=(TextView) findViewById(R.id.input_green);
        yellow=(TextView) findViewById(R.id.input_yellow);
        update= (Button) findViewById(R.id.update_score);
        update.setOnClickListener(this);

    }
    public void onClick(View view) {

        house= new house_score();
        house.Red= red.getText().toString();
        house.Green= green.getText().toString();
        house.Yellow= yellow.getText().toString();
        house.Blue= blue.getText().toString();
        Add_Score tsk = new Add_Score();
        tsk.execute(house);
        Toast.makeText(UpdateList.this, "Score added successfully", Toast.LENGTH_SHORT).show();

    }
    public String house(){
        return String
                .format("{\"document\"  : {\"Green\": \"%s\", "
                                + "\"Blue\": \"%s\", "
                                + "\"Yellow\": \"%s\", "
                                + "\"Red\": \"%s\"}, \"safe\" : true}",
                        house.Green, house.Blue, house.Yellow, house.Red);
    }

    private class Add_Score extends AsyncTask<house_score, Void, Boolean> {

        @Override
        protected Boolean doInBackground(house_score... arg0) {
            try {
                house_score match = arg0[0];
                String data=house();
                BuildQuery_match qb = new BuildQuery_match();

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(qb.buildanotherURL());

                StringEntity params = new StringEntity(data);
                request.addHeader("content-type", "application/json");
                request.setEntity(params);
                HttpResponse response = httpClient.execute(request);

                if (response.getStatusLine().getStatusCode() < 205) {
                    Log.w("Warnin", "true" + response.getStatusLine().getStatusCode());
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
}
