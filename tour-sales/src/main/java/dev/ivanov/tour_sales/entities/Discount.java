package dev.ivanov.tour_sales.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "discounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(DiscountId.class)
public class Discount {
    @Id
    private String categoryName;
    @Id
    private String tourId;
    private Double discount;
}

