package com.example.hireme.frontend.it20224370;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hireme.util.it20224370.IT20224370_AdapterRequest;
import com.example.hireme.models.IT20224370_RequestModel;
import com.example.hireme.R;
import com.example.hireme.models.IT20224370_RequestModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class IT20224370_MyRequests extends AppCompatActivity {

    RecyclerView recyclerView;
    IT20224370_AdapterRequest requestAdapter;
    //Button btn1;
    //ImageButton btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_myrequests);
        getSupportActionBar().hide();

        recyclerView=(RecyclerView) findViewById(R.id.RequestRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String userMail = getIntent().getStringExtra("email");     //get logged user mail by an intent

        //according to the logged user mail retrieve user previously made requests and pass to model

        FirebaseRecyclerOptions<IT20224370_RequestModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_RequestModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests").orderByChild("userMail").equalTo(userMail), IT20224370_RequestModel.class)
                        .build();

        requestAdapter = new IT20224370_AdapterRequest(options);
        recyclerView.setAdapter(requestAdapter);

//        btn1=(Button)findViewById(R.id.ReqUPbutton4);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent launchactivity= new Intent(IT20224370_MyRequests.this,It20224370_UpdateRequests.class);
//                startActivity(launchactivity);
//
//            }
//        });
//        btn2 = (ImageButton) findViewById(R.id.ReqDelimageView5); //IMPORTANT LINK THE DELETE BTN ID HERE !!!!!!!!! but its a image make it a btn
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopUpWindow();
//            }
//        });



    }

    @Override
    protected void onStart(){
        super.onStart();
        requestAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        requestAdapter.stopListening();
    }

}