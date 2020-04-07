package com.example.vybe;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class VisionBoardEnt extends AppCompatActivity {

    private static TextView DateEdit;
    private EditText mTitle;
    private EditText mDescription;
    private Spinner mCategories;
    private Button mButton;
    public Context context;
    String Title;
    String Description;
    String myCategory;
    String Date;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_board_ent);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Database").child("Vision");

        DateEdit = (TextView) findViewById(R.id.vDate);
        mTitle = (EditText) findViewById(R.id.vTitle);
        mDescription = (EditText) findViewById(R.id.vDescription);
        mCategories = (Spinner) findViewById(R.id.spinner);
        mButton = (Button) findViewById(R.id.vButton);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String text = spinner.getSelectedItem().toString();


        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonDatePickerDialog(v);
            }

        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCategory = mCategories.toString();
                Title = mTitle.getText().toString();
                Date = DateEdit.toString();
                Description = mDescription.getText().toString();

                final VBclass vision = new VBclass();

                Intent intent = new Intent(VisionBoardEnt.this, IntentService.class);
                intent.setAction(Background.vision);
                intent.putExtra("vision", (Parcelable) vision);

                Log.d("vision","Added vision to Firebase");

                startActivity(intent);

            }
        });
    }
    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit.setText(day + "/" + (month + 1) + "/" + year);
        }

        }
    }
