package com.example.hireme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IT20231682_give_feedback extends AppCompatActivity {

    //Initialize Variables
    TextView rateCount ;
    RatingBar ratingBar;
    float rateValue;
    Button btnsave;
    IT20231682_feedback_model it20231682_feedback_model;
    EditText name,email,review;
    FirebaseDatabase database;
    DatabaseReference reference;

    int id=0;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20231682_give_feedback);

        //Assign variable
        rateCount = findViewById(R.id.ratecount);
        ratingBar = findViewById(R.id.feedrate);
        btnsave = findViewById(R.id.btnAdd);
        it20231682_feedback_model = new IT20231682_feedback_model();
        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        review = findViewById(R.id.textReview);

        //Initialize validation styles
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //add validation for name
        awesomeValidation.addValidation(this,R.id.textName,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //add validation for email address
        awesomeValidation.addValidation(this,R.id.textEmail,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //add validation for rating bar
//        awesomeValidation.addValidation(this,R.id.feedrate,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_rating);
        //add validation for feedback
        awesomeValidation.addValidation(this,R.id.textReview,
                RegexTemplate.NOT_EMPTY,R.string.invalide_review);




        reference = database.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    id = (int) snapshot.getChildrenCount();
                }

                //Toast.makeText(MainActivity.this, "Data Inserted Successfully !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // display toast message data not saved
                Toast.makeText(IT20231682_give_feedback.this, "Error while Insertion !", Toast.LENGTH_SHORT).show();
            }
        });

        //get rating count according to the rating bar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                rateValue = ratingBar.getRating();
                if (rateValue<=1 && rateValue > 0)
                    rateCount.setText("Bad" + rateValue + "/5");
                else if (rateValue<=2 && rateValue > 1)
                    rateCount.setText("Ok" + rateValue + "/5");
                else if (rateValue<=3 && rateValue > 2)
                    rateCount.setText("Good" + rateValue + "/5");
                else if (rateValue<=4 && rateValue > 3)
                    rateCount.setText("Very good" + rateValue + "/5");
                else if (rateValue<=5 && rateValue > 4)
                    rateCount.setText("Best" + rateValue + "/5");
            }
        });

        // Save button
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check validation
                if (awesomeValidation.validate()){
                    //on success
                    insertFeedbackData();
                    Toast.makeText(getApplicationContext(),"Successfully data inserted " , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),IT20231682_feedback_view.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data inserted unsuccessfully " , Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void insertFeedbackData(){


        it20231682_feedback_model.setName(name.getText().toString());
        it20231682_feedback_model.setEmail(email.getText().toString());
        it20231682_feedback_model.setReview(review.getText().toString());
        it20231682_feedback_model.setRate(rateCount.getText().toString());

        reference.child(String.valueOf(id+1)).setValue(it20231682_feedback_model);


    }

}