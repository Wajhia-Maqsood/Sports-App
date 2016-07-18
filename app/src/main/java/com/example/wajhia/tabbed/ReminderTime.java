package com.example.wajhia.tabbed;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ReminderTime extends Activity implements View.OnClickListener{

    private TimePickerDialog mTimePicker;
    EditText editText;
    EditText editDate;
    Button button;
    public static String a, b;
    private PendingIntent pendingIntent;
    private DatePickerDialog DatePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_time);
        editText = (EditText)findViewById(R.id.input_time);
        editText.setOnClickListener(this);
        editDate = (EditText)findViewById(R.id.input_date);
        editDate.setOnClickListener(this);
        button = (Button)findViewById(R.id.btn_remind);
        button.setOnClickListener(this);
       editText.setInputType(InputType.TYPE_NULL);
        editDate.setInputType(InputType.TYPE_NULL);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog = new DatePickerDialog(this, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        DatePickerDialog.setTitle("Select Date");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_remind:

                //---get current date and time---
                Calendar calendar = Calendar.getInstance();

                Intent myIntent = new Intent(ReminderTime.this, AlarmService.class);

                pendingIntent = PendingIntent.getService(ReminderTime.this, 0, myIntent, 0);

                DatePicker datePicker = DatePickerDialog.getDatePicker();

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.YEAR, datePicker.getYear());
                calendar.set(Calendar.MONTH, datePicker.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ReminderTime.a));
                calendar.set(Calendar.MINUTE, Integer.parseInt(ReminderTime.b));
                calendar.set(Calendar.SECOND, 0);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000 * 60 * 20, pendingIntent);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Toast.makeText(ReminderTime.this, "Reminder Set!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.input_time:
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editText.setText( selectedHour + ": " + selectedMinute);
                        a = Integer.toString(selectedHour);
                        b = Integer.toString(selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            case R.id.input_date:
                DatePickerDialog.show();
                break;


        }
    }
}
