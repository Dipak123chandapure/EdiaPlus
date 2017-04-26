package com.example.deepak.myapplication.Database.DTO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deepak on 4/26/2017.
 */

public class AttachmentDTO implements Parcelable {
    private int id;
    private String url;
    private String section;

    public AttachmentDTO() {
    }

    protected AttachmentDTO(Parcel in) {
        in.writeInt(id);
        in.writeString(url);
        in.writeString(section);
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        this.id = dest.readInt();
        this.url = dest.readString();
        this.section = dest.readString();
    }


    public static final Creator<AttachmentDTO> CREATOR = new Creator<AttachmentDTO>() {
        @Override
        public AttachmentDTO createFromParcel(Parcel in) {
            return new AttachmentDTO(in);
        }

        @Override
        public AttachmentDTO[] newArray(int size) {
            return new AttachmentDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
