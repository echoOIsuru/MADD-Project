package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.VacancyServicesImp;

import java.util.Date;

public class IT20133290_AddVacancy extends AppCompatActivity {

    EditText jobTitle, organization, salary, jobFamily, jobLevel, description;
    DatePicker deadline;
    Button submit;
    Vacancies vacancies;
    //DatabaseReference ref;
    VacancyServicesImp vacSer = new VacancyServicesImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_add_vacancy);

        //assign view into variables
        jobTitle = findViewById(R.id.etJobTitle);
        jobFamily = findViewById(R.id.etJobFamily);
        jobLevel=(findViewById(R.id.etJobLevel));
        organization=(findViewById(R.id.etOrganization));
        salary=(findViewById(R.id.etSalary));
        description=(findViewById(R.id.etDescription));
        deadline=(findViewById(R.id.dpDeadline));
        submit=(findViewById(R.id.btnAddData));


        //create onclick to save data
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date(deadline.getYear(),deadline.getMonth(),deadline.getDayOfMonth());

                //add values
                vacSer.addNewVacancy(IT20133290_AddVacancy.this,jobTitle, organization, jobFamily,
                        jobLevel, description,salary, date) ;

            }
        });

    }




}

