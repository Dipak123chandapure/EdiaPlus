package com.example.deepak.myapplication.Database.DTO;

import android.os.Parcel;
import android.os.Parcelable;



public class FormConstarins implements Parcelable{

    private String TITLE;
    private Boolean IS_VISIBLE;

    private String CHILD_ONE_TITLE;
    private Boolean IS_CHILD_ONE_VISIBLE;
    private Boolean IS_CHILD_ONE_EDITABLE;
    private Boolean IS_CHILD_ONE_COMPULSORY;

    private String CHILD_TWO_TITLE;
    private Boolean IS_CHILD_TWO_VISIBLE;
    private Boolean IS_CHILD_TWO_EDITABLE;
    private Boolean IS_CHILD_TWO_COMPULSORY;


    private String CHILD_THREE_TITLE;
    private Boolean IS_CHILD_THREE_VISIBLE;
    private Boolean IS_CHILD_THREE_EDITABLE;
    private Boolean IS_CHILD_THREE_COMPULSORY;


    private String CHILD_FOUR_TITLE;
    private Boolean IS_CHILD_FOUR_VISIBLE;
    private Boolean IS_CHILD_FOUR_EDITABLE;
    private Boolean IS_CHILD_FOUR_COMPULSORY;

    private String CHILD_FIVE_TITLE;
    private Boolean IS_CHILD_FIVE_VISIBLE;
    private Boolean IS_CHILD_FIVE_EDITABLE;
    private Boolean IS_CHILD_FIVE_COMPULSORY;

    private String CHILD_SIX_TITLE;
    private Boolean IS_CHILD_SIX_VISIBLE;
    private Boolean IS_CHILD_SIX_EDITABLE;
    private Boolean IS_CHILD_SIX_COMPULSORY;

    private String CHILD_SEVEN_TITLE;
    private Boolean IS_CHILD_SEVEN_VISIBLE;
    private Boolean IS_CHILD_SEVEN_EDITABLE;


    private Boolean IS_CHILD_SEVEN_COMPULSORY;


    public FormConstarins(){

    }

    protected FormConstarins(Parcel in) {
        this.IS_VISIBLE = in.readByte() != 0;
        this.TITLE = in.readString();

        this.CHILD_ONE_TITLE = in.readString();
        this.IS_CHILD_ONE_VISIBLE = in.readByte() != 0;
        this.IS_CHILD_ONE_EDITABLE = in.readByte() != 0;
        this.IS_CHILD_ONE_COMPULSORY = in.readByte() != 0;

        this.CHILD_TWO_TITLE = in.readString();
        this.IS_CHILD_TWO_VISIBLE = in.readByte() != 0;
        this.IS_CHILD_TWO_EDITABLE = in.readByte() != 0;
        this.IS_CHILD_TWO_COMPULSORY = in.readByte() != 0;

        this.CHILD_THREE_TITLE = in.readString();
        this.IS_CHILD_THREE_VISIBLE = in.readByte() != 0;
        this.IS_CHILD_THREE_EDITABLE = in.readByte() != 0;
        this.IS_CHILD_THREE_COMPULSORY = in.readByte() != 0;

        this.CHILD_FOUR_TITLE = in.readString();
        this.IS_CHILD_FOUR_VISIBLE = in.readByte() != 0;
        this.IS_CHILD_FOUR_EDITABLE = in.readByte() != 0;
        this.IS_CHILD_FOUR_COMPULSORY = in.readByte() != 0;

        this.CHILD_FIVE_TITLE = in.readString();
        this.IS_CHILD_FIVE_VISIBLE = in.readByte() != 0;
        this.IS_CHILD_FIVE_EDITABLE = in.readByte() != 0;
        this.IS_CHILD_FIVE_COMPULSORY = in.readByte() != 0;


        this.CHILD_SIX_TITLE = in.readString();
        this.IS_CHILD_SIX_VISIBLE = in.readByte() != 0;
        this.IS_CHILD_SIX_EDITABLE = in.readByte() != 0;
        this.IS_CHILD_SIX_COMPULSORY = in.readByte() != 0;

        this.CHILD_SEVEN_TITLE = in.readString();
        this.IS_CHILD_SEVEN_VISIBLE = in.readByte() != 0;
        this.IS_CHILD_SEVEN_EDITABLE = in.readByte() != 0;
        this.IS_CHILD_SEVEN_COMPULSORY = in.readByte() != 0;


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeByte((byte) (IS_VISIBLE ? 1 : 0));
        dest.writeString(TITLE);

        dest.writeString(CHILD_ONE_TITLE);
        dest.writeByte((byte) (IS_CHILD_ONE_VISIBLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_ONE_EDITABLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_ONE_COMPULSORY ? 1 : 0));

        dest.writeString(CHILD_TWO_TITLE);
        dest.writeByte((byte) (IS_CHILD_TWO_VISIBLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_TWO_EDITABLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_TWO_COMPULSORY ? 1 : 0));

        dest.writeString(CHILD_THREE_TITLE);
        dest.writeByte((byte) (IS_CHILD_THREE_VISIBLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_THREE_EDITABLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_THREE_COMPULSORY ? 1 : 0));

        dest.writeString(CHILD_FOUR_TITLE);
        dest.writeByte((byte) (IS_CHILD_FOUR_VISIBLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_FOUR_EDITABLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_FOUR_COMPULSORY ? 1 : 0));

        dest.writeString(CHILD_FIVE_TITLE);
        dest.writeByte((byte) (IS_CHILD_FIVE_VISIBLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_FIVE_EDITABLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_FIVE_COMPULSORY ? 1 : 0));


        dest.writeString(CHILD_SIX_TITLE);
        dest.writeByte((byte) (IS_CHILD_SIX_VISIBLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_SIX_EDITABLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_SIX_COMPULSORY ? 1 : 0));


        dest.writeString(CHILD_SEVEN_TITLE);
        dest.writeByte((byte) (IS_CHILD_SEVEN_VISIBLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_SEVEN_EDITABLE ? 1 : 0));
        dest.writeByte((byte) (IS_CHILD_SEVEN_COMPULSORY ? 1 : 0));


    }


