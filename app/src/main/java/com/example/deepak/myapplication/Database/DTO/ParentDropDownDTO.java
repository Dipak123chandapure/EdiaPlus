package com.example.deepak.myapplication.Database.DTO;

import android.os.Parcel;
import android.os.Parcelable;


public class ParentDropDownDTO implements Parcelable {

    private String title;
    private Long id;
    private String details;
    private Boolean isSystemValue;


    private Boolean isVirtuallyDeleted;
    private String tableName;
    private String databaseKey;

    private Boolean isShown;
    private Boolean isCompulsory;
    private Boolean isDropDown;


    protected ParentDropDownDTO(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.details = in.readString();
        this.isSystemValue = in.readByte() != 0;
        this.isVirtuallyDeleted = in.readByte() != 0;
        this.isShown = in.readByte() != 0;
        this.isCompulsory = in.readByte() != 0;
        this.isDropDown = in.readByte() != 0;

        this.tableName = in.readString();
        this.databaseKey = in.readString();
    }
    public ParentDropDownDTO() {

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(details);
        dest.writeByte((byte) (isSystemValue ? 1 : 0));
        dest.writeByte((byte) (isVirtuallyDeleted ? 1 : 0));
        dest.writeString(tableName);
        dest.writeString(databaseKey);

        dest.writeByte((byte) (isShown ? 1 : 0));
        dest.writeByte((byte) (isCompulsory ? 1 : 0));
        dest.writeByte((byte) (isDropDown ? 1 : 0));
    }

    public static final Creator<ParentDropDownDTO> CREATOR = new Creator<ParentDropDownDTO>() {
        @Override
        public ParentDropDownDTO createFromParcel(Parcel in) {
            return new ParentDropDownDTO(in);
        }

        @Override
        public ParentDropDownDTO[] newArray(int size) {
            return new ParentDropDownDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseKey() {
        return databaseKey;
    }

    public void setDatabaseKey(String databaseKey) {
        this.databaseKey = databaseKey;
    }

    public Boolean getShown() {
        return isShown;
    }

    public void setShown(Boolean shown) {
        isShown = shown;
    }

    public Boolean getCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        isCompulsory = compulsory;
    }

    public Boolean getDropDown() {
        return isDropDown;
    }

    public void setDropDown(Boolean dropDown) {
        isDropDown = dropDown;
    }



}