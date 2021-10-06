/**
 * MAD PROJECT 2021
 * Y2-S2
 *
 * @author IT20133290 R.M.Isuru Sahan Kumarasingha
 */
package com.example.hireme.services.it20133290;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hireme.database.Connection;
import com.example.hireme.frontend.DashBoard;
import com.example.hireme.frontend.it20133290.IT20133290_AddVacancy;
import com.example.hireme.frontend.it20133290.IT20133290_CustomerMenu;
import com.example.hireme.frontend.it20133290.IT20133290_LoginActivity;
import com.example.hireme.frontend.it20133290.IT201333290_RegisterActivity;
import com.example.hireme.models.AppUser;
import com.example.hireme.models.Vacancies;
import com.example.hireme.util.CommonUtils;
import com.example.hireme.util.VacancyAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.security.Key;
import java.util.Date;
import java.util.Locale;

public class VacancyServicesImp implements VacancyServices {
    //get model class instance
    Vacancies vacancies = new Vacancies();
    AppUser appUser = new AppUser();

    //firebase instance
    Connection con = new Connection();
    CommonUtils cu = new CommonUtils();
    VacancyAdapter vacancyAdapter;
    RecyclerView rvAll;
    private StorageTask mUploadTask;


    @Override
    public void addNewVacancy(Context c, EditText jobTitle, EditText organization, AutoCompleteTextView jobFamily,
                              AutoCompleteTextView jobLevel, EditText description, EditText salary, String deadline, String email) {

        String desPattern = ".{1,200}"; // creating pattern for description
        try {
            //check input filed are empty or not
            if (TextUtils.isEmpty(jobTitle.getText().toString()))
                Toast.makeText(c, "Please enter job title", Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(jobFamily.getText().toString()))
                Toast.makeText(c, "Please enter job family", Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(jobLevel.getText().toString()))
                Toast.makeText(c, "Please enter job level", Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(organization.getText().toString()))
                Toast.makeText(c, "Please enter job organization", Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(salary.getText().toString()))
                Toast.makeText(c, "Please enter job salary", Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(description.getText().toString()))
                Toast.makeText(c, "Please enter job description", Toast.LENGTH_LONG).show();
            else if (!(description.getText().toString()).matches(desPattern))
                Toast.makeText(c, "job description max limit is 200 letters", Toast.LENGTH_LONG).show();
            else {
                vacancies.setJobTitle(jobTitle.getText().toString().trim());
                vacancies.setJobFamily(jobFamily.getText().toString().trim());
                vacancies.setJobLevel(jobLevel.getText().toString().trim());
                vacancies.setOrganization(organization.getText().toString().trim());
                vacancies.setSalary(salary.getText().toString().trim());
                vacancies.setDescription(description.getText().toString().trim());
                vacancies.setDeadline(deadline);
                vacancies.setEmail(email);
                //insert value in to database
                con.getRef().child("Vacancies").child("VID" + (cu.getNextID())).setValue(vacancies);

                Toast.makeText(c, "Data Inserted Successfully", Toast.LENGTH_LONG).show();

                Intent i = new Intent(c, IT20133290_CustomerMenu.class);
                i.putExtra("email", email);
                c.startActivity(i);

                //clear entered values
                clearVacancyForm(jobTitle, organization, jobFamily,
                        jobLevel, description, salary);
            }
        } catch (Exception e) {
            Toast.makeText(c, "Data Inserted Unsuccessful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public FirebaseRecyclerOptions<Vacancies> txtSearch(String str) {
        int val = 0;
        String val2 = "";

        //check the value is number or string
        try {
            val = Integer.parseInt(str);
            val2 = "" + val;
            //find values by salary
            FirebaseRecyclerOptions<Vacancies> options = new FirebaseRecyclerOptions.Builder<Vacancies>().
                    setQuery(con.getRef().child("Vacancies").orderByChild("salary").startAt(val2).endAt(val2 + "~"), Vacancies.class).build();
            return options;
        } catch (Exception e) {
            //find values by job title
            FirebaseRecyclerOptions<Vacancies> options = new FirebaseRecyclerOptions.Builder<Vacancies>().
                    setQuery(con.getRef().child("Vacancies").orderByChild("jobTitle").startAt(str).endAt(str + "~"), Vacancies.class).build();
            return options;
        }

    }

    @Override
    public void addNewUser(Context c, EditText name, EditText tp, EditText email, EditText password, EditText repassword, Uri mImageUri, ProgressBar pb) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        try {
            if (TextUtils.isEmpty(name.getText().toString()))
                Toast.makeText(c, "Please enter your name", Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(tp.getText().toString()))
                Toast.makeText(c, "Please enter mobile number", Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, "Please enter email", Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, "Please enter password", Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(repassword.getText().toString()))
                Toast.makeText(c, "Please enter password", Toast.LENGTH_LONG).show();

            else if (!password.getText().toString().equals(repassword.getText().toString()))
                Toast.makeText(c, "Password Mismatched", Toast.LENGTH_LONG).show();

            else if (!(email.getText().toString()).matches(emailPattern))
                Toast.makeText(c, "Incorrect Email", Toast.LENGTH_LONG).show();
            else {

                String key = email.getText().toString().trim();
                appUser.setName(name.getText().toString().trim());
                appUser.setTel(tp.getText().toString().trim());
                appUser.setEmail(email.getText().toString().trim());
                appUser.setPassword(password.getText().toString().trim());

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(c, "Progressing...", Toast.LENGTH_SHORT);
                } else {
                    uploadFile(mImageUri, pb, c, appUser);
                }


            }

        } catch (Exception e) {

        }


    }

