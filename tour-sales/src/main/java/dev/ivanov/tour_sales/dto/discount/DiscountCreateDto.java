package dev.ivanov.tour_sales.dto.discount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountCreateDto {
    private String categoryName;
    private String tourId;
    private Double discount;
}