    public static final Parcelable.Creator<FormConstarins> CREATOR = new Parcelable.Creator<FormConstarins>() {
        @Override
        public FormConstarins createFromParcel(Parcel in) {
            return new FormConstarins(in);
        }

        @Override
        public FormConstarins[] newArray(int size) {
            return new FormConstarins[size];
        }
    };


    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public Boolean getIS_VISIBLE() {
        return IS_VISIBLE;
    }

    public void setIS_VISIBLE(Boolean IS_VISIBLE) {
        this.IS_VISIBLE = IS_VISIBLE;
    }

    public Boolean getIS_CHILD_ONE_VISIBLE() {
        return IS_CHILD_ONE_VISIBLE;
    }

    public void setIS_CHILD_ONE_VISIBLE(Boolean IS_CHILD_ONE_VISIBLE) {
        this.IS_CHILD_ONE_VISIBLE = IS_CHILD_ONE_VISIBLE;
    }

    public String getCHILD_ONE_TITLE() {
        return CHILD_ONE_TITLE;
    }

    public void setCHILD_ONE_TITLE(String CHILD_ONE_TITLE) {
        this.CHILD_ONE_TITLE = CHILD_ONE_TITLE;
    }

    public Boolean getIS_CHILD_ONE_EDITABLE() {
        return IS_CHILD_ONE_EDITABLE;
    }

    public void setIS_CHILD_ONE_EDITABLE(Boolean IS_CHILD_ONE_EDITABLE) {
        this.IS_CHILD_ONE_EDITABLE = IS_CHILD_ONE_EDITABLE;
    }

    public Boolean getIS_CHILD_ONE_COMPULSORY() {
        return IS_CHILD_ONE_COMPULSORY;
    }

    public void setIS_CHILD_ONE_COMPULSORY(Boolean IS_CHILD_ONE_COMPULSORY) {
        this.IS_CHILD_ONE_COMPULSORY = IS_CHILD_ONE_COMPULSORY;
    }

    public String getCHILD_TWO_TITLE() {
        return CHILD_TWO_TITLE;
    }

    public void setCHILD_TWO_TITLE(String CHILD_TWO_TITLE) {
        this.CHILD_TWO_TITLE = CHILD_TWO_TITLE;
    }

