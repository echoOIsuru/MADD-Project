package com.example.hireme.frontend.it20245092;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hireme.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class IT20245092_JobDetails extends AppCompatActivity {

    //Button btn1;
    EditText name,location,description,contact,image,rate;
    Button addNewSave, addNewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20245092_job_details);

        //btn1 = findViewById(R.id.btndelete);

        //getting user inputs
        name = (EditText) findViewById(R.id.txtName);
        location = (EditText) findViewById(R.id.txtLocation);
        description = (EditText) findViewById(R.id.txtDescription);
        contact = (EditText) findViewById(R.id.txtContact);
        image = (EditText) findViewById(R.id.txtImage);
        rate = (EditText) findViewById(R.id.txtRate);
        addNewSave = (Button) findViewById(R.id.btnaddnewsave);
        addNewCancel = (Button) findViewById(R.id.btnaddnewcancel);

        addNewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });

        addNewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("Name",name.getText().toString());
        map.put("Location",location.getText().toString());
        map.put("Rate",rate.getText().toString());
        map.put("Contact",contact.getText().toString());
        map.put("Description",description.getText().toString());
        map.put("Image",image.getText().toString());
        //connection
        FirebaseDatabase.getInstance("https://fir-demo-734c3-default-rtdb.firebaseio.com/").getReference().child("Jobs").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(IT20245092_JobDetails.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(IT20245092_JobDetails.this, "Error while inserting", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        name.setText("");
        location.setText("");
        rate.setText("");
        contact.setText("");
        description.setText("");
        image.setText("");
    }

//    public void onClick(View v){
//        Intent i = new Intent();
//
//        if(v == btn1){
//            i = new Intent(this,IT20245092_DeleteConfimation.class);
//        }
//
//        startActivity(i);
//    }
}