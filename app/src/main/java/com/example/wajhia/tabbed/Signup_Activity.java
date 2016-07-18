package com.example.wajhia.tabbed;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

//import android.support.v7.app.AppCompatActivity;

/**
 * Created by wajhia on 5/4/2016.
 */
public class Signup_Activity extends Activity {
    //private EditText etNormalText;
    public static EditText etEmailAddrss;
    public static EditText etPhoneNumber;
    public static EditText password;
    private Button btnSubmit;
    public static int i1 = 0;
    ArrayList<Numbers> returnValues;
    ArrayList<String> listItems ;
    private static String SENT = "SMS_SENT";
    private static String DELIVERED = "SMS_DELIVERED";
    private static int MAX_SMS_MESSAGE_LENGTH = 160;
    public static String phoneNumberReceiver = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_signup_);
        registerViews();


    }
    private void registerViews() {
        /*etNormalText = (EditText) findViewById(R.id.input_name);
        // TextWatcher would let us check validation error on the fly
        etNormalText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etNormalText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });*/

        etEmailAddrss = (EditText) findViewById(R.id.input_email);
        etEmailAddrss.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(etEmailAddrss, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        password = (EditText) findViewById(R.id.input_password);
        password.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(password);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etPhoneNumber = (EditText) findViewById(R.id.input_phone);
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(etPhoneNumber, false);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        btnSubmit = (Button) findViewById(R.id.btn_next);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if (checkValidation()) {
                    submitForm();
                    phoneNumberReceiver = etPhoneNumber.getText().toString(); // phone number to which SMS to be send
                    phoneNumberReceiver.replaceAll("[\\s\\-()]", "");
                    returnValues = new ArrayList<Numbers>();
                    listItems = new ArrayList<String>();
                    Toast.makeText(Signup_Activity.this, "before", Toast.LENGTH_LONG).show();
                    SaveAsyncTask2 task = new SaveAsyncTask2();
                    Log.i("task", "" + task.server_output);
                    try {
                        returnValues = task.execute().get();
                    } catch (InterruptedException e) {
                        Log.i("task", "" + e);
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        Log.i("task", "" + e);
                        e.printStackTrace();
                    }
                    for (Numbers x : returnValues) {
                        listItems.add(x.getemail());
                        listItems.add(x.getphone());
                        listItems.add(x.getpass());
                        listItems.add(x.gethouse());
                    }
                    //Toast.makeText(Signup_Activity.this, "" + listItems + "", Toast.LENGTH_LONG).show();
                    if (!(listItems.contains(Signup_Activity.etEmailAddrss.getText().toString()))) {
                        Random r = new Random();
                        i1 = r.nextInt(9999 - 1000) + 9999;
                        String message = "Your Verification Code is: " + Integer.toString(i1);// message to send
                        sendSMS(phoneNumberReceiver, message);
                        // Show the toast  like in above screen shot
                        Toast.makeText(Signup_Activity.this, "Verification Code Sent", Toast.LENGTH_LONG).show();
                        userSignup1(view);
                    } else {
                        Toast.makeText(Signup_Activity.this, "This Email is already registered. Try again!", Toast.LENGTH_LONG).show();
                    }
                } else
                    Toast.makeText(Signup_Activity.this, "Form contains error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        // Submit your form here. your form is valid
        //Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
    }

    private boolean checkValidation() {
        boolean ret = true;

        //if (!Validation.hasText(etNormalText)) ret = false;
        if (!Validation.isEmailAddress(etEmailAddrss, true)) ret = false;
        if (!Validation.hasText(password)) ret = false;
        if (!Validation.isPhoneNumber(etPhoneNumber, true)) ret = false;

        return ret;
    }

    public void userSignup1(View view) {
        Intent intent1 = new Intent(this, SignUp_Activity2.class);
        startActivity(intent1);
    }
    public void sendSMS(String phoneNumber, String message) {
        PendingIntent piSent = PendingIntent.getBroadcast(getApplicationContext(),
                0, new Intent(SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(getApplicationContext(),
                0, new Intent(DELIVERED), 0);
        SmsManager smsManager = SmsManager.getDefault();
        int length = message.length();

        if (length > MAX_SMS_MESSAGE_LENGTH) {
            ArrayList<String> messagelist = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null,
                    messagelist, null, null);
        } else {
            smsManager.sendTextMessage(phoneNumber, null, message,
                    piSent, piDelivered);
        }

    }
}


