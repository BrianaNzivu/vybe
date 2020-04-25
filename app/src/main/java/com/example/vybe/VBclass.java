package com.example.vybe;

import android.os.Parcel;
import android.os.Parcelable;

public class VBclass implements Parcelable {

    String Title,Description,Date,Categories;

    public VBclass(String Title, String Description,String Date,String Categories) {

    }

    public VBclass()
    {
       // Empty Constructor
    }

    protected VBclass(Parcel in) {
        Title = in.readString();
        Description = in.readString();
        Date = in.readString();
        Categories = in.readString();
    }


    public static final Creator<VBclass> CREATOR = new Creator<VBclass>() {
        @Override
        public VBclass createFromParcel(Parcel in) {
            return new VBclass(in);
        }

        @Override
        public VBclass[] newArray(int size) {
            return new VBclass[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getDate() {
        return Date;
    }

    public String getCategories() {
        return Categories;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setDate(String Date) {
        this.Date= Date;
    }

    public void setCategories(String categories) {
        this.Categories= categories;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Description);
        dest.writeString(Date);
        dest.writeString(Categories);
    }
}

