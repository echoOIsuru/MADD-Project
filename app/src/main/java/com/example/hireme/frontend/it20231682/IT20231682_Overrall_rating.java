package com.example.hireme.frontend.it20231682;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.hireme.models.IT20231682_feedback_model;
import com.example.hireme.util.it20231682.IT20231682_feedback_overrall_Adapter;
import com.example.hireme.R;
import com.example.hireme.util.it20231682.IT20231682_feedback_rating_calculate;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class IT20231682_Overrall_rating extends AppCompatActivity {

    //Initialize Variables
    RecyclerView recyclerView;
    IT20231682_feedback_overrall_Adapter it20231682_feedback_overrall_adapter;
    Query totalr;
    TextView totalrate;
    int Rcount = 0 ;
    float average = 0 , total = 0;
    String iworkeremail;

    IT20231682_feedback_rating_calculate feedtest = new IT20231682_feedback_rating_calculate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20231682_overrall_rating);

        //Assign variable
        totalrate = findViewById(R.id.totalrate);
        recyclerView = findViewById(R.id.feedrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        //catch data (worker email) from intent
        Intent intent = getIntent();
        iworkeremail = intent.getStringExtra("wmail");



        //database connection related workers review
        FirebaseRecyclerOptions<IT20231682_feedback_model> options =
                new FirebaseRecyclerOptions.Builder<IT20231682_feedback_model>().setQuery(FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/")
                        .getReference()
                        .child("IT20231682_feedback_model").orderByChild("wemail").equalTo(iworkeremail), IT20231682_feedback_model.class)
                        .build();

        it20231682_feedback_overrall_adapter = new IT20231682_feedback_overrall_Adapter(options);
        recyclerView.setAdapter(it20231682_feedback_overrall_adapter);




        // get total number of rating count
       //totalr = FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model");
        totalr = FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model").orderByChild("wemail").equalTo(iworkeremail);

        totalr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    //get total number of rating
                    Rcount = (int) snapshot.getChildrenCount();
//                    totalrate.setText(Integer.toString(Rcount));

                    for (DataSnapshot temp : snapshot.getChildren()) {
//                        average = temp.getKey();
                        System.out.println(temp);
                        temp.child("email");
                        System.out.println(temp.child("rate"));

                        average += Float.parseFloat(temp.child("rate").getValue().toString());

                    }


                    total = average/Rcount;
               //  total =    feedtest.calcRate(Rcount,average);

                    String formattedString =String.format("%.02f" , total);
                    totalrate.setText(formattedString);



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


    }

    @Override
    protected void onStart() {
        super.onStart();
        it20231682_feedback_overrall_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        it20231682_feedback_overrall_adapter.startListening();
    }

    // for searching data
    //search customers name
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
                // Toast.makeText(getApplicationContext(),"no results! " , Toast.LENGTH_SHORT).show();
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

        it20231682_feedback_overrall_adapter = new IT20231682_feedback_overrall_Adapter(options);
        it20231682_feedback_overrall_adapter.startListening();
        recyclerView.setAdapter(it20231682_feedback_overrall_adapter);
    }


}