    public Boolean getIS_CHILD_TWO_VISIBLE() {
        return IS_CHILD_TWO_VISIBLE;
    }

    public void setIS_CHILD_TWO_VISIBLE(Boolean IS_CHILD_TWO_VISIBLE) {
        this.IS_CHILD_TWO_VISIBLE = IS_CHILD_TWO_VISIBLE;
    }

    public Boolean getIS_CHILD_TWO_EDITABLE() {
        return IS_CHILD_TWO_EDITABLE;
    }

    public void setIS_CHILD_TWO_EDITABLE(Boolean IS_CHILD_TWO_EDITABLE) {
        this.IS_CHILD_TWO_EDITABLE = IS_CHILD_TWO_EDITABLE;
    }

    public Boolean getIS_CHILD_TWO_COMPULSORY() {
        return IS_CHILD_TWO_COMPULSORY;
    }

    public void setIS_CHILD_TWO_COMPULSORY(Boolean IS_CHILD_TWO_COMPULSORY) {
        this.IS_CHILD_TWO_COMPULSORY = IS_CHILD_TWO_COMPULSORY;
    }

    public String getCHILD_THREE_TITLE() {
        return CHILD_THREE_TITLE;
    }

    public void setCHILD_THREE_TITLE(String CHILD_THREE_TITLE) {
        this.CHILD_THREE_TITLE = CHILD_THREE_TITLE;
    }

    public Boolean getIS_CHILD_THREE_VISIBLE() {
        return IS_CHILD_THREE_VISIBLE;
    }

    public void setIS_CHILD_THREE_VISIBLE(Boolean IS_CHILD_THREE_VISIBLE) {
        this.IS_CHILD_THREE_VISIBLE = IS_CHILD_THREE_VISIBLE;
    }

    public Boolean getIS_CHILD_THREE_EDITABLE() {
        return IS_CHILD_THREE_EDITABLE;
    }

    public void setIS_CHILD_THREE_EDITABLE(Boolean IS_CHILD_THREE_EDITABLE) {
        this.IS_CHILD_THREE_EDITABLE = IS_CHILD_THREE_EDITABLE;
    }

    public Boolean getIS_CHILD_THREE_COMPULSORY() {
        return IS_CHILD_THREE_COMPULSORY;
    }

    public void setIS_CHILD_THREE_COMPULSORY(Boolean IS_CHILD_THREE_COMPULSORY) {
        this.IS_CHILD_THREE_COMPULSORY = IS_CHILD_THREE_COMPULSORY;
    }

    public String getCHILD_FOUR_TITLE() {
        return CHILD_FOUR_TITLE;
    }

    public void setCHILD_FOUR_TITLE(String CHILD_FOUR_TITLE) {
        this.CHILD_FOUR_TITLE = CHILD_FOUR_TITLE;
    }

    public Boolean getIS_CHILD_FOUR_VISIBLE() {
        return IS_CHILD_FOUR_VISIBLE;
    }

    public void setIS_CHILD_FOUR_VISIBLE(Boolean IS_CHILD_FOUR_VISIBLE) {
        this.IS_CHILD_FOUR_VISIBLE = IS_CHILD_FOUR_VISIBLE;
    }

    public Boolean getIS_CHILD_FOUR_EDITABLE() {
        return IS_CHILD_FOUR_EDITABLE;
    }

    public void setIS_CHILD_FOUR_EDITABLE(Boolean IS_CHILD_FOUR_EDITABLE) {
        this.IS_CHILD_FOUR_EDITABLE = IS_CHILD_FOUR_EDITABLE;
    }

    public Boolean getIS_CHILD_FOUR_COMPULSORY() {
        return IS_CHILD_FOUR_COMPULSORY;
    }

    public void setIS_CHILD_FOUR_COMPULSORY(Boolean IS_CHILD_FOUR_COMPULSORY) {
        this.IS_CHILD_FOUR_COMPULSORY = IS_CHILD_FOUR_COMPULSORY;
    }
    public String getCHILD_FIVE_TITLE() {
        return CHILD_FIVE_TITLE;
    }

