package com.example.deepak.myapplication.GroupDashboard;

/**
 * Created by Deepak on 4/23/2017.
 */

public class StudentProfileFormDTO {

    StudentProfileFormDTO(String heading, String subheading){
        this.heading = heading;
        this.subheading = subheading;
    }

    private String heading;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    private String subheading;
}
