package com.example.hireme.frontend.it20133290;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hireme.R;
import com.example.hireme.models.AppUser;
import com.example.hireme.models.Vacancies;
import com.example.hireme.services.it20133290.VacancyServicesImp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class IT201333290_RegisterActivity extends AppCompatActivity {

    EditText name,email,tp,pass,repass;
    Button submit,chooseImg;
    ProgressBar pb;
    ImageView imgUpload;
    Vacancies vacancies;
    AppUser appUser;
    VacancyServicesImp vacSer = new VacancyServicesImp();
    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 1;
    String imageName;

    Uri mImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it201333290_register);
        getSupportActionBar().hide();

        name = findViewById(R.id.etUserName);
        email = findViewById(R.id.etUserEmail);
        tp = findViewById(R.id.etUserContact);
        pass = findViewById(R.id.etUserPassword);
        repass = findViewById(R.id.etReUserPassword);
        submit = findViewById(R.id.btnRegister);
        imgUpload = findViewById(R.id.ivUploadProfImg);
        chooseImg = findViewById(R.id.btnUploadProfImg);
        pb = findViewById(R.id.pbUploadImage);

        mStorageRef = FirebaseStorage.getInstance("gs://hireme-2e86a.appspot.com/").getReference("uploads");

        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   uploadFile();
                vacSer.addNewUser(IT201333290_RegisterActivity.this,name,tp,email,pass,repass,mImageUri,pb);

            }
        });


    }

    private void openFileChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() != null){
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(imgUpload);

        }
    }



}