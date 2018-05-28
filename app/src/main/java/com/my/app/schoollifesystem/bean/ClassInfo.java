package com.my.app.schoollifesystem.bean;

import java.io.Serializable;

/**
 * Created by yf on 18-5-26.
 */

public class ClassInfo implements Serializable {

    private String id;
    private String classNo;
    private String profession;
    private String className;
    private String classPeople;

    public String getClassPeople() {
        return classPeople;
    }

    public void setClassPeople(String classPeople) {
        this.classPeople = classPeople;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
