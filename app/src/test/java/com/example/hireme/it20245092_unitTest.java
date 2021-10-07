package com.example.hireme;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.hireme.util.it20245092.it20245092_workerManagementTesting;

public class it20245092_unitTest {

    private static it20245092_workerManagementTesting testActivity;

    @Before
    public void setup(){
        testActivity = new it20245092_workerManagementTesting();
    }

    @Test
    public void checkEmpty(){
        boolean result = testActivity.checkEmpty("");
        assertEquals(true,result);
    }

    @Test
    public void checkRateLength(){
        boolean result = testActivity.checkRateLength("55000");
        assertEquals(true,result);
    }

    @Test
    public void checkContactLength(){
        boolean result = testActivity.checkContactLength("071456956");
        assertEquals(true,result);
    }

    @AfterClass
    public static void clearAll(){
        testActivity = null;
    }

}
