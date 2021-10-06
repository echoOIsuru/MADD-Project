package com.example.hireme.frontend.it20224370;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.hireme.util.IT20224370_AdapterHousehold;
import com.example.hireme.models.IT20224370_HouseHoldModel;
import com.example.hireme.R;
import com.example.hireme.util.IT20224370_AdapterHousehold;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class IT20224370_Householdjobs extends AppCompatActivity {

    RecyclerView recyclerView;
    IT20224370_AdapterHousehold householdAdapter;
    Button Myrequests;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_householdjobs);
        getSupportActionBar().hide();

        Intent i = getIntent();
        String msg =i.getStringExtra("email");

        recyclerView=(RecyclerView) findViewById(R.id.HouseholdRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<IT20224370_HouseHoldModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_HouseHoldModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("HouseHoldWorkers"), IT20224370_HouseHoldModel.class)
                        .build();


        householdAdapter = new IT20224370_AdapterHousehold(options);
        recyclerView.setAdapter(householdAdapter);

        //save reference of the layout file search view component to a Searchview object
        SearchView searchView = (SearchView) findViewById(R.id.HouseholdSearch);

           //Search bar On text submit
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textSearch(query);
                return false;
            }
            //Search bar On text Change
            @Override
            public boolean onQueryTextChange(String query) {
                textSearch(query);
                return false;
            }
        });


        Myrequests = (Button)findViewById(R.id.ViewMyReq2);

        Myrequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchactivity= new Intent(IT20224370_Householdjobs.this,IT20224370_MyRequests.class);
                launchactivity.putExtra("email",msg);
                startActivity(launchactivity);

            }
        });
//
//        btn2=(Button)findViewById(R.id.HJbtnview3);
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent launchactivity= new Intent(IT20224370_Householdjobs.this,IT20224370_Workerprofile.class);
//                startActivity(launchactivity);
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        householdAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        householdAdapter.stopListening();
    }

    //Data filter by the workers location
    private void textSearch(String str){

        FirebaseRecyclerOptions<IT20224370_HouseHoldModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_HouseHoldModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("HouseHoldWorkers").orderByChild("from").startAt(str).endAt(str+"~"), IT20224370_HouseHoldModel.class)
                        .build();

        householdAdapter = new IT20224370_AdapterHousehold(options);
        householdAdapter.startListening();
        recyclerView.setAdapter(householdAdapter);

    }


}