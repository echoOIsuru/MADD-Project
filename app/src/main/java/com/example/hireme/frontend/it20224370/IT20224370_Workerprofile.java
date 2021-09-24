package com.example.hireme.frontend.it20224370;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class IT20224370_Workerprofile extends AppCompatActivity {

    Button btn,btn1;
//    IT20224370_HouseHoldModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_workerprofile);
        getSupportActionBar().hide();

//        TextView name = (TextView) findViewById(R.id.editTextTextPersonName3);
//        TextView mobile = (TextView)findViewById(R.id.editTextPhone3);
//        TextView email = (TextView)findViewById(R.id.editTextemail);
//        TextView des = (TextView)findViewById(R.id.editTextTextDes);
//
//
//        name.setText(model.getName());
//        mobile.setText(model.getContact_Number());
//        email.setText(model.getEmail());
//        des.setText(model.getDescription());



        String Details = getIntent().getStringExtra("wname");
        String Details2 = getIntent().getStringExtra("wnumber");
        String Details3 = getIntent().getStringExtra("wmail");
        String Details4 = getIntent().getStringExtra("wdes");
        String Details5 = getIntent().getStringExtra("workerpic");
        String Details6 = getIntent().getStringExtra("workerjob");

        btn=(Button)findViewById(R.id.workerbtn);
//        btn1=(Button)findViewById(R.id.backbtn);

        TextView name = (TextView) findViewById(R.id.editTextTextPersonName3);
        TextView mobile = (TextView)findViewById(R.id.editTextPhone3);
        TextView email = (TextView)findViewById(R.id.editTextemail);
        TextView des = (TextView)findViewById(R.id.editTextTextDes);
        CircleImageView Wpic = (CircleImageView)findViewById(R.id.workerpropic);


        name.setText(Details);
        mobile.setText(Details2);
        email.setText(Details3);
        des.setText(Details4);
        Glide.with(Wpic.getContext())
                .load(Details5)
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(Wpic);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), IT20224370_JobRequest.class);

                i.putExtra("wjob",Details6);
                i.putExtra("wmail",Details3);

                view.getContext().startActivity(i);

            }
        });

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent launchactivity= new Intent(IT20224370_Workerprofile.this,IT20224370_Householdjobs.class);
//                startActivity(launchactivity);
//
//            }
//        });
    }
}