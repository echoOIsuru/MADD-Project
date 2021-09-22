package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hireme.R;
import com.example.hireme.models.AppUser;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.it20133290.VacancyServicesImp;

public class IT20133290_LoginActivity extends AppCompatActivity {

    EditText email,pass;
    Button submit;
    Vacancies vacancies;
    AppUser appUser;
    VacancyServicesImp vacSer = new VacancyServicesImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_login);

        email = findViewById(R.id.etUserLoginEmail);
        pass = findViewById(R.id.etUserLoginPassword);
        submit = findViewById(R.id.btnLogin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vacSer.validateUser(IT20133290_LoginActivity.this,email,pass);
            }
        });


    }
}