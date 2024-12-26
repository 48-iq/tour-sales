package dev.ivanov.tour_sales.dto.discount;

import dev.ivanov.tour_sales.entities.Discount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountDto {
    private String tourId;
    private String categoryName;
    private Double discount;

    public static DiscountDto fromDiscount(Discount discount) {
        return DiscountDto.builder()
                .tourId(discount.getTourId())
                .categoryName(discount.getCategoryName())
                .discount(discount.getDiscount())
                .build();
    }
}
