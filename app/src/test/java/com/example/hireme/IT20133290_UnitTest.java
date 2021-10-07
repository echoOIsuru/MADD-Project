package com.example.hireme;

import static org.junit.Assert.assertEquals;

import com.example.hireme.services.it20133290.VacancyServicesImp;
import com.example.hireme.util.it20133290.VacancyValidation;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;


public class IT20133290_UnitTest {
    private static VacancyValidation vacancyValidation;
    private String name;
    private String password;
    private String email;


    @BeforeClass
    public static void createObject(){
        vacancyValidation = new VacancyValidation();
    }

    @Before
    public void setup(){
        name = "";
        password = "";
        email = "";
    }

    @Test
    public void validEmail(){
        email = "asdSaa";
        boolean result = vacancyValidation.isEmailValid(email);
        assertEquals( "invalid email",false, result);
    }

    @Test
    public void validPassword(){
        password = "abc1234";
        boolean result = vacancyValidation.isPasswordValid(password);
        assertEquals( "invalid password",false,result);

    }

    @Test
    public void validName(){
        name = "";
        boolean result = vacancyValidation.isNameEmpty(name);
        assertEquals("empty name",true,result);
    }

    @After
    public void clear(){
       name="";
       password="";
       email="";
    }

    @AfterClass
    public static void clearAll(){
        vacancyValidation = null;
    }

}
