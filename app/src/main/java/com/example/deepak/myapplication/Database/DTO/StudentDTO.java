package com.example.deepak.myapplication.Database.DTO;
import android.os.Parcel;
import android.os.Parcelable;

public class StudentDTO implements Parcelable {


    private int id;

    private String form1Entity1;
    private String form1Entity2;
    private String form1Entity3;
    private String form1Entity4;
    private String form1Entity5;

    private int form2Entity1ID;
    private int form2Entity2ID;
    private int form2Entity3ID;
    private int form2Entity4ID;
    private String form2Entity1;
    private String form2Entity2;
    private String form2Entity3;
    private String form2Entity4;
    private String form2Entity5;
    private String form2Entity6;
    private String form2Entity7;

    private int form3Entity1ID;
    private int form3Entity2ID;
    private int form3Entity3ID;
    private int form3Entity4ID;
    private String form3Entity1;
    private String form3Entity2;
    private String form3Entity3;
    private String form3Entity4;
    private String form3Entity5;
    private String form3Entity6;
    private String form3Entity7;

    private int form4Entity1ID;
    private int form4Entity2ID;
    private int form4Entity3ID;
    private int form4Entity4ID;
    private String form4Entity1;
    private String form4Entity2;
    private String form4Entity3;
    private String form4Entity4;
    private String form4Entity5;
    private String form4Entity6;


    private String form4Entity7;


    private Long createdOnMilli;
    private Long updatedONMilli;
    private String createdOn;
    private String updatedON;



    private String studentDataJSON;
    private String sendDataJSON;
    private String syncStatus;

    private Boolean isCkecked = false;

    public int describeContents() {
        return 0;
    }

    public StudentDTO() {

    }

    protected StudentDTO(Parcel in) {
        this.id = in.readInt();

        this.form1Entity1 = in.readString();
        this.form1Entity2 = in.readString();
        this.form1Entity3 = in.readString();
        this.form1Entity4 = in.readString();
        this.form1Entity5 = in.readString();

        this.form2Entity1ID = in.readInt();
        this.form2Entity2ID = in.readInt();
        this.form2Entity3ID = in.readInt();
        this.form2Entity4ID = in.readInt();
        this.form2Entity1 = in.readString();
        this.form2Entity2 = in.readString();
        this.form2Entity3 = in.readString();
        this.form2Entity4 = in.readString();
        this.form2Entity5 = in.readString();
        this.form2Entity6 = in.readString();
        this.form2Entity7 = in.readString();

        this.form3Entity1ID = in.readInt();
        this.form3Entity2ID = in.readInt();
        this.form3Entity3ID = in.readInt();
        this.form3Entity4ID = in.readInt();
        this.form3Entity1 = in.readString();
        this.form3Entity2 = in.readString();
        this.form3Entity3 = in.readString();
        this.form3Entity4 = in.readString();
        this.form3Entity5 = in.readString();
        this.form3Entity6 = in.readString();
        this.form3Entity7 = in.readString();

        this.form4Entity1ID = in.readInt();
        this.form4Entity2ID = in.readInt();
        this.form4Entity3ID = in.readInt();
        this.form4Entity4ID = in.readInt();
        this.form4Entity1 = in.readString();
        this.form4Entity2 = in.readString();
        this.form4Entity3 = in.readString();
        this.form4Entity4 = in.readString();
        this.form4Entity5 = in.readString();
        this.form4Entity6 = in.readString();
        this.form4Entity7 = in.readString();

        this.createdOnMilli = in.readLong();
        this.updatedONMilli = in.readLong();
        this.createdOn = in.readString();
        this.updatedON = in.readString();

        this.studentDataJSON = in.readString();
        this.sendDataJSON = in.readString();
        this.syncStatus = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);

        dest.writeString(this.form1Entity1);
        dest.writeString(this.form1Entity2);
        dest.writeString(this.form1Entity3);
        dest.writeString(this.form1Entity4);
        dest.writeString(this.form1Entity5);

        dest.writeInt(this.form2Entity1ID);
        dest.writeInt(this.form2Entity2ID);
        dest.writeInt(this.form2Entity3ID);
        dest.writeInt(this.form2Entity4ID);
        dest.writeString(this.form2Entity1);
        dest.writeString(this.form2Entity2);
        dest.writeString(this.form2Entity3);
        dest.writeString(this.form2Entity4);
        dest.writeString(this.form2Entity5);
        dest.writeString(this.form2Entity6);
        dest.writeString(this.form2Entity7);

        dest.writeInt(this.form3Entity1ID);
        dest.writeInt(this.form3Entity2ID);
        dest.writeInt(this.form3Entity3ID);
        dest.writeInt(this.form3Entity4ID);
        dest.writeString(this.form3Entity1);
        dest.writeString(this.form3Entity2);
        dest.writeString(this.form3Entity3);
        dest.writeString(this.form3Entity4);
        dest.writeString(this.form3Entity5);
        dest.writeString(this.form3Entity6);
        dest.writeString(this.form3Entity7);

