package com.google.gwt.sample.stockwatcher.shared.bo;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.google.gwt.user.client.rpc.IsSerializable;



public abstract class BusinessObject implements IsSerializable{
	
private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private Timestamp modDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	 public Timestamp getModDate() {
		return modDate;
	}
	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
	
	public boolean equals(Object obj) {
	    	if (obj != null && obj instanceof BusinessObject) {
				BusinessObject bo = (BusinessObject) obj;
				
				try {
					
					if (bo.getId() == this.id) {
						return true;
					}
				} catch (IllegalArgumentException e) {
					
					return false;
					}
				}
			
			return false;
	    }
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	


}
