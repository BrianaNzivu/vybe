package com.example.vybe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiaryViewPager extends AppCompatActivity {

    private Button mDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_view_pager);

        mDiary = (Button) findViewById(R.id.gotodiary);

        mDiary.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DiaryViewPager.this ,DiaryEnt.class);
                startActivity(intent);
            }
        });
    }
}
