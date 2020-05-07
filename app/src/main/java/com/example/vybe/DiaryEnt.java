package com.example.vybe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class DiaryEnt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_ent);

        final TextView chooseTime = findViewById(R.id.dDate);

        chooseTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                TimePickerDialog timePickerDialog = new TimePickerDialog(DiaryEnt.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes)
                    {
                        chooseTime.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);

                timePickerDialog.show();
            }
        });
    }
}
