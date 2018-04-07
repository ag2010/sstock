package com.xingrongjinfu.system.dictionary.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Dictionary {
    private String dictionaryId;

    private String name;

    private String nameEn;

    private String no;

    private Integer sort;

    private String parentId;

    private String remark;

    private String value;

    private String typeCode;

    private Date addTime;

    private Date updateTime;

    private String status;
    private String objType;
    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    private String objName;
    private String objId;

    private List<Dictionary> children = new ArrayList<Dictionary>();

    public List<Dictionary> getChildren() {
        return children;
    }

    public void setChildren(List<Dictionary> children) {
        this.children = children;
    }

    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionaryId='" + dictionaryId + '\'' +
                ", name='" + name + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", no='" + no + '\'' +
                ", sort=" + sort +
                ", parentId='" + parentId + '\'' +
                ", remark='" + remark + '\'' +
                ", value='" + value + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                ", objType='" + objType + '\'' +
                ", objName='" + objName + '\'' +
                ", objId='" + objId + '\'' +
                ", children=" + children +
                '}';
    }
}