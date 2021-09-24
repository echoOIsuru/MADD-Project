package com.example.hireme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class IT20133290_VacancyMenu extends AppCompatActivity {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_vacancy_menu);
        btn1 = findViewById(R.id.btnVcustomerMenu);
        btn2 = findViewById(R.id.btnVworker);


    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btn1)
            i = new Intent(this,IT20133290_CustomerMenu.class);
        else if(v == btn2)
            i = new Intent(this,IT20133290_JobMenu.class);

        startActivity(i);
    }

}