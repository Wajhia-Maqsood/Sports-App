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
 * Created by wajhia on 5/8/2016.
 */
public class SaveAsyncTask2 extends AsyncTask<Numbers, Void, ArrayList<Numbers>> {

static String server_output = "";
static String temp_output = "";
        ArrayList<Numbers> mycontacts;
@Override
public ArrayList<Numbers> doInBackground(Numbers... arg0) {
        mycontacts = new ArrayList<Numbers>();
        Log.i("do in background", "true");
        try {
        // Numbers number = arg0[0];

        //  Log.i("hellll", "true" + arg0[0]);
        Log.i("do in background", "true");
        QueryBuilder qb = new QueryBuilder();
        URL url = new URL(qb.buildContactsSaveURL());
        HttpURLConnection conn = (HttpURLConnection) url
        .openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
        Log.i("warning status code",""+conn.getResponseCode());
        throw new RuntimeException("Failed : HTTP error code : "
        + conn.getResponseCode());

        }
        Log.i("warning status code",""+conn.getResponseCode());
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
        Log.i("warning dbobj", "" + dbObj);
        for (Object obj : contacts) {


        DBObject userObj = (DBObject) obj;
        document =  userObj.get("document");
        Log.i("warning obj", "Hello" + userObj);
        Numbers temp = new Numbers();

        Object doc=userObj.get("document");
        DBObject dbObj2 = (DBObject) doc;

        //  Log.i("warning temp", "Hello document " + dbObj2);
        temp.setemail(dbObj2.get("Email").toString());
        temp.setpass(dbObj2.get("Password").toString());
        temp.setphone(dbObj2.get("Phone").toString());
        temp.sethouse(dbObj2.get("House").toString());



        // Log.i("warning temp2", "Hello " + temp.getlattitude());
        mycontacts.add(temp);

        }

        }catch (Exception e) {
        Log.i("Exception", "true" + e);
        e.getMessage();
        }
        Log.i("warning in save", "Hello");
        return mycontacts;
        }
        }

