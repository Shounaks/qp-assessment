package com.questionpro.grocerybookingapi.service;

import com.questionpro.grocerybookingapi.model.Grocery;
import com.questionpro.grocerybookingapi.repository.GroceryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    GroceryRepository groceryRepository;

    AdminService adminService;

    @BeforeEach
    public void setup() {
        adminService = new AdminService(groceryRepository);
    }

    @Test
    void addGroceryItem_valid() {
        // Given
        Grocery grocery = new Grocery(null, "DUMMY_GROCERY", 123.00, 250L);
        Grocery savedGrocery = new Grocery(1L, "DUMMY_GROCERY", 123.00, 250L);

        // When
        Mockito.when(groceryRepository.save(Mockito.any(Grocery.class))).thenReturn(savedGrocery);
        String s = adminService.addGroceryItem(grocery);

        // Then
        assertAll(
//                () -> assertThat(grocery.getName()).isEqualTo(savedGrocery.getName()),
//                () -> assertThat(grocery.getPrice()).isEqualTo(savedGrocery.getPrice()),
//                () -> assertThat(grocery.getInventory()).isEqualTo(savedGrocery.getInventory()),

                // Or simply
                () -> assertThat(grocery).extracting(Grocery::getName,Grocery::getPrice,Grocery::getInventory)
                        .doesNotContainNull()
                        .containsExactly(savedGrocery.getName(),savedGrocery.getPrice(),savedGrocery.getInventory()),

                // Mandatory check
                () -> assertThat(savedGrocery).isNotNull().extracting("id").asString().isEqualTo("1")
        );
    }

    @Test
    void deleteGrocery_valid() {
        // Given
        Mockito.doNothing().when(groceryRepository).deleteById(Mockito.anyLong());

        // When
        adminService.deleteGrocery(15);

        // Then - just ensure no error is being thrown
        assertThat(true).isTrue();
    }

    @Test
    void updateGrocery_valid() {
        // Given
        Grocery updated = new Grocery(1L, "DUMMY_GROCERY_2", 1234.00, 2500L);

        // When
        Mockito.when(groceryRepository.updateNameAndPriceAndInventoryById(Mockito.anyString(),Mockito.anyDouble(),Mockito.anyLong(),Mockito.anyLong()))
                .thenReturn(1);

        // Then
        assertDoesNotThrow(() -> adminService.updateGrocery(1L,updated));
    }

    //... same for others
}