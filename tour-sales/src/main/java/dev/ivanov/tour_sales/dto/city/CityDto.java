package dev.ivanov.tour_sales.dto.city;

import dev.ivanov.tour_sales.entities.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {
    private String name;
    private String countryName;

    public static CityDto from(City city) {
        return CityDto.builder()
                .name(city.getName())
                .countryName(city.getCountryName())
                .build();
    }
}
