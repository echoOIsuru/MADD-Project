package com.example.hireme.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hireme.IT20231682_feedback_view;
import com.example.hireme.R;
import com.example.hireme.frontend.it20133290.IT20133290_VacancyMenu;
//import com.example.hireme.frontend.it20224370.IT20224370_JobCategories;
//import com.example.hireme.frontend.it20224370.IT20224370_MyRequests;
import com.example.hireme.frontend.it20245092.IT20245092_workerprofile;
import com.example.hireme.models.AppUser;
import com.example.hireme.services.it20133290.VacancyServicesImp;
import com.example.hireme.frontend.it20224370.IT20224370_JobCategories;
import com.example.hireme.frontend.it20224370.IT20224370_Session_Management;

public class DashBoard extends AppCompatActivity {

    Button btnVacancy, btnhireWorker,btnworker;
    String msg,name;
    Button btnVacancy, btnhireWorker,btnworker,btnFeedbackID;
    String msg, img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dash_board);

        btnVacancy = findViewById(R.id.btnVacancy);
        btnhireWorker = findViewById(R.id.hireworker);
        btnworker = findViewById(R.id.btnworkerPro);
        btnFeedbackID = findViewById(R.id.btnFeedbackID);

        Intent i = getIntent();
        msg = i.getStringExtra("email");
        name = i.getStringExtra("name");
        img = i.getStringExtra("img");

        System.out.println(msg);

        //set the username to a session
        IT20224370_Session_Management session;//global variable
        session = new IT20224370_Session_Management(DashBoard.this); //in oncreate
        //set sharedpreference

        session.setusename(msg); //set the usermail to the session


    }



    public void onClick(View v){
        Intent i = new Intent();

        if(v == btnVacancy){
            i = new Intent(this, IT20133290_VacancyMenu.class);
            i.putExtra("email",msg);

        }

        else if(v == btnhireWorker){
            i = new Intent(this, IT20224370_JobCategories.class);
            i.putExtra("email",msg);

        }
        else if(v == btnworker) {
            i = new Intent(this, IT20245092_workerprofile.class);
            i.putExtra("email",msg);
            i.putExtra("name",name);
            i.putExtra("name",msg);
            i.putExtra("img",img);

        }else if(v == btnFeedbackID){
            i = new Intent(this, IT20231682_feedback_view.class);
            i.putExtra("email",msg);
            i.putExtra("name",msg);
        }


        startActivity(i);
    }

}