package com.leonardo.service.impl;

import com.leonardo.entity.Menu;
import com.leonardo.repository.MenuRepository;
import com.leonardo.service.IMenuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {
  private final MenuRepository menuRepo;

  @Override
  public List<Menu> findAll() {
    return menuRepo.findAll();
  }
}
