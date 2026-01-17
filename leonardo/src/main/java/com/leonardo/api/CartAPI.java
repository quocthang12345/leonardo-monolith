package com.leonardo.api;

import com.leonardo.entity.CommonFashion;
import com.leonardo.service.ICartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartAPI {
  private final ICartService cartService;

  @GetMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/getCarts/{anotherName}", "/getCarts"})
  public List<CommonFashion> findListValue(
      @PathVariable(value = "anotherName", required = false) String anotherName) {
    return cartService.findValueCart(anotherName);
  }

  @PostMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/postCarts/{anotherName}"})
  public ResponseEntity<String> InsertValue(
      @PathVariable(required = true, value = "anotherName") String anotherName) {
    boolean isInsert = cartService.addItemInCart(anotherName);
    return isInsert
        ? new ResponseEntity<String>(HttpStatus.ACCEPTED)
        : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/deleteCarts/{anotherName}", "/deleteCarts"})
  public ResponseEntity<String> DeleteValue(
      @PathVariable(required = true, value = "anotherName") String anotherName) {
    boolean isDeleted = cartService.deleteItemCart(anotherName);
    return isDeleted
        ? new ResponseEntity<String>(HttpStatus.ACCEPTED)
        : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
  }
}
