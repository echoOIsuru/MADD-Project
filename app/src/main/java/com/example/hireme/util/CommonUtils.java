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
                    maxID = dataSnapshot.getChildrenCount();
                    maxID++;
                    String nextID = Long.toString(maxID);
                    for(DataSnapshot temp : dataSnapshot.getChildren()){
                        val = temp.getKey();
                    }
//                    System.out.println(val+" -----Value----------------");
//                    System.out.println(nextID+" -----NextID----------------");

                    while (val.equals("VID"+nextID)){
                        maxID++;
                        nextID = Long.toString(maxID);
                    }

                    maxID = Long.parseLong(nextID);
//                    System.out.println(maxID+" -----MAX----------------ID");
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
                    cusID++;
                    String nextID = Long.toString(cusID);
                    for(DataSnapshot temp : dataSnapshot.getChildren()){
                        val = temp.getKey();
                    }
                    //System.out.println(val+" -----Value----------------");
                   // System.out.println(nextID+" -----NextID----------------");

                    while (val.equals("CUID"+nextID)){
                        cusID++;
                        nextID = Long.toString(cusID);
                    }

                    cusID = Long.parseLong(nextID);
                    //System.out.println(cusID+" -----MAX----------------ID");

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
