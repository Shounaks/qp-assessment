package com.questionpro.grocerybookingapi.controller;

import com.questionpro.grocerybookingapi.model.Grocery;
import com.questionpro.grocerybookingapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/grocery/add")
    public ResponseEntity<String> addGroceryItem(@RequestBody Grocery grocery) {
        return ResponseEntity.ok(adminService.addGroceryItem(grocery));
    }

    @GetMapping("/grocery/{pageId}")
    public ResponseEntity<Object> getAllItems(@PathVariable(required = false) int pageId) {
        return ResponseEntity.ok(adminService.getAllItems(pageId));
    }

    @DeleteMapping("/grocery/{groceryId}")
    public ResponseEntity<Object> deleteGrocery(@PathVariable long groceryId) {
        adminService.deleteGrocery(groceryId);
        return ResponseEntity.ok(groceryId);
    }

    // This method can be used to update grocery as well as manage inventory
    @PutMapping("/grocery/{groceryId}")
    public ResponseEntity<Object> updateGrocery(@PathVariable long groceryId, @RequestBody Grocery grocery) {
        return ResponseEntity.ok(adminService.updateGrocery(groceryId,grocery));
    }
}
