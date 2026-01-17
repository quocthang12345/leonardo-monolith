package com.leonardo.convert;

import com.leonardo.entity.CommonFashion;
import com.leonardo.entity.TypeColor;
import com.leonardo.entity.dto.CommonFashionDTO;
import com.leonardo.repository.FashionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonFashionConvert {
  private final ModelMapper modelMapper;
  private final FashionRepository fashionRepo;

  public CommonFashionDTO toDTO(CommonFashion commonFashion) {
      return modelMapper.map(commonFashion, CommonFashionDTO.class);
  }

  public CommonFashion toDocs(CommonFashionDTO commonFashionDto) {
    if (commonFashionDto.get_id() == null) {
        return modelMapper.map(commonFashionDto, CommonFashion.class);
    }
    CommonFashion commonFashionForUpdate =
        fashionRepo.findById(commonFashionDto.get_id()).get();
    commonFashionForUpdate.setAnotherName(commonFashionDto.getAnotherName());
    commonFashionForUpdate.setImgContinue(commonFashionDto.getImgContinue());
    commonFashionForUpdate.setImgDisplay(commonFashionDto.getImgDisplay());
    commonFashionForUpdate.setListImg(commonFashionDto.getListImg());
    commonFashionForUpdate.setModel(commonFashionDto.getModel());
    commonFashionForUpdate.setType(commonFashionDto.getType());
    commonFashionForUpdate.setName(commonFashionDto.getName());
    commonFashionForUpdate.setPrice(commonFashionDto.getPrice());

    List<TypeColor> typeColors = new ArrayList<TypeColor>();
    for (Map<String, String> item : commonFashionDto.getTypeColor()) {
      TypeColor typeColor = new TypeColor();
      typeColor.setColor(item.getOrDefault("color", null));
      typeColor.setImgColor(item.getOrDefault("imgColor", null));
      typeColors.add(typeColor);
    }
    commonFashionForUpdate.setTypeColor(typeColors);

    return commonFashionForUpdate;
  }
}
