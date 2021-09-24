package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hireme.R;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.it20133290.VacancyServicesImp;

import java.util.Date;

public class IT20133290_AddVacancy extends AppCompatActivity {

    EditText jobTitle, organization, salary, jobFamily, jobLevel, description;
    DatePicker deadline;
    Button submit;
    String msg;
    Vacancies vacancies;
    //DatabaseReference ref;
    VacancyServicesImp vacSer = new VacancyServicesImp();

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_it20133290_add_vacancy);

        //assign view into variables
        jobTitle = findViewById(R.id.etJobTitle);
        jobFamily = findViewById(R.id.etJobFamily);
        jobLevel = (findViewById(R.id.etJobLevel));
        organization = (findViewById(R.id.etOrganization));
        salary = (findViewById(R.id.etSalary));
        description = (findViewById(R.id.etDescription));
        deadline = (findViewById(R.id.dpDeadline));
        submit = (findViewById(R.id.btnAddData));

        backArrow = findViewById(R.id.backArrow);


        //getExtra
        Intent i = getIntent();
        msg = i.getStringExtra("email");
        System.out.println(msg);



        //create onclick to save data
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = ""+deadline.getYear()+"/"+deadline.getMonth()+"/"+deadline.getDayOfMonth();

                //add values
                vacSer.addNewVacancy(IT20133290_AddVacancy.this,jobTitle, organization, jobFamily,
                        jobLevel, description,salary, date,msg) ;

                Intent i = new Intent(IT20133290_AddVacancy.this,IT20133290_CustomerMenu.class);
                i.putExtra("email",msg);
                startActivity(i);

            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}

