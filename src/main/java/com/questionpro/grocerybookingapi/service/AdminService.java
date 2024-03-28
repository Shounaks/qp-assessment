package com.questionpro.grocerybookingapi.service;

import com.questionpro.grocerybookingapi.model.Grocery;
import com.questionpro.grocerybookingapi.repository.GroceryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final GroceryRepository groceryRepository;

    public String addGroceryItem(Grocery grocery) {
        log.info("Adding GroceryItem: " + grocery);
        return groceryRepository.save(grocery).toString();
    }

    public void deleteGrocery(long groceryId) {
        log.info("Deleting GroceryItem: " + groceryId);
        groceryRepository.deleteById(groceryId);
    }

    public int updateGrocery(long groceryId, Grocery grocery) {
        log.info("Updating ItemNumber: " + groceryId + " , with GroceryItem: " + grocery);
        return groceryRepository.updateNameAndPriceAndInventoryById(grocery.getName(), grocery.getPrice(), grocery.getInventory(), groceryId);
    }

    public Page<Grocery> getAllItems(int pageId) {
        log.info("Querying Page Number: " + pageId);
        return groceryRepository.findAll(PageRequest.of(pageId, 15));
    }
}
