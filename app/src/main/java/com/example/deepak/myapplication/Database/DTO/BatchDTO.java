package com.example.deepak.myapplication.Database.DTO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deepak on 4/22/2017.
 */

public class BatchDTO implements Parcelable {

    private String name;
    private Long id;

    private String details;


    public BatchDTO(){

    }


    protected BatchDTO(Parcel in) {
        this.name = in.readString();
        this.details = in.readString();
        this.id = in.readLong();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(details);
        dest.writeLong(id);
    }


    public static final Parcelable.Creator<BatchDTO> CREATOR = new Parcelable.Creator<BatchDTO>() {
        @Override
        public BatchDTO createFromParcel(Parcel in) {
            return new BatchDTO(in);
        }

        @Override
        public BatchDTO[] newArray(int size) {
            return new BatchDTO[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }








}
