package com.questionpro.grocerybookingapi.repository;

import com.questionpro.grocerybookingapi.model.CartItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroceryItemRepository  extends JpaRepository<CartItem,Long> {
    List<CartItem> findByUserId(Long userId, Pageable pageable);
}
