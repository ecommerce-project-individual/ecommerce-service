package com.example.coffeebe.app.dtos.responses;


import com.example.coffeebe.domain.entities.enums.ProductResourceState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSourceResponse {
  private Long id;
  private ProductResponse product;
  private Integer quantity;
  private Long price;
  private Long totalPrice;
  private ProductResourceState state;
}
