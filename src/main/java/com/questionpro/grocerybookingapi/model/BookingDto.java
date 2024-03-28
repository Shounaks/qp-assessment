package com.questionpro.grocerybookingapi.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDto {
    List<Long> groceryIdList;
}
