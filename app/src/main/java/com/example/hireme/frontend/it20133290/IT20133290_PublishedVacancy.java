package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hireme.R;
import com.example.hireme.database.Connection;
import com.example.hireme.models.Vacancies;
import com.example.hireme.util.VacancyAdapter;
import com.example.hireme.util.VacancyPublishedAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class IT20133290_PublishedVacancy extends AppCompatActivity {

    VacancyPublishedAdapter vpa;
    RecyclerView recyclerView;
    Connection con =  new Connection();
    String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_published_vacancy);
        getSupportActionBar().hide();

        Intent i = getIntent();
        msg = i.getStringExtra("email");
        System.out.println(msg);

        recyclerView = (RecyclerView)findViewById(R.id.rvUpdateVacancy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Vacancies> options = new FirebaseRecyclerOptions.Builder<Vacancies>().
                setQuery(con.getRef().child("Vacancies").orderByChild("email").equalTo(msg),Vacancies.class).build();

        vpa = new VacancyPublishedAdapter(options);
        recyclerView.setAdapter(vpa);


    }

    @Override
    protected void onStart() {
        super.onStart();
        vpa.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vpa.stopListening();
    }
}