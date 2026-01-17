package com.leonardo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.entity.CommonFashion;

@Repository
public interface FashionRepository extends JpaRepository<CommonFashion, String> {

  List<CommonFashion> findByType(String type);

  List<CommonFashion> findByTypeAndAnotherName(String type, String anotherName);

  List<CommonFashion> findByTypeAndModel(String type, String model);

  CommonFashion findOneByAnotherName(String anotherName);

  void deleteByAnotherName(String anotherName);
}
