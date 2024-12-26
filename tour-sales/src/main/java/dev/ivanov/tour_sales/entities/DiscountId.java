package dev.ivanov.tour_sales.entities;

import jakarta.persistence.IdClass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountId {

    private String categoryName;
    private String tourId;
}
