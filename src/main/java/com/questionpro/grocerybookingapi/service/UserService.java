package com.questionpro.grocerybookingapi.service;

import com.questionpro.grocerybookingapi.exception.UserServiceException;
import com.questionpro.grocerybookingapi.model.CartItem;
import com.questionpro.grocerybookingapi.model.Grocery;
import com.questionpro.grocerybookingapi.repository.GroceryRepository;
import com.questionpro.grocerybookingapi.repository.UserGroceryItemRepository;
import com.questionpro.grocerybookingapi.security.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final GroceryRepository groceryRepository;
    private final UserGroceryItemRepository userGroceryItemRepository;

    public Page<Grocery> getAllItems(@PathVariable(required = false) Integer pageId) {
        log.info("UserService.getAllItems: Retrieving Page with Id: "  + pageId);
        return groceryRepository.findAll(PageRequest.of(pageId, 15));
    }

    public ResponseEntity<Object> bookGrocery(User principal,List<Long> groceryIdList) {
        log.info("Booking groceryId: " + groceryIdList + " , For User: " + principal.getId());
        List<Grocery> groceries = groceryRepository.findAllById(groceryIdList).stream().filter(grocery -> grocery.getInventory() >= 1).toList();
        groceries.forEach(grocery ->  grocery.setInventory(grocery.getInventory() - 1));

        //Could have used builder/mapstruct or even cleaner approaches,
        CartItem cartItem = new CartItem();
        cartItem.setGroceries(groceries.stream().map(Grocery::getId).toList());
        cartItem.setUserId(principal.getId());
        cartItem.setSubscriptionDate(LocalDate.now());

        return ResponseEntity.ok(userGroceryItemRepository.save(cartItem));
    }

    public List<CartItem> getAllGroceries(User principal, int pageId) {
        return userGroceryItemRepository.findByUserId(principal.getId(),PageRequest.of(pageId, 15));
    }
}
