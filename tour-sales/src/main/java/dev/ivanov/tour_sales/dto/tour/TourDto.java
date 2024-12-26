package dev.ivanov.tour_sales.dto.tour;

import dev.ivanov.tour_sales.dto.city.CityDto;
import dev.ivanov.tour_sales.dto.company.CompanyDto;
import dev.ivanov.tour_sales.dto.discount.DiscountDto;
import dev.ivanov.tour_sales.entities.DiscountView;
import dev.ivanov.tour_sales.entities.Tour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDto {
    private String id;
    private String title;
    private String description;
    private String startAt;
    private String finishAt;
    private Double price;
    private Integer availableCount;
    private CompanyDto company;
    private List<CityDto> cities;
    private List<DiscountView> discounts;

    public static TourDto from(Tour tour, List<CityDto> cities, CompanyDto company, List<DiscountView> discounts) {
        return TourDto.builder()
                .id(tour.getId())
                .title(tour.getTitle())
                .description(tour.getDescription())
                .startAt(tour.getStartAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .finishAt(tour.getFinishAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .price(tour.getPrice())
                .company(company)
                .cities(cities)
                .discounts(discounts)
                .availableCount(tour.getAvailableCount())
                .build();
    }
}
