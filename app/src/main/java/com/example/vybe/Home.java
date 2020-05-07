package com.example.vybe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    private ImageView myVision;
    private ImageView myDiary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        myVision=(ImageView) findViewById(R.id.hVison);
        myDiary = (ImageView) findViewById(R.id.diary);

        myVision.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent =new Intent(Home.this ,VisionBoard.class);
                startActivity(intent);
            }
        });

        myDiary.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent =new Intent(Home.this ,DiaryViewPager.class);
                startActivity(intent);
            }
        });


    }
}
