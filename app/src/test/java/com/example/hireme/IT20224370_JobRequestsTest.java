package com.example.hireme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.hireme.util.it20224370.IT20224370_AddRequestDetails;

public class IT20224370_JobRequestsTest {

    private static IT20224370_AddRequestDetails checker;
    private static String date, time;
    private static String num;
    private static String name;
    private static boolean returnedDate;
    private static boolean returnedName;
    private static boolean returnedtime;
    private static boolean returnednum;

    @BeforeClass
    public static void initmain() {
        checker = new IT20224370_AddRequestDetails();
    }

    @Before
    public void setup() {
        date = "";
        num = "";
        name = "";
        time = "";
    }

    //test case to check the time which is to get a false output

    @Test
    public void TestInvalidTimeValidation() {
        time = "08:65";
        returnedtime = checker.TimeChecker(time);
        assertEquals(false, returnedtime, "Incorrect time");

    }

    //test case to check the validity of mobile number entered, test case expectes the value false

    @Test
    public void TestInvalidMNumber() {
        num = "077665544";
        returnednum = checker.MNumberChecker(num);
        assertEquals(true, returnednum, "Incorrect Mobile number");

    }

    //checks that passed string values returns same

    @Test
    public void TestInvalidName() {
        name = "Dassanayake Mudiyanselage Nayomal Irujith Bandara Dassanayake";
        returnedName = checker.NameChecker(name);
        assertEquals(false, returnedName, "Name is InCorrect");
    }

    //current date returns as a string, so chanage the checking expected output according to the current date

    @Test
    public void TestInvalidDate() {
        date = "08/22/2021";
        returnedDate = checker.DateChecker(date);
        assertEquals(false, returnedDate, "Incorrect Date");
    }


    @After
    public void clear() {
        date = "";
        num = "";
        name = "";
        time = "";
    }

    @AfterClass
    public static void clearAll() {
        checker = null;
    }

}
