package com.my.app.schoollifesystem.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yf on 18-5-26.
 */

public class Card implements Serializable {

    private String id;
    private String cardNo;
    private String status;
    private String stuNo;
    private String money;
    private String lostDate;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
