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
    private String registeredAt;
    private String email;
}
