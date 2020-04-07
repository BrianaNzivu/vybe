package com.example.vybe;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class IntentService extends android.app.IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentService(String name) {
        super("IntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
//Connecting to Background class.
        Log.d("Handler","Handler starting");

        Background background = new Background(this);
        String action = intent.getAction();

//Writing to Firebase Database for VisionBoardEnt class.
        if(Background.vision.equals(action))
        {
            VBclass vision = new VBclass();
            vision = intent.getParcelableExtra("vision");
            background.writeToFirebase(vision);
        }
    }
}
