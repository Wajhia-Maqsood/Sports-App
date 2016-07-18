package com.example.wajhia.tabbed;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by wajhia on 5/7/2016.
 */
public class AddSchedule extends Activity implements View.OnClickListener {
    TextView date, time,location,house,game;
    private TimePickerDialog mTimePicker;
    CheckBox matche,trial;
    Button add;
    //EditText date;
    ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private RadioGroup radioGroup;
    private static final String REQUIRED_MSG = "required";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ib = (ImageButton) findViewById(R.id.imageButton1);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        date = (TextView) findViewById(R.id.date);
        ib.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //matche = radioGroup.getCheckedRadioButtonId();
        // house = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId() )).getText().toString();
        matche = (CheckBox) findViewById(R.id.match);
        trial = (CheckBox) findViewById(R.id.trial);
        //date=(EditText) findViewById(R.id.date);
        time=(EditText) findViewById(R.id.time);
        if( time.getText().toString().length() == 0 )
            time.setError( "Time is required!" );
        location=(EditText) findViewById(R.id.location);
        if( location.getText().toString().length() == 0 )
            location.setError( "Location is required!" );
        game=(EditText) findViewById(R.id.sport);
        if( game.getText().toString().length() == 0 )
            game.setError( "Sport is required!" );
        house=(EditText) findViewById(R.id.house);
        if( house.getText().toString().length() == 0 )
            house.setError( "House name is required!" );
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(this);
        time.setOnClickListener(this);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            date.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };



    // add.setOnClickListener(new View.OnClickListener() {
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton1:{
                showDialog(0);
                break;
            }
            case R.id.time: {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ": " + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            }
            // calculate distance between 2 points;
            case R.id.add: {
                Matches match= new Matches();

                match.date= date.getText().toString();
                match.time= time.getText().toString();
                match.location= location.getText().toString();
                match.house= house.getText().toString();
                match.sport= game.getText().toString();
                add.setOnClickListener(this);

                if (matche.isChecked()) {
                    trial.setChecked(false);
                    match.type= "Match";
                }
                if (trial.isChecked()) {
                    matche.setChecked(false);
                    match.type= "Trial";
                }

                Add_match tsk = new  Add_match();
                tsk.execute(match);
                Toast.makeText(AddSchedule.this, "Added to DataBase", Toast.LENGTH_SHORT).show();
                time.setText("");
                location.setText("");
                house.setText("");
                game.setText("");
                date.setText("");
                break;
            }
        }
    }

}

