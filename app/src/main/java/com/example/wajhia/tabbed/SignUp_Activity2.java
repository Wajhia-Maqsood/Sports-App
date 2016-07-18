package com.example.wajhia.tabbed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import android.widget.Toolbar;

import android.os.Bundle;
/**
 * Created by wajhia on 5/4/2016.
 */
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import android.widget.RadioButton;
public class SignUp_Activity2  extends Activity {

    private EditText VerCode;
    private Button btnSubmit;
    private RadioGroup radioGroup;
    private int selected;
    public static String house;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sign_up_2);
        registerViews();

    }

    private void registerViews() {

        VerCode = (EditText) findViewById(R.id.editText2);
        VerCode.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(VerCode);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        btnSubmit = (Button) findViewById(R.id.register);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if (checkValidation()) {
                    submitForm();
                    house = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId() )).getText().toString();


                    SaveAsyncTask tsk = new SaveAsyncTask();
                    Numbers number = new Numbers();
                    number.Email = Signup_Activity.etEmailAddrss.getText().toString();
                    number.Password = Signup_Activity.password.getText().toString();
                    number.Phone = Signup_Activity.etPhoneNumber.getText().toString();
                    number.House = house;
                    tsk.execute(number);
                    Register(view);
                } else
                    Toast.makeText(SignUp_Activity2.this, "Form contains error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        // Submit your form here. your form is valid
        Toast.makeText(this, "Registering...", Toast.LENGTH_LONG).show();
    }

    private boolean checkValidation() {
        boolean ret = true;
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        selected = radioGroup.getCheckedRadioButtonId();

        if(selected == -1) {
            ret = false;
            Toast.makeText(SignUp_Activity2.this, "House must be selected", Toast.LENGTH_LONG).show();
        }
        if (!Validation.hasText(VerCode)) ret = false;
        String text = VerCode.getText().toString();
        if(Integer.parseInt(text)!=Signup_Activity.i1) {
            ret = false;
            Toast.makeText(SignUp_Activity2.this, "Incorrect Verification Code", Toast.LENGTH_LONG).show();
        }

        return ret;
    }

    public void Register(View view) {
        Intent intent1 = new Intent(this, LoginActivity.class);

        startActivity(intent1);
    }
}
