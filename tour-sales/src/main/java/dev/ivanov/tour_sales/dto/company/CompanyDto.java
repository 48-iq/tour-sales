package dev.ivanov.tour_sales.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {

    private String id;
    private String name;
    private String description;
    private String email;


    public static CompanyDto from(dev.ivanov.tour_sales.entities.Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .email(company.getEmail())
                .build();
    }
}
