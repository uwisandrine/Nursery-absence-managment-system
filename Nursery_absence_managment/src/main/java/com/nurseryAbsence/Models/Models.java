package com.nurseryAbsence.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Models {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
    private int stid;
    private String fname;
	private String lname;
	private String cname;
	private String level;
	private String date;
	private String atendance;
	public int getId() {
		return id;
		
	}
	public void setId(int id) {
		this.id = id;
		
	}
	
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAtendance() {
		return atendance;
	}
	public void setAtendance(String atendance) {
		this.atendance = atendance;
	}
	
}