        dest.writeInt(this.form4Entity1ID);
        dest.writeInt(this.form4Entity2ID);
        dest.writeInt(this.form4Entity3ID);
        dest.writeInt(this.form4Entity4ID);
        dest.writeString(this.form4Entity1);
        dest.writeString(this.form4Entity2);
        dest.writeString(this.form4Entity3);
        dest.writeString(this.form4Entity4);
        dest.writeString(this.form4Entity5);
        dest.writeString(this.form4Entity6);
        dest.writeString(this.form4Entity7);

        dest.writeLong(this.createdOnMilli);
        dest.writeLong(this.updatedONMilli);
        dest.writeString(this.createdOn);
        dest.writeString(this.updatedON);

        dest.writeString(this.studentDataJSON);
        dest.writeString(this.sendDataJSON);
        dest.writeString(this.syncStatus);
    }

    public static final Creator<StudentDTO> CREATOR = new Creator<StudentDTO>() {

        public StudentDTO createFromParcel(Parcel in) {
            return new StudentDTO(in);
        }

        public StudentDTO[] newArray(int size) {
            return new StudentDTO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForm1Entity1() {
        return form1Entity1;
    }

    public void setForm1Entity1(String form1Entity1) {
        this.form1Entity1 = form1Entity1;
    }

    public String getForm1Entity2() {
        return form1Entity2;
    }

    public void setForm1Entity2(String form1Entity2) {
        this.form1Entity2 = form1Entity2;
    }

    public String getForm1Entity3() {
        return form1Entity3;
    }

    public void setForm1Entity3(String form1Entity3) {
        this.form1Entity3 = form1Entity3;
    }

    public String getForm1Entity4() {
        return form1Entity4;
    }

    public void setForm1Entity4(String form1Entity4) {
        this.form1Entity4 = form1Entity4;
    }

    public int getForm2Entity1ID() {
        return form2Entity1ID;
    }

    public void setForm2Entity1ID(int form2Entity1ID) {
        this.form2Entity1ID = form2Entity1ID;
    }

    public int getForm2Entity2ID() {
        return form2Entity2ID;
    }

    public void setForm2Entity2ID(int form2Entity2ID) {
        this.form2Entity2ID = form2Entity2ID;
    }

    public int getForm2Entity3ID() {
        return form2Entity3ID;
    }

    public void setForm2Entity3ID(int form2Entity3ID) {
        this.form2Entity3ID = form2Entity3ID;
    }

    public int getForm2Entity4ID() {
        return form2Entity4ID;
    }

    public void setForm2Entity4ID(int form2Entity4ID) {
        this.form2Entity4ID = form2Entity4ID;
    }

    public String getForm2Entity5() {
        return form2Entity5;
    }

    public void setForm2Entity5(String form2Entity5) {
        this.form2Entity5 = form2Entity5;
    }

    public String getForm2Entity6() {
        return form2Entity6;
    }

    public void setForm2Entity6(String form2Entity6) {
        this.form2Entity6 = form2Entity6;
    }

    public String getForm2Entity7() {
        return form2Entity7;
    }

    public void setForm2Entity7(String form2Entity7) {
        this.form2Entity7 = form2Entity7;
    }

    public int getForm3Entity1ID() {
        return form3Entity1ID;
    }

    public void setForm3Entity1ID(int form3Entity1ID) {
        this.form3Entity1ID = form3Entity1ID;
    }

    public int getForm3Entity2ID() {
        return form3Entity2ID;
    }

    public void setForm3Entity2ID(int form3Entity2ID) {
        this.form3Entity2ID = form3Entity2ID;
    }

    public int getForm3Entity3ID() {
        return form3Entity3ID;
    }

    public void setForm3Entity3ID(int form3Entity3ID) {
        this.form3Entity3ID = form3Entity3ID;
    }

    public int getForm3Entity4ID() {
        return form3Entity4ID;
    }

    public void setForm3Entity4ID(int form3Entity4ID) {
        this.form3Entity4ID = form3Entity4ID;
    }

    public String getForm3Entity5() {
        return form3Entity5;
    }

    public void setForm3Entity5(String form3Entity5) {
        this.form3Entity5 = form3Entity5;
    }

    public String getForm3Entity6() {
        return form3Entity6;
    }

    public void setForm3Entity6(String form3Entity6) {
        this.form3Entity6 = form3Entity6;
    }

    public String getForm3Entity7() {
        return form3Entity7;
    }

    public void setForm3Entity7(String form3Entity7) {
        this.form3Entity7 = form3Entity7;
    }

    public int getForm4Entity1ID() {
        return form4Entity1ID;
    }

    public void setForm4Entity1ID(int form4Entity1ID) {
        this.form4Entity1ID = form4Entity1ID;
    }

    public int getForm4Entity2ID() {
        return form4Entity2ID;
    }

    public void setForm4Entity2ID(int form4Entity2ID) {
        this.form4Entity2ID = form4Entity2ID;
    }

    public int getForm4Entity3ID() {
        return form4Entity3ID;
    }

    public void setForm4Entity3ID(int form4Entity3ID) {
        this.form4Entity3ID = form4Entity3ID;
    }

    public int getForm4Entity4ID() {
        return form4Entity4ID;
    }

    public void setForm4Entity4ID(int form4Entity4ID) {
        this.form4Entity4ID = form4Entity4ID;
    }

    public String getForm4Entity5() {
        return form4Entity5;
    }

    public void setForm4Entity5(String form4Entity5) {
        this.form4Entity5 = form4Entity5;
    }

    public String getForm4Entity6() {
        return form4Entity6;
    }

    public void setForm4Entity6(String form4Entity6) {
        this.form4Entity6 = form4Entity6;
    }

    public String getForm4Entity7() {
        return form4Entity7;
    }

    public void setForm4Entity7(String form4Entity7) {
        this.form4Entity7 = form4Entity7;
    }

    public Long getCreatedOnMilli() {
        return createdOnMilli;
    }

    public void setCreatedOnMilli(Long createdOnMilli) {
        this.createdOnMilli = createdOnMilli;
    }

    public Long getUpdatedONMilli() {
        return updatedONMilli;
    }

    public void setUpdatedONMilli(Long updatedONMilli) {
        this.updatedONMilli = updatedONMilli;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedON() {
        return updatedON;
    }

    public void setUpdatedON(String updatedON) {
        this.updatedON = updatedON;
    }

    public String getStudentDataJSON() {
        return studentDataJSON;
    }

    public void setStudentDataJSON(String studentDataJSON) {
        this.studentDataJSON = studentDataJSON;
    }

    public String getSendDataJSON() {
        return sendDataJSON;
    }

    public void setSendDataJSON(String sendDataJSON) {
        this.sendDataJSON = sendDataJSON;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }


    public String getForm1Entity5() {
        return form1Entity5;
    }

    public void setForm1Entity5(String form1Entity5) {
        this.form1Entity5 = form1Entity5;
    }

    public Boolean getCkecked() {
        return isCkecked;
    }

    public void setCkecked(Boolean ckecked) {
        isCkecked = ckecked;
    }

    public String getForm2Entity1() {
        return form2Entity1;
    }

    public void setForm2Entity1(String form2Entity1) {
        this.form2Entity1 = form2Entity1;
    }

    public String getForm2Entity2() {
        return form2Entity2;
    }

    public void setForm2Entity2(String form2Entity2) {
        this.form2Entity2 = form2Entity2;
    }

    public String getForm2Entity3() {
        return form2Entity3;
    }

    public void setForm2Entity3(String form2Entity3) {
        this.form2Entity3 = form2Entity3;
    }

    public String getForm2Entity4() {
        return form2Entity4;
    }

    public void setForm2Entity4(String form2Entity4) {
        this.form2Entity4 = form2Entity4;
    }

    public String getForm3Entity1() {
        return form3Entity1;
    }

    public void setForm3Entity1(String form3Entity1) {
        this.form3Entity1 = form3Entity1;
    }

    public String getForm3Entity2() {
        return form3Entity2;
    }

    public void setForm3Entity2(String form3Entity2) {
        this.form3Entity2 = form3Entity2;
    }

    public String getForm3Entity3() {
        return form3Entity3;
    }

    public void setForm3Entity3(String form3Entity3) {
        this.form3Entity3 = form3Entity3;
    }

    public String getForm3Entity4() {
        return form3Entity4;
    }

    public void setForm3Entity4(String form3Entity4) {
        this.form3Entity4 = form3Entity4;
    }

    public String getForm4Entity1() {
        return form4Entity1;
    }

    public void setForm4Entity1(String form4Entity1) {
        this.form4Entity1 = form4Entity1;
    }

    public String getForm4Entity2() {
        return form4Entity2;
    }

    public void setForm4Entity2(String form4Entity2) {
        this.form4Entity2 = form4Entity2;
    }

    public String getForm4Entity3() {
        return form4Entity3;
    }

    public void setForm4Entity3(String form4Entity3) {
        this.form4Entity3 = form4Entity3;
    }

    public String getForm4Entity4() {
        return form4Entity4;
    }

    public void setForm4Entity4(String form4Entity4) {
        this.form4Entity4 = form4Entity4;
    }

}
