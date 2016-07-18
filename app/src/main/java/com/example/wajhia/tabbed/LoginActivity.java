package com.example.wajhia.tabbed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
//import android.support.v7.app.AppCompatActivity;

/**
 * Created by wajhia on 5/4/2016.
 */


public class LoginActivity extends Activity {

    private EditText EmailAddress;
    int flag=0;
    String Email;
    String Password;
    int admin=0;
    private EditText password;
    private Button btnSubmit;
    ArrayList<Numbers> returnValues;
    ArrayList<String> listItems ;
    public static String house;
    public static String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerViews();
    }

    private void registerViews() {

        EmailAddress = (EditText) findViewById(R.id.input_email);
        EmailAddress.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(EmailAddress, true);
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


        btnSubmit = (Button) findViewById(R.id.btn_login);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if (checkValidation()) {
                    admin=0;
                    submitForm();
                    Email = EmailAddress.getText().toString();
                    Password = password.getText().toString();
                    if (Email.equals("asports61@yahoo.com") && Password.equals("hello123good")) {
                        admin = 1;
                        //Toast.makeText(LoginActivity.this, "here", Toast.LENGTH_LONG).show();
                        home(view);
                    } else {
                        returnValues = new ArrayList<Numbers>();
                        listItems = new ArrayList<String>();

                        //Toast.makeText(LoginActivity.this, "before", Toast.LENGTH_LONG).show();
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
                        //Toast.makeText(LoginActivity.this, "" + listItems + "", Toast.LENGTH_LONG).show();
                        for (int i = 0; i < returnValues.size(); i++) {
                            Numbers f = returnValues.get(i);
                            if (f.getemail().matches(Email) && f.getpass().matches(Password)) {
                                phoneNumber = f.getphone();
                                phoneNumber.replaceAll("[\\s\\-()]", "");
                                house=f.gethouse();
                                home(view);
                                flag = 1;
                            }
                        }
                        if (flag == 0) {
                            Toast.makeText(LoginActivity.this, "Incorrect credentials, try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                } else
                    Toast.makeText(LoginActivity.this, "Form contains error", Toast.LENGTH_LONG).show();
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
        if (!Validation.isEmailAddress(EmailAddress, true)) ret = false;
        if (!Validation.hasText(password)) ret = false;

        return ret;
    }

    public void signUp(View view) {
        Intent intent1 = new Intent(this, Signup_Activity.class);

        startActivity(intent1);
    }

    public void home(View view) {
        if(admin==0){
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);}

        if(admin==1){
            //Toast.makeText(LoginActivity.this, "here1", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this, AdminActivity.class);

            startActivity(intent1);
        }
    }

}