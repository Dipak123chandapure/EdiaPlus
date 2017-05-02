package com.example.deepak.myapplication.Database.DTO;
import android.os.Parcel;
import android.os.Parcelable;

public class DropDownDataDTO implements Parcelable {

    private String title;
    private Long id;
    private String details;
    private Boolean isSystemValue;
    private Boolean isVirtuallyDeleted;
    private Boolean isChecked = false;

    public DropDownDataDTO(){

    }

    protected DropDownDataDTO(Parcel in) {
        this.title = in.readString();
        this.id = in.readLong();
        this.details = in.readString();
        this.isSystemValue = in.readByte() != 0;
        this.isVirtuallyDeleted = in.readByte() != 0;
        this.isChecked = in.readByte() != 0;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeLong(this.id);
        dest.writeString(this.details);
        dest.writeByte((byte) (isSystemValue ? 1 : 0));
        dest.writeByte((byte) (isVirtuallyDeleted ? 1 : 0));
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }


    public static final Creator<DropDownDataDTO> CREATOR = new Creator<DropDownDataDTO>() {
        @Override
        public DropDownDataDTO createFromParcel(Parcel in) {
            return new DropDownDataDTO(in);
        }

        @Override
        public DropDownDataDTO[] newArray(int size) {
            return new DropDownDataDTO[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Boolean getSystemValue() {
        return isSystemValue;
    }

    public void setSystemValue(Boolean systemValue) {
        isSystemValue = systemValue;
    }

    public Boolean getVirtuallyDeleted() {
        return isVirtuallyDeleted;
    }

    public void setVirtuallyDeleted(Boolean virtuallyDeleted) {
        isVirtuallyDeleted = virtuallyDeleted;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }



}
