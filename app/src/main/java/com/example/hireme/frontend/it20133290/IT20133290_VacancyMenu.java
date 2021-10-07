package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hireme.R;

public class IT20133290_VacancyMenu extends AppCompatActivity {

    Button btn1, btn2;
    String msg;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_vacancy_menu);
        getSupportActionBar().hide();
        btn1 = findViewById(R.id.btnVcustomerMenu);
        btn2 = findViewById(R.id.btnVworker);
        backArrow = findViewById(R.id.backArrow);

        Intent i = getIntent();
        msg = i.getStringExtra("email");

        //System.out.println(msg);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btn1)
            i = new Intent(this,IT20133290_CustomerMenu.class);

        else if(v == btn2)
            i = new Intent(this,IT20133290_JobMenu.class);

        i.putExtra("email",msg);
        startActivity(i);
    }

}