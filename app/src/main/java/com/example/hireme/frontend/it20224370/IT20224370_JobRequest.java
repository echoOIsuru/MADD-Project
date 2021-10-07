package com.example.hireme.frontend.it20224370;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.util.it20224370.IT20224370_AdapterRequest;
import com.example.hireme.util.it20224370.IT20224370_AddRequestDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class IT20224370_JobRequest extends AppCompatActivity {

    EditText fullName, mobileNumber, date, time, address;
    Button btn1;
    IT20224370_AddRequestDetails test = new IT20224370_AddRequestDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20224370_job_request);
        getSupportActionBar().hide();

        //get the usermail from the session
        IT20224370_Session_Management session;//global variable
        session = new IT20224370_Session_Management(IT20224370_JobRequest.this); //in oncreate
        //set sharedpreference

        String SessionMail = session.getusename();//assign the usermail to a string varaible which get from the session

        fullName = (EditText) findViewById(R.id.editTextTextPersonName);
        mobileNumber = (EditText) findViewById(R.id.editTextphoneNumber);
        date = (EditText) findViewById(R.id.editTextDate);
        time = (EditText) findViewById(R.id.editTextTime3);
        address = (EditText) findViewById(R.id.et_Address);
        btn1 = (Button) findViewById(R.id.btnsendrequest);

        //get worker mail from a intent
        String passedWorkerMail = getIntent().getStringExtra("wmail");

        //if worker-mail equlas to usermail that user cannot send request
        if (SessionMail.equals(passedWorkerMail)) {
            btn1.setEnabled(false);
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(fullName.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("This Request will send to relevant worker");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertData(SessionMail);

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

    private void insertData(String sessionMail) {

        //passed data through intents

        String passedJob = getIntent().getStringExtra("wjob");
        String passedWorkerMail = getIntent().getStringExtra("wmail");
        String passedWorkerName = getIntent().getStringExtra("wname");
        String passedWorkerPhoto = getIntent().getStringExtra("workerpic");

        // creating pattern for name

        String NamePattern = ".{1,50}";
        String AddressPattern = ".{1,200}"; // creating pattern for address
        String DatePattern = ("[0-3][0-9]/[0-1][0-9]/[0-2][0][0-2][0-2]"); //date pattern
        String Time = ("([01]?[0-9]|2[0-3]):[0-5][0-9]"); //time pattern

        //nested else if to clarify whether user enter inputs to text fields, if not shows a toast
        try {
            if (TextUtils.isEmpty(fullName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();

            else if (!test.NameChecker(fullName.getText().toString())) //!(fullName.getText().toString()).matches(NamePattern)
                Toast.makeText(getApplicationContext(), "The Name You have Entered has Exceeded the Limit", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(mobileNumber.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();

            else if (test.MNumberChecker(mobileNumber.getText().toString())) //mobileNumber.length() != 10

                mobileNumber.setError("Mobile Number should have 10 digits");

            else if (TextUtils.isEmpty(date.getText().toString()))

                Toast.makeText(getApplicationContext(), "Please enter Appointment Date", Toast.LENGTH_SHORT).show();

            else if (!test.DateChecker(date.getText().toString()))  //!(date.getText().toString()).matches(DatePattern)

                Toast.makeText(getApplicationContext(), "Entered Date Pattern is incorrect", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(time.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Appointment Time", Toast.LENGTH_SHORT).show();

            else if (!test.TimeChecker(time.getText().toString())) //!(time.getText().toString()).matches(Time)
                Toast.makeText(getApplicationContext(), "You have Entered Time Incorrectly", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(address.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_SHORT).show();

            else if (!(address.getText().toString()).matches(AddressPattern))
                Toast.makeText(getApplicationContext(), "The Address You have Entered has Exceeded the Limit", Toast.LENGTH_SHORT).show();

            else {
                //map with firebase and insert user inputs to firebase object
                Map<String, Object> map = new HashMap<>();
                //map.put("workerID",wid);
                map.put("fullName", fullName.getText().toString());
                map.put("mobileNumber", mobileNumber.getText().toString());
                map.put("date", date.getText().toString());
                map.put("status", "pending");
                map.put("userMail", sessionMail);
                map.put("selectedJob", passedJob);
                map.put("workerMail", passedWorkerMail);
                map.put("time", time.getText().toString());
                map.put("address", address.getText().toString());
                map.put("workerPhoto", passedWorkerPhoto);
                map.put("workerName", passedWorkerName);



                /* reqModel.setFullName(fullName.getText().toString());
                reqModel.setMobileNumber(mobileNumber.getText().toString());
                reqModel.setDate(date.getText().toString());
                reqModel.setStatus("pending");
                reqModel.setUserMail("abcz@gmail.com");
                reqModel.setSelectedJob(passedJob);
                reqModel.setWorkerMail(passedWorkerMail);
                reqModel.setTime(time.getText().toString());
                reqModel.setAddress(address.getText().toString());

                FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests").push()
                        .setValue(reqModel);

                        Toast.makeText(c,"Data Inserted Successfully", Toast.LENGTH_SHORT).show(); */


                FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                if (!map.isEmpty())
                                    Toast.makeText(IT20224370_JobRequest.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                                clearAll();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(IT20224370_JobRequest.this, "Data Insertion is Unsuccessful", Toast.LENGTH_SHORT).show();

                            }
                        });
            }

        } catch (Exception e) {


        }

    }

    private void clearAll() {

        fullName.setText("");
        mobileNumber.setText("");
        date.setText("");
        time.setText("");
        address.setText("");

    }
}