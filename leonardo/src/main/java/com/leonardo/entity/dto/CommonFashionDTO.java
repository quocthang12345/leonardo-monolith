package com.leonardo.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CommonFashionDTO {
	
	private String _id;
	
	private String imgDisplay;
	
	private String imgContinue;
	
	private String name;
	
	private String anotherName;
	
	private String model;
	
	private String type;
	
	private String price;
	
	private List<Map<String,String>> typeColor = new ArrayList<Map<String,String>>(); 
	
	private List<String> listImg = new ArrayList<String>();
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String createBy;
	
	private String modifyBy;

}
