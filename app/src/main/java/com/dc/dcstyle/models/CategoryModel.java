package com.dc.dcstyle.models;

public class CategoryModel {
    String name;
    String img_Url;

    public CategoryModel(){

    }
    public CategoryModel(String name, String img_Url){
        this.name = name;
        this.img_Url = img_Url;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getImg_Url(){
        return img_Url;
    }
    public void setImg_Url(String img_Url){
        this.img_Url = img_Url;
    }

}
