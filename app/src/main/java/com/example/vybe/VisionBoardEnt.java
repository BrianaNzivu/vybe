package com.example.vybe;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class VisionBoardEnt extends AppCompatActivity {

    private static TextView DateEdit;
    private ArrayList<VisionBoardCategory> Categories;
    private CategoriesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_board_ent);


        DateEdit = (TextView) findViewById(R.id.vDate);
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonDatePickerDialog(v);
            }
        });


            initList();

        Log.d("Spinner" ,"Spinner activated");
            Spinner spinnerCategories = findViewById(R.id.category);

            mAdapter = new CategoriesAdapter(this, Categories);
            spinnerCategories.setAdapter(mAdapter);

            spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    VisionBoardCategory clickedItem = (VisionBoardCategory) parent.getItemAtPosition(position);
                    String clickedmCategories = clickedItem.mCategories();
                    Toast.makeText(VisionBoardEnt.this, clickedmCategories + " selected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        Log.d("Dropdown" ,"Dropdown activated");
    }

        private void initList() {
            Categories = new ArrayList<>();
            Categories.add(new VisionBoardCategory("India", R.drawable.dpg));
            Categories.add(new VisionBoardCategory("China", R.drawable.hpg));
            Categories.add(new VisionBoardCategory("USA", R.drawable.jpg));
            Categories.add(new VisionBoardCategory("Germany", R.drawable.vpg));
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