package dev.ivanov.tour_sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTourDto {
    private String title;
    private String description;
    private String startAt;
    private String finishAt;
    private Double price;
    private Integer availableCount;
    private String companyId;
}
