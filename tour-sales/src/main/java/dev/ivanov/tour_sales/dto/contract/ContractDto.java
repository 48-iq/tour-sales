package dev.ivanov.tour_sales.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {
    private String id;
    private String userId;
    private String tourId;
    private Double price;
    private Boolean wasPaid;
}
