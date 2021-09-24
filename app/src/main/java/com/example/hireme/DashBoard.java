package com.example.hireme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {

    Button btnVacancy, btnWorkerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnVacancy = findViewById(R.id.btnVacancy);
        btnWorkerProfile = findViewById(R.id.btnworkerPro);
    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btnVacancy){
            i = new Intent(this,IT20133290_VacancyMenu.class);
        }else if (v == btnWorkerProfile){
            i = new Intent(this,IT20245092_workerprofile.class);
        }

        startActivity(i);
    }

}