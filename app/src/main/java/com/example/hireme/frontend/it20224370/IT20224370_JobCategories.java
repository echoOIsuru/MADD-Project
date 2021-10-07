package com.example.hireme.frontend.it20224370;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hireme.R;

public class IT20224370_JobCategories extends AppCompatActivity {

    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_job_categories);
        getSupportActionBar().hide();

        btn1 = (Button) findViewById(R.id.btnview);
        Intent i = getIntent();
        String msg = i.getStringExtra("email");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchactivity = new Intent(IT20224370_JobCategories.this, IT20224370_Householdjobs.class);
                launchactivity.putExtra("email", msg);
                startActivity(launchactivity);

            }
        });
        btn2 = (Button) findViewById(R.id.btnview3);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchactivity = new Intent(IT20224370_JobCategories.this, IT20224370_IndustrialJobs.class);
                launchactivity.putExtra("email", msg);
                startActivity(launchactivity);

            }
        });


    }
}