package com.example.hireme.frontend.it20245092;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hireme.R;
import com.example.hireme.models.IT20224370_RequestModel;
import com.example.hireme.util.IT20245092_ReqHistoryAdapter;
import com.example.hireme.util.IT20245092_RequestAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class IT20245092_WorkHistory extends AppCompatActivity {

    String name,email;

    RecyclerView recyclerView;
    IT20245092_ReqHistoryAdapter reqHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20245092_work_history);

        Intent i = getIntent();
        email = i.getStringExtra("email");
        name = i.getStringExtra("name");

        recyclerView = (RecyclerView) findViewById(R.id.historyRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<IT20224370_RequestModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_RequestModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/")
                                        .getReference().child("requests")
                                        .orderByChild("workerMail").equalTo(email)
                                ,IT20224370_RequestModel.class)
                        .build();


        reqHistoryAdapter = new IT20245092_ReqHistoryAdapter(options);
        recyclerView.setAdapter(reqHistoryAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        reqHistoryAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        reqHistoryAdapter.stopListening();
    }

}