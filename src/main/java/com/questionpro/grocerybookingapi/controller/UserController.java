package com.questionpro.grocerybookingapi.controller;

import com.questionpro.grocerybookingapi.model.BookingDto;
import com.questionpro.grocerybookingapi.model.CartItem;
import com.questionpro.grocerybookingapi.security.User;
import com.questionpro.grocerybookingapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/grocery/{pageId}")
    public ResponseEntity<Object> getAllItems(@PathVariable(required = false) Integer pageId) {
        return ResponseEntity.ok(userService.getAllItems(pageId));
    }

    @PostMapping("/grocery/book/")
    public ResponseEntity<Object> bookGrocery(@AuthenticationPrincipal User principal, @RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(userService.bookGrocery(principal, bookingDto.getGroceryIdList()));
    }

    @GetMapping("/grocery/booked/{pageId}")
    public ResponseEntity<List<CartItem>> getAllGroceries(@AuthenticationPrincipal User principal, @PathVariable Integer pageId) {
        return ResponseEntity.ok(userService.getAllGroceries(principal, pageId));
    }
}
