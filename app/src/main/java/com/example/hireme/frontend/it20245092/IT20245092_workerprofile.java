package com.example.hireme.frontend.it20245092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.frontend.it20224370.IT20224370_JobRequest;
import com.example.hireme.frontend.it20224370.IT20224370_Session_Management;

public class IT20245092_workerprofile extends AppCompatActivity {

     Button btn1, btn2, btn3, btn4;
     String name,email,img;
     TextView wname,wemail;
     ImageView imgview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20245092_workerprofile);
        getSupportActionBar().hide();


        IT20224370_Session_Management session;//global variable
        session = new IT20224370_Session_Management(IT20245092_workerprofile.this);
        img = session.getImg();
        name = session.getName();
        email = session.getusename();

//        Intent i = getIntent();
//        email = i.getStringExtra("email");
//        name = i.getStringExtra("name");
//        img = i.getStringExtra("img");
        System.out.println("hi" + email);
        System.out.println("haaai" + name);


        btn1 = findViewById(R.id.btnrequests);
        btn2 = findViewById(R.id.btnmyjobs);
        btn3 = findViewById(R.id.btnworkhistory);
        btn4 = findViewById(R.id.btnreviews);


        wname = (TextView) findViewById(R.id.textView2);
        wemail = (TextView) findViewById(R.id.textView3);
        imgview4 = (ImageView) findViewById(R.id.imageView4);

        wname.setText(name);
        wemail.setText(email);
        Glide.with(this).load(img).into(imgview4);
    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btn1){
            i = new Intent(this, IT20245092_MyRequests.class);
            i.putExtra("email",email);
            i.putExtra("name",name);
        }else if(v == btn2){
//           i = new Intent(this,Jobs.class);
            i = new Intent(this, Jobs.class);
            i.putExtra("email",email);
            i.putExtra("name",name);
            i.putExtra("img",img);
        }else if(v == btn3){
            i = new Intent(this, IT20245092_WorkHistory.class);
            i.putExtra("email",email);
            i.putExtra("name",name);
        }else if(v == btn4){

        }

        startActivity(i);
    }
}