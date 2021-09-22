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
                if(dataSnapshot.exists()) {
                    maxID = dataSnapshot.getChildrenCount();

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
                    cusID = dataSnapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public long getNextID(){

        return maxID + 1;
    }

    public long getCusID() {
        return cusID + 1;
    }
}
