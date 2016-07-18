package com.example.wajhia.tabbed;

/**
 * Created by wajhia on 8/4/2015.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView red,blue,green,yellow;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void house_captain(View view) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_captains);
    }
    public void score(View view) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        red=(TextView) findViewById(R.id.Red);
        blue=(TextView) findViewById(R.id.Blue);
        green=(TextView) findViewById(R.id.Green);
        yellow=(TextView) findViewById(R.id.Yellow);
        Show_score();
    }
    public void Show_score(){
        int i= MainActivity.scorelist.size();
        house_score x = MainActivity.scorelist.get(i-1);
        red.setText(x.getRed());
        blue.setText(x.getBlue());
        green.setText(x.getGreen());
        yellow.setText(x.getYellow());


    }
    public void back(View view) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void team_blue(View view) {
        setContentView(R.layout.content_blue_teams);
    }

    public void team_red(View view) {
        setContentView(R.layout.content_red_teams);
    }

    public void team_green(View view) {
        setContentView(R.layout.content_green_teams);
    }

    public void team_yellow(View view) {
        setContentView(R.layout.content_yellow_teams);
    }
    public void captain_sport(View view) {
        setContentView(R.layout.content_sports_captains);
    }

    public void team_sport(View view) {
        setContentView(R.layout.content_teams);
    }
}