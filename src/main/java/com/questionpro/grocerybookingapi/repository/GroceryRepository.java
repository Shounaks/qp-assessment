package com.questionpro.grocerybookingapi.repository;

import com.questionpro.grocerybookingapi.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface  GroceryRepository extends JpaRepository<Grocery, Long> {
    long deleteByIdAllIgnoreCase(Long id);

    @Transactional
    @Modifying
    @Query("update Grocery g set g.name = ?1, g.price = ?2, g.inventory = ?3 where g.id = ?4")
    int updateNameAndPriceAndInventoryById(String name, Double price, Long inventory, Long id);


}
