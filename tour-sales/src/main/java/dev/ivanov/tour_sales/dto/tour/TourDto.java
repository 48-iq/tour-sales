package dev.ivanov.tour_sales.dto.tour;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDto {
    private String id;
    private String title;
    private String description;
    private String createdAt;
    private String startAt;
    private String finishAt;
    private Double price;
    private Integer availableCount;
    private String companyId;
}
