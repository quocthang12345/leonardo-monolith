package com.leonardo.service.impl;

import com.leonardo.entity.Cart;
import com.leonardo.entity.CommonFashion;
import com.leonardo.repository.CartRepository;
import com.leonardo.repository.FashionRepository;
import com.leonardo.service.ICartService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
  private final CartRepository cartRepo;
  private final FashionRepository fasionRepo;

  @Override
  public List<CommonFashion> findValueCart(String anotherName) {
    List<CommonFashion> listItem = new ArrayList<CommonFashion>();
    List<Cart> listItemInCart =
        Optional.ofNullable(anotherName)
            .map(value -> cartRepo.findByItemName(anotherName))
            .orElse(cartRepo.findAll());
    for (Cart itemInCart : listItemInCart) {
      listItem.add(fasionRepo.findOneByAnotherName(itemInCart.getItemName()));
    }
    return listItem;
  }

  @Override
  @Transactional
  public boolean addItemInCart(String anotherName) {
    CommonFashion fashion = fasionRepo.findOneByAnotherName(anotherName);
    Cart cart = new Cart(fashion.getAnotherName(), 1, fashion.getType());

    Optional.of(cart)
        .map(
            item -> {
              cartRepo.save(item);
              return true;
            });

    return true;
  }

  @Override
  @Transactional
  public boolean deleteItemCart(String anotherName) {
    List<Cart> cart = cartRepo.findByItemName(anotherName);
    return Optional.ofNullable(cart)
        .map(
            listItem -> {
              cartRepo.deleteAll(listItem);
              return true;
            })
        .orElse(false);
  }
}
