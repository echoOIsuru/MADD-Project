package com.example.hireme.frontend.it20231682;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hireme.util.it20231682.IT20231682_feedbackAdapter;
import com.example.hireme.models.IT20231682_feedback_model;
import com.example.hireme.R;
import com.example.hireme.frontend.it20224370.IT20224370_Session_Management;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class IT20231682_feedback_onecustomer_reviews extends AppCompatActivity {

    //Initialize Variables
    RecyclerView recyclerView;
   IT20231682_feedbackAdapter it20231682_feedbackAdapter;
    String workeremail ,userEmail , userName , userImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20231682_feedback_onecustomer_reviews);



        //catch data(user name , user email) from intent
        Intent intent = getIntent();
        workeremail = intent.getStringExtra("wmail");


        //get the user details (mail,username) from the session
        IT20224370_Session_Management session;
        session = new IT20224370_Session_Management(IT20231682_feedback_onecustomer_reviews.this);

        userEmail = session.getusename();
        userName = session.getName();
        userImg = session.getImg();


        //Assign variable
//        totalrate = findViewById(R.id.totalrate);
        recyclerView = findViewById(R.id.feedrv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        //database connection to get data from only related customer
        FirebaseRecyclerOptions<IT20231682_feedback_model> options =
                new FirebaseRecyclerOptions.Builder<IT20231682_feedback_model>().setQuery(FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/")
                        .getReference().child("IT20231682_feedback_model").orderByChild("email").equalTo(userEmail), IT20231682_feedback_model.class)
                        .build();

        

        it20231682_feedbackAdapter = new IT20231682_feedbackAdapter(options);
        recyclerView.setAdapter(it20231682_feedbackAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        it20231682_feedbackAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        it20231682_feedbackAdapter.startListening();
    }



}