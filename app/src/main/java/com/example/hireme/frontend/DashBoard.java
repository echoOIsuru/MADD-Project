package com.example.hireme.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.frontend.it20133290.IT20133290_VacancyMenu;
//import com.example.hireme.frontend.it20224370.IT20224370_JobCategories;
//import com.example.hireme.frontend.it20224370.IT20224370_MyRequests;
import com.example.hireme.frontend.it20245092.IT20245092_workerprofile;
import com.example.hireme.models.AppUser;
import com.example.hireme.services.it20133290.VacancyServicesImp;

public class DashBoard extends AppCompatActivity {

    Button btnVacancy, btnhireWorker,btnworker;
    String msg,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dash_board);

        btnVacancy = findViewById(R.id.btnVacancy);
        btnhireWorker = findViewById(R.id.hireworker);
        btnworker = findViewById(R.id.btnworkerPro);

        Intent i = getIntent();
        msg = i.getStringExtra("email");
        name = i.getStringExtra("name");
        System.out.println(msg);
    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btnVacancy){
            i = new Intent(this, IT20133290_VacancyMenu.class);
            i.putExtra("email",msg);

        }

//        else if(v == btnhireWorker){
//            i = new Intent(this, IT20224370_JobCategories.class);
//            i.putExtra("email",msg);
//
//        }
        else if(v == btnworker) {
            i = new Intent(this, IT20245092_workerprofile.class);
            i.putExtra("email",msg);
            i.putExtra("name",name);
        }


        startActivity(i);
    }

}