package com.leonardo.api;

import com.leonardo.entity.Menu;
import com.leonardo.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuAPI {
  private final IMenuService menuService;

  @GetMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/getMenu"})
  public Menu findListValue() {
    return menuService.findAll().get(0);
  }
}
