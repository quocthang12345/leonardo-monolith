package com.leonardo.service;

import java.util.List;

import com.leonardo.entity.CommonFashion;

public interface ICartService {
	
	List<CommonFashion> findValueCart(String anotherName);

	boolean addItemInCart(String anotherName);

	boolean deleteItemCart(String anotherName);

}
