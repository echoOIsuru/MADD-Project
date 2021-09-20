/**
 * MAD PROJECT 2021
 * Y2-S2
 * @author IT20133290 R.M.Isuru Sahan Kumarasingha
 */

package com.example.hireme.services;

import android.content.Context;
import android.widget.EditText;

import java.util.Date;

public interface VacancyServices {

    void addNewVacancy(Context c, EditText jobTitle, EditText  organization, EditText  jobFamily,
                       EditText  jobLevel, EditText  description, EditText  salary, Date deadline);



}
