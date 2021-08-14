package com.example.hireme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IT20133290_CustomerMenu extends AppCompatActivity {

    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_customer_menu);

        btn1 = findViewById(R.id.btnAddVaMenu);
        btn2 = findViewById(R.id.btnPublishedVaMenu);
        btn3 = findViewById(R.id.btnInterviewMenu);
    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btn1)
            i = new Intent(this,IT20133290_AddVacancy.class);
        else if(v == btn2)
            i = new Intent(this,IT20133290_PublishedVacancy.class);
        else if(v == btn3)
            i = new Intent(this,IT20133290_WorkerRequest.class);

        startActivity(i);
    }


}