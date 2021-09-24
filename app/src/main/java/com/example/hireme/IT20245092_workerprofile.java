package com.example.hireme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IT20245092_workerprofile extends AppCompatActivity {

     Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20245092_workerprofile);

        btn1 = findViewById(R.id.btnrequests);
        btn2 = findViewById(R.id.btnmyjobs);
        btn3 = findViewById(R.id.btnworkhistory);


    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btn1){
            i = new Intent(this,IT20245092_Requests.class);
        }else if(v == btn2){
//           i = new Intent(this,Jobs.class);
            i = new Intent(this,Jobs.class);
        }else if(v == btn3){
            i = new Intent(this,IT20245092_WorkHistory.class);
        }

        startActivity(i);
    }
}