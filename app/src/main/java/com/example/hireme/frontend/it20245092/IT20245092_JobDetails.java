package com.example.hireme.frontend.it20245092;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.models.IT20224370_RequestModel;
import com.example.hireme.util.it20245092.it20245092_workerManagementTesting;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class IT20245092_JobDetails extends AppCompatActivity {

    //Button btn1;
    EditText name,location,description,contact,image,rate;
    Button addNewSave, addNewCancel;
    Spinner jobType;
    String uName,email,img;
    it20245092_workerManagementTesting testing = new it20245092_workerManagementTesting();

    boolean fieldCheck = false;

    //job types
    String jobTypes[] = {"Household","Industrial"};
    AutoCompleteTextView selectedjobType;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20245092_job_details);

        Intent i = getIntent();
        email = i.getStringExtra("email");
        uName = i.getStringExtra("name");
        img = i.getStringExtra("img");
        System.out.println("hi" + email);
        System.out.println("haaai" + uName);

        //register all the edit text fields
        name = (EditText) findViewById(R.id.txtName);
        location = (EditText) findViewById(R.id.txtLocation);
//        jobType = (Spinner) findViewById(R.id.spinner1);
        description = (EditText) findViewById(R.id.txtDescription);
        contact = (EditText) findViewById(R.id.txtContact);
//        image = (EditText) findViewById(R.id.txtImage);
        rate = (EditText) findViewById(R.id.txtRate);
        addNewSave = (Button) findViewById(R.id.btnaddnewsave);
        addNewCancel = (Button) findViewById(R.id.btnaddnewcancel);

//        //register the dropdown list
//        Spinner spin = (Spinner) findViewById(R.id.spinner1);

        //get details according to user email
        FirebaseRecyclerOptions<IT20224370_RequestModel> options =
                new FirebaseRecyclerOptions.Builder<IT20224370_RequestModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://hireme-2e86a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                                        .getReference().child("requests")
                                        .orderByChild("workerMail").equalTo("janith@gmail.com")
                                ,IT20224370_RequestModel.class)
                        .build();


        addNewSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fieldCheck = CheckAllFields();

                if (fieldCheck){
                    insertData();
                    clearAll();
                }
            }
        });

        addNewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ///Job type dropdown menu
        selectedjobType = findViewById(R.id.txtjobtype);
        adapter = new ArrayAdapter<String>(this,R.layout.it20133290_list_item,jobTypes);
        selectedjobType.setAdapter(adapter);
        selectedjobType.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String jT = Long.toString(parent.getItemIdAtPosition(position));
            }
        });
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("Name",name.getText().toString());
        map.put("Type",selectedjobType.getText().toString());
        map.put("uName",uName);
        map.put("email",email);
        map.put("Location",location.getText().toString());
        map.put("Rate",rate.getText().toString());
        map.put("Contact",contact.getText().toString());
        map.put("Description",description.getText().toString());
        map.put("Image",img);
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

    //form validation
    private boolean CheckAllFields() {
        if (testing.checkEmpty(name.getText().toString())) {
            name.setError("This field is required");
            return false;
        }else if (name.length() > 15) {
            name.setError("Job name must be maximum 15 characters");
            return false;
        }

        if (location.length() == 0) {
            location.setError("This field is required");
            return false;
        }

        if (selectedjobType.getText().toString() == "") {
            selectedjobType.setError("Please select job type");
            return false;
        }

        if (rate.length() == 0) {
            rate.setError("This field is required");
            return false;
        } else if (testing.checkRateLength(rate.getText().toString())) {
            rate.setError("Price rate must be maximum 4 characters");
            return false;
        }

        if (contact.length() == 0) {
            contact.setError("This field is required");
            return false;
        } else if (testing.checkContactLength(contact.getText().toString())) {
            contact.setError("Contact no must be 10 characters");
            return false;
        }

        // after all validation return true
        return true;
    }
}