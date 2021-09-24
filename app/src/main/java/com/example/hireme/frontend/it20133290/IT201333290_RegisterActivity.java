package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hireme.R;
import com.example.hireme.models.AppUser;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.it20133290.VacancyServicesImp;

public class IT201333290_RegisterActivity extends AppCompatActivity {

    EditText name,email,tp,pass,repass;
    Button submit;
    Vacancies vacancies;
    AppUser appUser;
    VacancyServicesImp vacSer = new VacancyServicesImp();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it201333290_register);

        name = findViewById(R.id.etUserName);
        email = findViewById(R.id.etUserEmail);
        tp = findViewById(R.id.etUserContact);
        pass = findViewById(R.id.etUserPassword);
        repass = findViewById(R.id.etReUserPassword);
        submit = findViewById(R.id.btnRegister);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vacSer.addNewUser(IT201333290_RegisterActivity.this,name,tp,email,pass,repass);
//
//                Intent intent = new Intent(IT201333290_RegisterActivity.this, IT20133290_LoginActivity.class);
//
//                startActivity(intent);
            }
        });


    }
}