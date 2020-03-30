package com.example.vybe;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class VisionBoardClass {

    public String Category;
    public String Title;
    public String Date;
    public String Description;

    public VisionBoardClass (){
        //Empty constructor required
    }

    public VisionBoardClass(String Category , String Title , String Date , String Description ){
        this.Category = Category;
        this.Title = Title;
        this.Date = Date;
        this.Description = Description;

    }
}

