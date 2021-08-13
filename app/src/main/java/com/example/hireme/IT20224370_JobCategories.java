package com.example.hireme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IT20224370_JobCategories extends AppCompatActivity {

    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_job_categories);

        btn1=(Button)findViewById(R.id.btnview1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchactivity= new Intent(IT20224370_JobCategories.this,IT20224370_Householdjobs.class);
                startActivity(launchactivity);

            }
        });
        btn2=(Button)findViewById(R.id.btnview);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchactivity= new Intent(IT20224370_JobCategories.this,IT20224370_IndustrialJobs.class);
                startActivity(launchactivity);

            }
        });


    }
}