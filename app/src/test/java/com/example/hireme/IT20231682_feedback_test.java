package com.example.hireme;

import com.example.hireme.util.it20231682.IT20231682_feedback_rating_calculate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IT20231682_feedback_test {


    private IT20231682_feedback_rating_calculate it20231682_feedback_rating_calculate;
    private  double ratecount;
    private  double totalrate;
    private String val;
    private int val1;

    //calculate rating
    @Before
    public void setup(){
        it20231682_feedback_rating_calculate = new IT20231682_feedback_rating_calculate();
    }

    @Test
    public void testcalcrate(){
        Float result = it20231682_feedback_rating_calculate.calcRate(25, 100F);
        assertEquals(4.00,result,0.001);
    }

    //review Valid
    @Test
    public void whenInputsIsValid(){

        val = "Good job";
        boolean result = it20231682_feedback_rating_calculate.reviewVaild(val);
        assertEquals("Valid input", true,result);
    }

    //review Invalid
    @Test
    public void whenInputsIsInvalid(){
        val = "";
        boolean result = it20231682_feedback_rating_calculate.reviewVaild(val);
        assertEquals("Valid input", false,result);
    }

    //Invalid (rating is 0)
    @Test
    public void whenRatingIsZero(){
        val = "";
        Integer result = it20231682_feedback_rating_calculate.RateInvaild(4);
        assertEquals(0,result,4);
    }









}
