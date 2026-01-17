package com.leonardo.repository;

import com.leonardo.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
  List<Cart> findByItemName(String itemName);
}
