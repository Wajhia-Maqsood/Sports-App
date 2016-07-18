package com.example.wajhia.tabbed;

import android.os.AsyncTask;
import android.util.Log;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Fizza on 5/18/2016.
 */
public class Retriev_score extends AsyncTask<house_score, Void, ArrayList<house_score>> {

    static String server_output = "";
    static String temp_output = "";
    ArrayList<house_score> matchlist;
    @Override
    public ArrayList<house_score> doInBackground(house_score... arg0) {
        matchlist = new ArrayList<house_score>();
        Log.i("do in background score", "true");
        try {
            // Numbers number = arg0[0];

            //  Log.i("hellll", "true" + arg0[0]);
            Log.i("retrieve in score", "true");
            BuildQuery_match qb = new BuildQuery_match();
            URL url = new URL(qb.buildanotherURL());
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                Log.i("warning status code", "" + conn.getResponseCode());
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());

            }
            Log.i("warning status code", "" + conn.getResponseCode());
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((temp_output = br.readLine()) != null) {
                server_output = temp_output;
            }

            // create a basic db list
            String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
            Object o = com.mongodb.util.JSON.parse(mongoarray);


            DBObject dbObj = (DBObject) o;
            BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");
            Object document ;
            Log.i("retrieve dbobj", "" + dbObj);
            for (Object obj : contacts) {


                DBObject userObj = (DBObject) obj;
                document =  userObj.get("document");
                Log.i("retrieve obj", "Hello" + userObj);
                house_score temp = new house_score();
                //  temp.setDoc_id(userObj.get("_id").toString());

                Object doc=userObj.get("document");
                DBObject dbObj2 = (DBObject) doc;

                //  Log.i("warning temp", "Hello document " + dbObj2);
                temp.setGreen(dbObj2.get("Green").toString());
                temp.setBlue(dbObj2.get("Blue").toString());
                temp.setYellow(dbObj2.get("Yellow").toString());
                temp.setRed(dbObj2.get("Red").toString());



                // Log.i("warning temp2", "Hello " + temp.getlattitude());
                matchlist.add(temp);
                Log.i("Matchlist", ""+matchlist);

            }

        }catch (Exception e) {
            Log.i("Exception", "true" + e);
            e.getMessage();
        }
        Log.i("warning in save", "Hello");
        return matchlist;
    }
}
