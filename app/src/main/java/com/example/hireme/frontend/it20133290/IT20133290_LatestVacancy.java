package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.hireme.R;
import com.example.hireme.database.Connection;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.it20133290.VacancyServicesImp;
import com.example.hireme.util.VacancyAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class IT20133290_LatestVacancy extends AppCompatActivity {

//    Button btn1;

    RecyclerView rvAll;
    Connection con = new Connection();
    VacancyAdapter vacancyAdapter;
    VacancyServicesImp vacSer = new VacancyServicesImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_latest_vacancy);

        rvAll = findViewById(R.id.rvVacancy);
        rvAll.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Vacancies> options = new FirebaseRecyclerOptions.Builder<Vacancies>().
                setQuery(con.getRef().child("Vacancies"),Vacancies.class).build();

        vacancyAdapter = new VacancyAdapter(options);
        rvAll.setAdapter(vacancyAdapter);

//        btn1 = (Button)findViewById(R.id.btnApply);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopUpWindow();
//            }
//        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        vacancyAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vacancyAdapter.stopListening();
    }

//    public void openPopUpWindow(){
//        Intent popUp = new Intent(this,IT20133290_Popup_apply_form.class);
//        startActivity(popUp);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.v_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                vacancyAdapter = new VacancyAdapter(vacSer.txtSearch(query)); // call the search query
                vacancyAdapter.startListening();
                rvAll.setAdapter(vacancyAdapter);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {


                vacancyAdapter = new VacancyAdapter(vacSer.txtSearch(query));  // call the search query
                vacancyAdapter.startListening();
                rvAll.setAdapter(vacancyAdapter);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}