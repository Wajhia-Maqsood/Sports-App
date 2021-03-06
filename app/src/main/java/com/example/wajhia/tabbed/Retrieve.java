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

public class Retrieve extends AsyncTask<Matches, Void, ArrayList<Matches>> {

    static String server_output = "";
    static String temp_output = "";
    ArrayList<Matches> matchlist;
    @Override
    public ArrayList<Matches> doInBackground(Matches... arg0) {
        matchlist = new ArrayList<Matches>();
        Log.i("do in background", "true");
        try {
            // Numbers number = arg0[0];

            //  Log.i("hellll", "true" + arg0[0]);
            Log.i("retrieve in background", "true");
            BuildQuery_match qb = new BuildQuery_match();
            URL url = new URL(qb.buildURL());
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
                Matches temp = new Matches();
                //  temp.setDoc_id(userObj.get("_id").toString());

                Object doc=userObj.get("document");
                DBObject dbObj2 = (DBObject) doc;

                //  Log.i("warning temp", "Hello document " + dbObj2);
                temp.setdate(dbObj2.get("Date").toString());
                temp.settime(dbObj2.get("Time").toString());
                temp.setlocation(dbObj2.get("location").toString());
                temp.sethouse(dbObj2.get("House").toString());
                temp.settype(dbObj2.get("Type").toString());
                temp.setsport(dbObj2.get("game").toString());


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
