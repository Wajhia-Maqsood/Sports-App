package com.example.wajhia.tabbed;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScheduleActivity extends Activity  {

    TableLayout table;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        table = (TableLayout) findViewById(R.id.tableLayout1);
        TableRow tableRow1 = new TableRow(this);
        TextView date = new TextView(this);
        TextView time = new TextView(this);
        TextView location = new TextView(this);
        TextView type = new TextView(this);
        TextView game = new TextView(this);
        type.setBackgroundResource(R.drawable.cell_shape);
        location.setBackgroundResource(R.drawable.cell_shape);
        time.setBackgroundResource(R.drawable.cell_shape);
        date.setBackgroundResource(R.drawable.cell_shape);
        game.setBackgroundResource(R.drawable.cell_shape);
        type.setTextAppearance(this, android.R.style.TextAppearance_Large);
        location.setTextAppearance(this, android.R.style.TextAppearance_Large);
        game.setTextAppearance(this, android.R.style.TextAppearance_Large);
        date.setTextAppearance(this, android.R.style.TextAppearance_Large);
        time.setTextAppearance(this, android.R.style.TextAppearance_Large);
        date.setGravity(Gravity.CENTER);
        time.setGravity(Gravity.CENTER);
        location.setGravity(Gravity.CENTER);
        game.setGravity(Gravity.CENTER);
        type.setGravity(Gravity.CENTER);
        date.setText("Date");
        time.setText("Time");
        location.setText("Location");
        game.setText("Game");
        type.setText("Type");
        tableRow1.addView(game);
        tableRow1.addView(date);
        tableRow1.addView(time);
        tableRow1.addView(location);
        tableRow1.addView(type);
        tableRow1.setPadding(0,12,0,12);
        tableRow1.setMinimumHeight(20);
        table.addView(tableRow1);

        show();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    public void show() {


        //Toast.makeText(ScheduleActivity.this, ""+MainActivity.returnValues, Toast.LENGTH_SHORT).show();
       // Toast.makeText(ScheduleActivity.this, LoginActivity.house, Toast.LENGTH_SHORT).show();
            for (Matches x : MainActivity.returnValues) {
               // Toast.makeText(ScheduleActivity.this, x.gethouse(), Toast.LENGTH_SHORT).show();
                if(x.gethouse().equalsIgnoreCase(LoginActivity.house)) {

                    TableRow tableRow2 = new TableRow(this);
                    TextView date = new TextView(this);
                    TextView time = new TextView(this);
                    TextView location = new TextView(this);
                    TextView type = new TextView(this);
                    TextView game = new TextView(this);
                    type.setBackgroundResource(R.drawable.cell_shape);
                    location.setBackgroundResource(R.drawable.cell_shape);
                    time.setBackgroundResource(R.drawable.cell_shape);
                    date.setBackgroundResource(R.drawable.cell_shape);
                    game.setBackgroundResource(R.drawable.cell_shape);
                    date.setGravity(Gravity.CENTER);
                   time.setGravity(Gravity.CENTER);
                    location.setGravity(Gravity.CENTER);
                    game.setGravity(Gravity.CENTER);
                    type.setGravity(Gravity.CENTER);
                    date.setText("" + x.getdate());
                    time.setText("" + x.gettime());
                    location.setText("" + x.getlocation());
                    game.setText("" + x.getsport());
                    type.setText(""+x.gettype());
                    tableRow2.addView(game);
                    tableRow2.addView(date);
                    tableRow2.addView(time);
                    tableRow2.addView(location);
                    tableRow2.addView(type);
                    tableRow2.setPadding(0, 15, 0, 15);
                    table.addView(tableRow2);
                }

            }
        }



   }

