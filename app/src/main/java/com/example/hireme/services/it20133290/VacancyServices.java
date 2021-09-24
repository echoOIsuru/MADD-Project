/**
 * MAD PROJECT 2021
 * Y2-S2
 * @author IT20133290 R.M.Isuru Sahan Kumarasingha
 */

package com.example.hireme.services.it20133290;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.hireme.models.Vacancies;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Date;

public interface VacancyServices {

    void addNewVacancy(Context c, EditText jobTitle, EditText  organization, AutoCompleteTextView jobFamily,
                       AutoCompleteTextView  jobLevel, EditText  description, EditText  salary, String deadline,String email);

    FirebaseRecyclerOptions<Vacancies> txtSearch(String str);

    void addNewUser(Context c, EditText name, EditText tp, EditText email, EditText password, EditText repassword);

    void validateUser(Context c, EditText email, EditText password);


}
