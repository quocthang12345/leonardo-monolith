package com.leonardo.service;

import java.util.List;

import com.leonardo.entity.CommonFashion;
import com.leonardo.entity.dto.CommonFashionDTO;

public interface IFashionService {
	List<CommonFashion> findAllFashion(Integer page);
	CommonFashion addOrUpdateItem(CommonFashionDTO fashionDto);
	List<CommonFashion> findByTypeAndAnotherName(String type, String anotherName);
	List<CommonFashion> findByTypeAndModel(String type, String model);
	Boolean deleteById(String[] ids);
	void deleteByAnotherName(String anotherName);
	Boolean addListItem(List<CommonFashionDTO> listFashions);
}
