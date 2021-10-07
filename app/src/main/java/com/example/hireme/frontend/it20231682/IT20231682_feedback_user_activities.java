package com.example.hireme.frontend.it20231682;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.hireme.frontend.it20231682.IT20231682_feedback_onecustomer_reviews;
import com.example.hireme.util.it20231682.IT20231682_feedbackUserActvityAdapter;
import com.example.hireme.R;
import com.example.hireme.models.IT20224370_RequestModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class IT20231682_feedback_user_activities extends AppCompatActivity {

    //Initialize Variables
    RecyclerView recyclerView;
    IT20231682_feedbackUserActvityAdapter it20231682_feedbackUserActvityAdapter;
    Button btnviewall,btnmyreview ;
    String iname,iemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20231682_feedback_user_activities);

        //Assign variable
        recyclerView = findViewById(R.id.feedworkerrv);
        btnviewall = findViewById(R.id.btnviewall);
        btnmyreview = findViewById(R.id.btnmyreview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //catch data(user name , user email) from intent
        Intent intent = getIntent();

         iname = intent.getStringExtra("name");
         iemail = intent.getStringExtra("email");



        //database connection to get data
        FirebaseRecyclerOptions<IT20224370_RequestModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_RequestModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests").orderByChild("userMail").equalTo(iemail), IT20224370_RequestModel.class)
                        .build();

        it20231682_feedbackUserActvityAdapter = new IT20231682_feedbackUserActvityAdapter(options);
        recyclerView.setAdapter(it20231682_feedbackUserActvityAdapter);


        //click on my reviews button then derect to the view all workers feedback page
        btnmyreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IT20231682_feedback_onecustomer_reviews.class));
            }
        });



        //click on All reviews button then derect to the view all workers feedback page
        btnviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.hireme.frontend.it20231682.IT20231682_feedback_view.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        it20231682_feedbackUserActvityAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        it20231682_feedbackUserActvityAdapter.startListening();
    }
}