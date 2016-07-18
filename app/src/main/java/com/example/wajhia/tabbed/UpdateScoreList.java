package com.example.wajhia.tabbed;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by wajhia on 5/13/2016.
 */
public class UpdateScoreList extends Activity {
    private Spinner spinner1;
    private Button btnSubmit;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_score);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }
    // add items into spinner dynamically


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Toast.makeText(UpdateScoreList.this,
                        //"OnClickListener : " +
                                //"\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()),

                        //Toast.LENGTH_SHORT).show();
            }

        });
    }
}
