package com.example.hireme.frontend.it20245092;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hireme.R;
import com.example.hireme.models.IT20224370_RequestModel;
import com.example.hireme.util.IT20245092_RequestAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class IT20245092_MyRequests extends AppCompatActivity {

    RecyclerView recyclerView;
    IT20245092_RequestAdapter requestAdapter;
    String email,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20245092_my_requests);

        Intent i = getIntent();
        email = i.getStringExtra("email");
        name = i.getStringExtra("name");
        System.out.println("hi" + email);
        System.out.println("haaai" + name);

        recyclerView = (RecyclerView) findViewById(R.id.requestsrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Query temp = FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/")
//                .getReference().child("requests")
//                .orderByChild("workerMail").equalTo("janith@gmail.com");
//
//        temp.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()) {
//                    String val = "ok";
//                    for (DataSnapshot temp : snapshot.getChildren()) {
//                        temp.status
//                        System.out.println("asddasdasd  "+ temp.getValue().equals("pending"));
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        FirebaseRecyclerOptions<IT20224370_RequestModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_RequestModel>()
                .setQuery(FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/")
                        .getReference().child("requests")
                        .orderByChild("workerMail").equalTo(email)
                        ,IT20224370_RequestModel.class)
                .build();

        requestAdapter = new IT20245092_RequestAdapter(options);
        recyclerView.setAdapter(requestAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestAdapter.stopListening();
    }
}