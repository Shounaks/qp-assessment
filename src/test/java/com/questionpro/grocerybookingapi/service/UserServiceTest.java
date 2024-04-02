package com.questionpro.grocerybookingapi.service;

import com.questionpro.grocerybookingapi.model.Grocery;
import com.questionpro.grocerybookingapi.repository.GroceryRepository;
import com.questionpro.grocerybookingapi.repository.UserGroceryItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    GroceryRepository groceryRepository;

    @Mock
    UserGroceryItemRepository userGroceryItemRepository;

    UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(groceryRepository, userGroceryItemRepository);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 23, 54, 123})
    void getAllItems_valid(int pageNumbers) {
        int maxItems = 200;
        List<Grocery> groceries = IntStream.range(0, maxItems).mapToObj(id -> new Grocery((long) id, "DUMMY", 123.00, 250L)).toList();
        PageRequest pageRequest = PageRequest.of(pageNumbers, 15);

        // Given
        Mockito.when(groceryRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(groceries, pageRequest, maxItems));

        // When
        Page<Grocery> allItems = userService.getAllItems(pageNumbers);

        // Then
        assertThat(allItems.get().count()).isEqualTo(200L);

    }
}