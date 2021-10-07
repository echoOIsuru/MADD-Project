package com.example.hireme.util.it20231682;

import android.widget.EditText;

import com.example.hireme.frontend.it20231682.IT20231682_give_feedback;

public class IT20231682_feedback_rating_calculate {


    //calculate average rating
    public Float calcRate(int ratecount , Float totalrate){
        return (totalrate/ratecount);
    }

    //review not null
    public boolean reviewVaild(String val) {
        return ! (val.isEmpty());
    }

    //ratingbar is null
    public int RateInvaild(int val1) {
        return  (val1 = 0);
    }



//    //validate input
//    public boolean vaildInputs(int val1 , String val){
//        return ! (val1 = 0 || val.isEmpty());
//    }


}





