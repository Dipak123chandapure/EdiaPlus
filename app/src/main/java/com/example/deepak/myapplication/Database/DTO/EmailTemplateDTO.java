package com.example.deepak.myapplication.Database.DTO;


public class EmailTemplateDTO {
    private Long id;
    private String titile;
    private String subject;
    private String body;
    private Boolean isSystemValue;
    private Boolean isVirtuallyDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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


}
