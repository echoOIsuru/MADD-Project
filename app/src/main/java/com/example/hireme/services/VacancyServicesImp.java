/**
 * MAD PROJECT 2021
 * Y2-S2
 *
 * @author IT20133290 R.M.Isuru Sahan Kumarasingha
 */
package com.example.hireme.services;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hireme.database.Connection;
import com.example.hireme.frontend.it20133290.IT20133290_AddVacancy;
import com.example.hireme.models.Vacancies;
import com.example.hireme.util.CommonUtils;

import java.util.Date;

public class VacancyServicesImp implements VacancyServices {
    //get model class instance
    Vacancies vacancies = new Vacancies();

    //firebase instance
    Connection con = new Connection();
    CommonUtils cu = new CommonUtils();


    @Override
    public void addNewVacancy(Context c, EditText jobTitle, EditText organization, EditText jobFamily,
                              EditText jobLevel, EditText description, EditText salary, Date deadline) {

        try {
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
            else {
                vacancies.setJobTitle(jobTitle.getText().toString().trim());
                vacancies.setJobFamily(jobFamily.getText().toString().trim());
                vacancies.setJobLevel(jobLevel.getText().toString().trim());
                vacancies.setOrganization(organization.getText().toString().trim());
                vacancies.setSalary(salary.getText().toString().trim());
                vacancies.setDescription(description.getText().toString().trim());
                vacancies.setDeadline(deadline);

                con.getRef().child(String.valueOf(cu.getNextID())).setValue(vacancies);

                Toast.makeText(c, "Data Inserted Successfully", Toast.LENGTH_LONG).show();

                //clear entered values
                clearVacancyForm(jobTitle, organization, jobFamily,
                        jobLevel, description,salary);
            }

        } catch (Exception e) {

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

}
