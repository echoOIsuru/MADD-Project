/**
 * MAD PROJECT 2021
 * Y2-S2
 * @author IT20133290 R.M.Isuru Sahan Kumarasingha
 */
package com.example.hireme.database;
import androidx.annotation.NonNull;

import com.example.hireme.util.it20133290.CommonConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Connection {

    private DatabaseReference ref;

    public Connection(){
        ref = FirebaseDatabase.getInstance(CommonConstants.FIREBASE_URL_1).getReference();

    }


    public DatabaseReference getRef() {
        return ref;
    }

    public void setRef(DatabaseReference ref) {
        this.ref = ref;
    }



}
