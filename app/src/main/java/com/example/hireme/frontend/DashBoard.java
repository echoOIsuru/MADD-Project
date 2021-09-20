package com.example.hireme.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.frontend.it20133290.IT20133290_VacancyMenu;

public class DashBoard extends AppCompatActivity {

    Button btnVacancy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnVacancy = findViewById(R.id.btnVacancy);


    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btnVacancy){
            i = new Intent(this, IT20133290_VacancyMenu.class);
        }

        startActivity(i);
    }

}