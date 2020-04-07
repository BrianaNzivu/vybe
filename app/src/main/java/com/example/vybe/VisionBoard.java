package com.example.vybe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VisionBoard extends AppCompatActivity {
    private Button cVision;
    private ImageView vback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_board);

        cVision =(Button) findViewById (R.id.bVisionOne);
        vback=(ImageView) findViewById(R.id.hVison);


        cVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VisionBoard.this ,VisionBoardEnt.class);
                startActivity(intent);
            }
        });
    }
}
