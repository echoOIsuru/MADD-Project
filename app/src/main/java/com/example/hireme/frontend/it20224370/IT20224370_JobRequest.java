package com.example.hireme.frontend.it20224370;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class IT20224370_JobRequest extends AppCompatActivity {

    EditText fullName,mobileNumber,date,time,address;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_job_request);
        getSupportActionBar().hide();

        fullName = (EditText) findViewById(R.id.editTextTextPersonName);
        mobileNumber = (EditText) findViewById(R.id.editTextphoneNumber);
        date = (EditText) findViewById(R.id.editTextDate);
        time = (EditText) findViewById(R.id.editTextTime3);
        address = (EditText) findViewById(R.id.et_Address);
        btn1 = (Button)findViewById(R.id.btnsendrequest);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(fullName.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("This Request will send to relevant worker");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertData();
                        clearAll();
                        Toast.makeText(fullName.getContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(fullName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });
    }



//    private void openPopUpWindow(){
//        Intent popUp = new Intent(this,IT20224370_Popup.class);
//        startActivity(popUp);
//    }

    //insert data inorder to send a job request

    private void insertData(){


        String passedJob = getIntent().getStringExtra("wjob");
        String passedWorkerMail = getIntent().getStringExtra("wmail");
        String passedUserMail = getIntent().getStringExtra("email");     //passed data through intents

        //nested else if to clarify whether user enter inputs to text fields, if not shows a toast
        try {
            if (TextUtils.isEmpty(fullName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(mobileNumber.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(date.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Appointment Date", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(time.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Appointment Time", Toast.LENGTH_SHORT).show();

            else if(TextUtils.isEmpty(address.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_SHORT).show();

            else {
        //map with firebase and insert user inputs to firebase object
                Map<String,Object> map = new HashMap<>();
                //map.put("workerID",wid);
                map.put("fullName",fullName.getText().toString());
                map.put("mobileNumber",mobileNumber.getText().toString());
                map.put("date",date.getText().toString());
                map.put("status","pending");
                map.put("userMail","abcz@gmail.com");
                map.put("selectedJob",passedJob);
                map.put("workerMail",passedWorkerMail);
                map.put("time",time.getText().toString());
                map.put("address",address.getText().toString());

                FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                if(!map.isEmpty())
                                Toast.makeText(IT20224370_JobRequest.this,"Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(IT20224370_JobRequest.this,"Data Insertion is Unsuccessful", Toast.LENGTH_SHORT).show();

                            }
                        });
            }

            } catch (Exception e) {

            }

    }

    private void clearAll(){

        fullName.setText("");
        mobileNumber.setText("");
        date.setText("");
        time.setText("");
        address.setText("");

    }
}