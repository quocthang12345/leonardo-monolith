package com.leonardo.service.impl;

import com.leonardo.convert.CommonFashionConvert;
import com.leonardo.entity.CommonFashion;
import com.leonardo.entity.dto.CommonFashionDTO;
import com.leonardo.repository.FashionRepository;
import com.leonardo.service.IFashionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FashionService implements IFashionService {
	private final FashionRepository fashionRepo;
	private final CommonFashionConvert fashionConvert;

	@Override
	public List<CommonFashion> findAllFashion(Integer page) {
		return Optional.ofNullable(page).map(item -> fashionRepo.findAll(PageRequest.of(item, 8, Sort.Direction.DESC, "_id")).getContent()).orElse(fashionRepo.findAll());
	}

	@Override
	public CommonFashion addOrUpdateItem(CommonFashionDTO fashionDto) {
		CommonFashion newFashion = fashionConvert.toDocs(fashionDto);
		return Optional.ofNullable(newFashion).map(item -> fashionRepo.save(item)).orElse(null);
	}


	@Override
	public List<CommonFashion> findByTypeAndModel(String type, String model) {
		return Optional.ofNullable(fashionRepo.findByTypeAndModel(type, model)).orElse(null);
	}

	@Override
	@Transactional
	public Boolean deleteById(String[] ids) {
		return Optional.ofNullable(ids).map(listId -> {  
			for(String idDelete : listId) {
				fashionRepo.deleteById(idDelete);
			}
			return true;
		}).orElse(false);
	}

	@Override
	public List<CommonFashion> findByTypeAndAnotherName(String type, String anotherName) {
		return (anotherName != null ? (fashionRepo.findByTypeAndAnotherName(type, anotherName)):(Optional.ofNullable(type).map(value -> fashionRepo.findByType(value)).orElse(null)));
	}

	@Override
	@Transactional
	public void deleteByAnotherName(String anotherName) {
		fashionRepo.deleteByAnotherName(anotherName);
	}

	@Override
	@Transactional
	public Boolean addListItem(List<CommonFashionDTO> listFashionsDto) {
		List<CommonFashion> listResult = new ArrayList<CommonFashion>();
		for(CommonFashionDTO fashionConverter : listFashionsDto) {
			listResult.add(fashionConvert.toDocs(fashionConverter));
		}
        Optional.of(listResult).map(item -> {
            fashionRepo.saveAll(item);
            return true;
        });
        return true;
	}


}




