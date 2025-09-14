package com.product.events;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateEvent {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
