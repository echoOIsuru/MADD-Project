/**
 * MAD PROJECT 2021
 * Y2-S2
 * @author IT20133290 R.M.Isuru Sahan Kumarasingha
 */
package com.example.hireme.util;

import androidx.annotation.NonNull;

import com.example.hireme.database.Connection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CommonUtils {

    Connection con = new Connection();
    DatabaseReference ref = con.getRef().child("Vacancies");
    DatabaseReference ref2 = con.getRef().child("AppUser");
    private long maxID = 0;
    private long cusID = 0;

    public CommonUtils() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val = "";
                if(dataSnapshot.exists()) {
                    maxID = dataSnapshot.getChildrenCount(); //get data count

                    String nextID = Long.toString(maxID); // convert into string

                    for(DataSnapshot temp : dataSnapshot.getChildren()){
                        val = temp.getKey();
                        //check if it's in the database
                        while (val.equals("VID"+nextID)){
                            maxID++;
                            nextID = Long.toString(maxID);
                        }
                    }

                    maxID = Long.parseLong(nextID);
                }else{
                    maxID = 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String val = "";
                    cusID = dataSnapshot.getChildrenCount();

                    String nextID = Long.toString(cusID);
                    for(DataSnapshot temp : dataSnapshot.getChildren()){
                        val = temp.getKey();
                        while (val.equals("CUID"+nextID)){
                            cusID++;
                            nextID = Long.toString(cusID);
                        }
                    }


                    cusID = Long.parseLong(nextID);

                }else{
                    cusID = 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public long getNextID(){

        return maxID;
    }

    public long getCusID() {
        return cusID;
    }
}
