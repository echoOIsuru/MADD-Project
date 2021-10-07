package com.example.hireme.frontend.it20224370;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.hireme.util.it20224370.IT20224370_AdapterIndustrial;
import com.example.hireme.models.IT20224370_IndustrialModel;
import com.example.hireme.R;
import com.example.hireme.models.IT20224370_IndustrialModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class IT20224370_IndustrialJobs extends AppCompatActivity {

    RecyclerView recyclerView;
    IT20224370_AdapterIndustrial industrialAdapter;
    Button Myrequests;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_industrial_jobs);
        getSupportActionBar().hide();

        Intent i = getIntent();
        String msg = i.getStringExtra("email");

        recyclerView = (RecyclerView) findViewById(R.id.IndustrialRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<IT20224370_IndustrialModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_IndustrialModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://fir-demo-734c3-default-rtdb.firebaseio.com/").getReference().child("Jobs").orderByChild("Type").equalTo("Industrial"), IT20224370_IndustrialModel.class)
                        .build();


        industrialAdapter = new IT20224370_AdapterIndustrial(options);
        recyclerView.setAdapter(industrialAdapter);

        SearchView searchView = (SearchView) findViewById(R.id.IndustrialSearch);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textSearch(query);
                return false;
            }
        });


        Myrequests = (Button) findViewById(R.id.ViewMyReq);

        Myrequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchactivity = new Intent(IT20224370_IndustrialJobs.this, IT20224370_MyRequests.class);
                launchactivity.putExtra("email", msg);
                startActivity(launchactivity);

            }
        });
//
//        btn2=(Button)findViewById(R.id.IJbtnview2);
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent launchactivity= new Intent(IT20224370_IndustrialJobs.this,IT20224370_Workerprofile.class);
//                startActivity(launchactivity);
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        industrialAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        industrialAdapter.stopListening();
    }

    private void textSearch(String str) {

        FirebaseRecyclerOptions<IT20224370_IndustrialModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_IndustrialModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://fir-demo-734c3-default-rtdb.firebaseio.com/").getReference().child("Jobs").orderByChild("Location").startAt(str).endAt(str + "~"), IT20224370_IndustrialModel.class)
                        .build();

        industrialAdapter = new IT20224370_AdapterIndustrial(options);
        industrialAdapter.startListening();
        recyclerView.setAdapter(industrialAdapter);

    }
}