    public void setCHILD_FIVE_TITLE(String CHILD_FIVE_TITLE) {
        this.CHILD_FIVE_TITLE = CHILD_FIVE_TITLE;
    }

    public Boolean getIS_CHILD_FIVE_VISIBLE() {
        return IS_CHILD_FIVE_VISIBLE;
    }

    public void setIS_CHILD_FIVE_VISIBLE(Boolean IS_CHILD_FIVE_VISIBLE) {
        this.IS_CHILD_FIVE_VISIBLE = IS_CHILD_FIVE_VISIBLE;
    }

    public Boolean getIS_CHILD_FIVE_EDITABLE() {
        return IS_CHILD_FIVE_EDITABLE;
    }

    public void setIS_CHILD_FIVE_EDITABLE(Boolean IS_CHILD_FIVE_EDITABLE) {
        this.IS_CHILD_FIVE_EDITABLE = IS_CHILD_FIVE_EDITABLE;
    }

    public Boolean getIS_CHILD_FIVE_COMPULSORY() {
        return IS_CHILD_FIVE_COMPULSORY;
    }

    public void setIS_CHILD_FIVE_COMPULSORY(Boolean IS_CHILD_FIVE_COMPULSORY) {
        this.IS_CHILD_FIVE_COMPULSORY = IS_CHILD_FIVE_COMPULSORY;
    }

    public String getCHILD_SIX_TITLE() {
        return CHILD_SIX_TITLE;
    }

    public void setCHILD_SIX_TITLE(String CHILD_SIX_TITLE) {
        this.CHILD_SIX_TITLE = CHILD_SIX_TITLE;
    }

    public Boolean getIS_CHILD_SIX_VISIBLE() {
        return IS_CHILD_SIX_VISIBLE;
    }

    public void setIS_CHILD_SIX_VISIBLE(Boolean IS_CHILD_SIX_VISIBLE) {
        this.IS_CHILD_SIX_VISIBLE = IS_CHILD_SIX_VISIBLE;
    }

    public Boolean getIS_CHILD_SIX_EDITABLE() {
        return IS_CHILD_SIX_EDITABLE;
    }

    public void setIS_CHILD_SIX_EDITABLE(Boolean IS_CHILD_SIX_EDITABLE) {
        this.IS_CHILD_SIX_EDITABLE = IS_CHILD_SIX_EDITABLE;
    }

    public Boolean getIS_CHILD_SIX_COMPULSORY() {
        return IS_CHILD_SIX_COMPULSORY;
    }

    public void setIS_CHILD_SIX_COMPULSORY(Boolean IS_CHILD_SIX_COMPULSORY) {
        this.IS_CHILD_SIX_COMPULSORY = IS_CHILD_SIX_COMPULSORY;
    }

    public String getCHILD_SEVEN_TITLE() {
        return CHILD_SEVEN_TITLE;
    }

    public void setCHILD_SEVEN_TITLE(String CHILD_SEVEN_TITLE) {
        this.CHILD_SEVEN_TITLE = CHILD_SEVEN_TITLE;
    }

    public Boolean getIS_CHILD_SEVEN_VISIBLE() {
        return IS_CHILD_SEVEN_VISIBLE;
    }

    public void setIS_CHILD_SEVEN_VISIBLE(Boolean IS_CHILD_SEVEN_VISIBLE) {
        this.IS_CHILD_SEVEN_VISIBLE = IS_CHILD_SEVEN_VISIBLE;
    }

    public Boolean getIS_CHILD_SEVEN_EDITABLE() {
        return IS_CHILD_SEVEN_EDITABLE;
    }

    public void setIS_CHILD_SEVEN_EDITABLE(Boolean IS_CHILD_SEVEN_EDITABLE) {
        this.IS_CHILD_SEVEN_EDITABLE = IS_CHILD_SEVEN_EDITABLE;
    }

    public Boolean getIS_CHILD_SEVEN_COMPULSORY() {
        return IS_CHILD_SEVEN_COMPULSORY;
    }

    public void setIS_CHILD_SEVEN_COMPULSORY(Boolean IS_CHILD_SEVEN_COMPULSORY) {
        this.IS_CHILD_SEVEN_COMPULSORY = IS_CHILD_SEVEN_COMPULSORY;
    }


}
