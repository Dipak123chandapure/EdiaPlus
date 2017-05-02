package com.example.deepak.myapplication.Database.DTO;


public class SMSTemplateDTO {
    private Long id;
    private String titile;
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
