package com.example.hireme.frontend.it20224370;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hireme.services.it20133290.VacancyServicesImp;

public class IT20224370_Session_Management extends AppCompatActivity {

    private SharedPreferences prefs;

    public IT20224370_Session_Management(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
    }
    public void setName(String name) {
        prefs.edit().putString("name", name).commit();
    }
    public void setImg(String img) {
        prefs.edit().putString("img", img).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }

    public String getName() {
        String name = prefs.getString("name","");
        return name;
    }

    public String getImg() {
        String img = prefs.getString("img","");
        return img;
    }

}
