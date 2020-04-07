package com.example.vybe;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Background {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Context context;
    public static final String vision = "Add vision";

    public  Background(Context context)
    {
        this.context = context;
    }
//Writing to Firebase Database from VisionBoardEnt.
    public void writeToFirebase(VBclass vision)
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Database").child("Vision").push().setValue(vision);
    }
}
