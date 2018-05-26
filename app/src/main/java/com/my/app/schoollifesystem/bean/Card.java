package com.my.app.schoollifesystem.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yf on 18-5-26.
 */

public class Card implements Serializable {

    private String id;
    private String num;
    private String status;
    private String lostDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLostDate() {
        return lostDate;
    }

    public void setLostDate(String lostDate) {
        this.lostDate = lostDate;
    }
}
