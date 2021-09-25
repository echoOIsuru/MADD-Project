package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.it20133290.VacancyServicesImp;

import java.util.Date;

public class IT20133290_AddVacancy extends AppCompatActivity {

    EditText jobTitle, organization, salary, description;
    DatePicker deadline;
    Button submit;
    String msg;
    Vacancies vacancies;
    //DatabaseReference ref;
    VacancyServicesImp vacSer = new VacancyServicesImp();

    //job family
    String item[] = {"Banking","Driving","Internet","Private", "Government", "Other"};
    AutoCompleteTextView atJobFamily;
    ArrayAdapter<String> adapter;

    //job level
    String item2[] = {"Entry-level","Intermediate","Mid-level","Senior", "Executive-level"};
    AutoCompleteTextView atJobLevel;
    ArrayAdapter<String> adapter2;


    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_it20133290_add_vacancy);

        //assign view into variables
        jobTitle = findViewById(R.id.etJobTitle);
        //jobFamily = findViewById(R.id.etJobFamily);
        //jobLevel = (findViewById(R.id.etJobLevel));
        organization = (findViewById(R.id.etOrganization));
        salary = (findViewById(R.id.etSalary));
        description = (findViewById(R.id.etDescription));
        deadline = (findViewById(R.id.dpDeadline));
        submit = (findViewById(R.id.btnAddData));

        backArrow = findViewById(R.id.backArrow);

        ///Job family dropdown menu
        atJobFamily = findViewById(R.id.etJobFamily);
        adapter = new ArrayAdapter<String>(this,R.layout.it20133290_list_item,item);
        atJobFamily.setAdapter(adapter);
        atJobFamily.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = Long.toString(parent.getItemIdAtPosition(position));

                  Toast.makeText(getApplicationContext(),"Item "+item, Toast.LENGTH_SHORT);
            }
        });

        //Job level dropdown menu
        atJobLevel = findViewById(R.id.etJobLevel);
        adapter2 = new ArrayAdapter<String>(this,R.layout.it20133290_list_item,item2);
        atJobLevel.setAdapter(adapter2);
        atJobLevel.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item2 = Long.toString(parent.getItemIdAtPosition(position));

                Toast.makeText(getApplicationContext(),"Item "+item2, Toast.LENGTH_SHORT);
            }
        });



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
                vacSer.addNewVacancy(IT20133290_AddVacancy.this,jobTitle, organization, atJobFamily,
                        atJobLevel, description,salary, date,msg) ;



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

