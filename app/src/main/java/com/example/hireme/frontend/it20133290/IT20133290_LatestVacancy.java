package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.database.Connection;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.it20133290.VacancyServicesImp;
import com.example.hireme.util.it20133290.VacancyAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class IT20133290_LatestVacancy extends AppCompatActivity {

//    Button btn1;
    Button clear;

    RecyclerView rvAll;
    Connection con = new Connection();
    VacancyAdapter vacancyAdapter;
    VacancyServicesImp vacSer = new VacancyServicesImp();

    //category
    String item[] = {"Banking","Driving","Internet","Private", "Government", "Other"};
    AutoCompleteTextView atCategory;
    ArrayAdapter<String> adapter;


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

        ///Category dropdown menu
        atCategory = findViewById(R.id.etVacancyCategory);
        adapter = new ArrayAdapter<String>(this,R.layout.it20133290_list_item,item);
        atCategory.setAdapter(adapter);
        atCategory.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String i = Long.toString(parent.getItemIdAtPosition(position));


                FirebaseRecyclerOptions<Vacancies> options = new FirebaseRecyclerOptions.Builder<Vacancies>().
                        setQuery(con.getRef().child("Vacancies").orderByChild("jobFamily").startAt(item[Integer.parseInt(i)]).endAt(item[Integer.parseInt(i)]+"~"),Vacancies.class).build();

                vacancyAdapter = new VacancyAdapter(options);
                rvAll.setAdapter(vacancyAdapter);
                vacancyAdapter.startListening();


                Toast.makeText(getApplicationContext(),"Item "+i, Toast.LENGTH_SHORT);
            }
        });

        //clear button
        clear = findViewById(R.id.btnVacancySortClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atCategory.setText("");
                FirebaseRecyclerOptions<Vacancies> options = new FirebaseRecyclerOptions.Builder<Vacancies>().
                        setQuery(con.getRef().child("Vacancies"),Vacancies.class).build();

                vacancyAdapter = new VacancyAdapter(options);
                rvAll.setAdapter(vacancyAdapter);
                vacancyAdapter.startListening();
            }
        });




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