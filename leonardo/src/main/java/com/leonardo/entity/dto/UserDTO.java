package com.leonardo.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
	private String username;

	private String fullname;
	
	private String password;
	
	private String phoneNumber;
	
	private String address;
	
	private String city;
	
	private int status;
	
	private List<String> roleId = new ArrayList<String>();
	
	private String email;
	
	private Boolean emailVerified = false;
	
	private String verifyCode;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String createBy;
	
	private String modifyBy;
}
