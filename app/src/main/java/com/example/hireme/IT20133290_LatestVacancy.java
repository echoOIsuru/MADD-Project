package com.example.hireme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IT20133290_LatestVacancy extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_latest_vacancy);

        btn1 = (Button)findViewById(R.id.btnApply);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUpWindow();
            }
        });
    }



    private void openPopUpWindow(){
        Intent popUp = new Intent(this,IT20133290_Popup_apply_form.class);
        startActivity(popUp);
    }
}