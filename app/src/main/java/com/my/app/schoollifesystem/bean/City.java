package com.my.app.schoollifesystem.bean;

import java.io.Serializable;

public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String no;
	private String city;
	private String remark;

    public City(String city) {
    	this.city = city;
    }
    public City(){}

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("city [id=");
		builder.append(id);
		builder.append(", no=");
		builder.append(no);
		builder.append(", city=");
		builder.append(city);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}
	
}
