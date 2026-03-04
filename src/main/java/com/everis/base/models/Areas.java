package com.everis.base.models;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","code","flag","parentAreaId","parentArea","childAreas"})
public class Areas {
    private String id;
    private String name;
    private String code;
    private String flag;
    private String parentAreaId;
    private String parentArea;
    private ChildAreas[] childAreas;


//SOLO POST
    public Areas() {

    }
    public Areas(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(String parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public ChildAreas[] getChildAreas() {
        return childAreas;
    }

    public void setChildAreas(ChildAreas[] childAreas) {
        this.childAreas = childAreas;
    }
}
