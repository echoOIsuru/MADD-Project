package com.example.hireme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IT20231682_feedback_view extends AppCompatActivity {

    //Initialize Variables
    RecyclerView recyclerView;
    IT20231682_feedbackAdapter it20231682_feedbackAdapter;
    FloatingActionButton floatingActionButton;
    DatabaseReference totalr;
    TextView totalrate;
    int Rcount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20231682_feedback_view);

        //Assign variable
        totalrate = findViewById(R.id.totalrate);
        recyclerView = findViewById(R.id.feedrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<IT20231682_feedback_model> options =
                new FirebaseRecyclerOptions.Builder<IT20231682_feedback_model>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model"), IT20231682_feedback_model.class)
                        .build();

        it20231682_feedbackAdapter = new IT20231682_feedbackAdapter(options);
        recyclerView.setAdapter(it20231682_feedbackAdapter);



        // get total number of rating count
        totalr = FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model");

        totalr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    //get total number of rating
                    Rcount = (int) snapshot.getChildrenCount();
                    totalrate.setText(Integer.toString(Rcount));
                }
                else {
                    //no any count
                    totalrate.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        // click on plus icon then user direct to the add feedback page
        floatingActionButton = (FloatingActionButton) findViewById(R.id.feedfloatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IT20231682_give_feedback.class));
            }
        });

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


    // for searching data
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.feedback_search,menu);
        MenuItem item = menu.findItem(R.id.feedsearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textSearch(query);
                Toast.makeText(getApplicationContext(),"no results! " , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void textSearch(String str){
        FirebaseRecyclerOptions<IT20231682_feedback_model> options =
                new FirebaseRecyclerOptions.Builder<IT20231682_feedback_model>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model").orderByChild("name").startAt(str).endAt(str+"~"), IT20231682_feedback_model.class)
                        .build();

        it20231682_feedbackAdapter = new IT20231682_feedbackAdapter(options);
        it20231682_feedbackAdapter.startListening();
        recyclerView.setAdapter(it20231682_feedbackAdapter);
    }

}