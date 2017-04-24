package com.example.deepak.myapplication.Database.DTO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deepak on 4/21/2017.
 */

public class ActivityDTO implements Parcelable {

    private String form1Entity1;
    private String form1Entity3;
    private String form1Entity4;
    private int activityParentID;

    private Long createdDate;
    private Long modificationDate;
    private Long nextActionDate;

    private String smartCallDuration;

    private int actvityTypeID;
    private String activityTitle;
    private String activitySubject;
    private String activityComment;
    private String activityBody;
    private String activityAttachmentList;

    private String sendActivityJSON;
    private String syncStatus;
    private String activityDataJSON;
    private int isDone;

    public ActivityDTO(){}
    protected ActivityDTO(Parcel in) {

        this.form1Entity1 = in.readString();
        this.form1Entity3 = in.readString();
        this.form1Entity4 = in.readString();
        this.activityParentID = in.readInt();

        this.createdDate = in.readLong();
        this.modificationDate = in.readLong();
        this.nextActionDate = in.readLong();
        this.smartCallDuration = in.readString();

        this.actvityTypeID = in.readInt();
        this.activityTitle = in.readString();
        this.activitySubject = in.readString();
        this.activityComment = in.readString();
        this.activityBody = in.readString();
        this.activityAttachmentList = in.readString();

        this.activityDataJSON = in.readString();
        this.syncStatus = in.readString();
        this.sendActivityJSON = in.readString();

        this.isDone = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.form1Entity1);
        dest.writeString(this.form1Entity3);
        dest.writeString(this.form1Entity4);
        dest.writeInt(this.activityParentID);

        dest.writeLong(this.createdDate);
        dest.writeLong(this.modificationDate);
        dest.writeLong(this.nextActionDate);
        dest.writeString(this.smartCallDuration);

        dest.writeInt(this.actvityTypeID);
        dest.writeString(this.activityTitle);
        dest.writeString(this.activitySubject);
        dest.writeString(this.activityComment);
        dest.writeString(this.activityBody);
        dest.writeString(this.activityAttachmentList);
        dest.writeString(this.activityDataJSON);
        dest.writeString(this.sendActivityJSON);
        dest.writeString(this.syncStatus);

        dest.writeInt(this.isDone);

    }


    public static final Creator<ActivityDTO> CREATOR = new Creator<ActivityDTO>() {

        public ActivityDTO createFromParcel(Parcel in) {
            return new ActivityDTO(in);
        }

        public ActivityDTO[] newArray(int size) {
            return new ActivityDTO[size];
        }
    };


    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Long modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Long getNextActionDate() {
        return nextActionDate;
    }

    public void setNextActionDate(Long nextActionDate) {
        this.nextActionDate = nextActionDate;
    }

    public int getActvityTypeID() {
        return actvityTypeID;
    }

    public void setActvityTypeID(int actvityTypeID) {
        this.actvityTypeID = actvityTypeID;
    }

    public String getActivityComment() {
        return activityComment;
    }

    public void setActivityComment(String activityComment) {
        this.activityComment = activityComment;
    }

    public String getActivityBody() {
        return activityBody;
    }

    public void setActivityBody(String activityBody) {
        this.activityBody = activityBody;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }


    public String getSmartCallDuration() {
        return smartCallDuration;
    }

    public void setSmartCallDuration(String smartCallDuration) {
        this.smartCallDuration = smartCallDuration;
    }

    public String getActivitySubject() {
        return activitySubject;
    }

    public void setActivitySubject(String activitySubject) {
        this.activitySubject = activitySubject;
    }

    public String getActivityAttachmentList() {
        return activityAttachmentList;
    }

    public void setActivityAttachmentList(String activityAttachmentList) {
        this.activityAttachmentList = activityAttachmentList;
    }

    public String getActivityDataJSON() {
        return activityDataJSON;
    }

    public void setActivityDataJSON(String activityDataJSON) {
        this.activityDataJSON = activityDataJSON;
    }

    public int getActivityParentID() {
        return activityParentID;
    }

    public void setActivityParentID(int activityParentID) {
        this.activityParentID = activityParentID;
    }

    public String getForm1Entity1() {
        return form1Entity1;
    }

    public void setForm1Entity1(String form1Entity1) {
        this.form1Entity1 = form1Entity1;
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

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getSendActivityJSON() {
        return sendActivityJSON;
    }

    public void setSendActivityJSON(String sendActivityJSON) {
        this.sendActivityJSON = sendActivityJSON;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }
}