    @Override
    public void validateUser(Context c, EditText email, EditText password) {
        //create patter for email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        try {
            //check input fields are empty or not
            if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, "Please enter email", Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, "Please enter password", Toast.LENGTH_LONG).show();

            else if (!(email.getText().toString()).matches(emailPattern))
                Toast.makeText(c, "Incorrect Email", Toast.LENGTH_LONG).show();

            else {
                String enteredEmail = email.getText().toString();
                String enteredPassword = password.getText().toString();
                //validate user
                Query checkUser = con.getRef().child("AppUser").orderByChild("email").equalTo(enteredEmail);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String val = "ok";
                            for (DataSnapshot temp : snapshot.getChildren()) {
                                val = temp.getKey();
                            }

                            String dbPassword = snapshot.child(val).child("password").getValue(String.class);
                            //get user values
                            if (dbPassword.equals(enteredPassword)) {
                                String dbName = snapshot.child(val).child("name").getValue(String.class);
                                String dbTel = snapshot.child(val).child("tel").getValue(String.class);
                                String dbEmail = snapshot.child(val).child("email").getValue(String.class);

                                Intent i = new Intent(c, DashBoard.class);

                                //i.putExtra("pass",dbPassword);
                                i.putExtra("name", dbName);
                                i.putExtra("tel", dbTel);
                                i.putExtra("email", dbEmail);

                                c.startActivity(i);
                            } else {
                                Toast.makeText(c, "The Entered Password is incorrect", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(c, "No such user exist", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        } catch (Exception e) {
            Toast.makeText(c, "Try again", Toast.LENGTH_LONG).show();
        }

    }


    public void clearVacancyForm(EditText jobTitle, EditText organization, EditText jobFamily,
                                 EditText jobLevel, EditText description, EditText salary) {
        jobTitle.setText("");
        organization.setText("");
        jobFamily.setText("");
        jobLevel.setText("");
        description.setText("");
        salary.setText("");
    }


    private String getFileExtension(Uri uri, Context c) {
        ContentResolver cR = c.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void uploadFile(Uri mImageUri, ProgressBar pb, Context c, AppUser appUser) {
        StorageReference mStorageRef = FirebaseStorage.getInstance("gs://hireme-2e86a.appspot.com/").getReference("uploads");


        if (mImageUri != null) {
            System.out.println(mImageUri + "     asdasdasdassssssssssssssss");
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri, c));

            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(0);
                        }
                    }, 500);

                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            //Do what you want with the url

                            appUser.setImg(downloadUrl.toString());
                            con.getRef().child("AppUser").child("CUID" + (cu.getCusID())).setValue(appUser);


                            Toast.makeText(c, "Account created Successfully, Now you can log in", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(c, IT20133290_LoginActivity.class);
                            c.startActivity(i);
                        }

                    });

                            //Toast.makeText(c,"Upload successful", Toast.LENGTH_SHORT).show();




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    pb.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(c, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
