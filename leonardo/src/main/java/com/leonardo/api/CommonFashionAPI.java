package com.leonardo.api;

import com.leonardo.entity.CommonFashion;
import com.leonardo.entity.dto.CommonFashionDTO;
import com.leonardo.service.IFashionService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommonFashionAPI {
  private final IFashionService fashionService;

  @GetMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/getCollectionFashion/{model}/{type}"})
  public List<CommonFashion> findValueItemFashion(
      @PathVariable(value = "model", required = true) String model,
      @PathVariable(value = "type", required = true) String type) {
    return fashionService.findByTypeAndModel(type, model);
  }

  @GetMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/getCollectionFashion/{type}"})
  public List<CommonFashion> findListValueFashion(
      @PathVariable(value = "type", required = true) String type,
      @RequestParam(required = false, value = "anotherName") String anotherName) {
    return fashionService.findByTypeAndAnotherName(type, anotherName);
  }

  @GetMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/getCollectionFashion"})
  public List<CommonFashion> findListValueFashion(
      @RequestParam(value = "page", required = false) Integer page) {
    return fashionService.findAllFashion(page);
  }

  @PostMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/postCommonFashion"})
  public ResponseEntity<String> addValueInList(@RequestBody CommonFashionDTO fashions) {
    CommonFashion newFashion = fashionService.addOrUpdateItem(fashions);
    return ((newFashion != null)
        ? new ResponseEntity<String>(HttpStatus.ACCEPTED)
        : new ResponseEntity<String>(HttpStatus.BAD_REQUEST));
  }

  @PostMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/postMultiCommonFashion"})
  public ResponseEntity<String> addMultiValueInList(@RequestBody List<CommonFashionDTO> fashions) {
    Boolean isAddAll = fashionService.addListItem(fashions);
    return (isAddAll
        ? new ResponseEntity<String>(HttpStatus.ACCEPTED)
        : new ResponseEntity<String>(HttpStatus.BAD_REQUEST));
  }

  @PutMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/updateCommonFashion"})
  public ResponseEntity<String> updateValueInList(@RequestBody CommonFashionDTO fashions) {
    CommonFashion newFashion = fashionService.addOrUpdateItem(fashions);
    return ((newFashion != null)
        ? new ResponseEntity<String>(HttpStatus.ACCEPTED)
        : new ResponseEntity<String>(HttpStatus.BAD_REQUEST));
  }

  @DeleteMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/deleteCommonFashion/{anotherName}"})
  public ResponseEntity<String> deleteValueInList(
      @PathVariable(value = "anotherName", required = true) String anotherName) {
    fashionService.deleteByAnotherName(anotherName);
    return new ResponseEntity<String>(HttpStatus.ACCEPTED);
  }
}
