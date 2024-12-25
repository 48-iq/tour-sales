package dev.ivanov.tour_sales.dto.category;

import dev.ivanov.tour_sales.entities.UserCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCategoryDto {
    private String title;
    private String description;

    public static UserCategoryDto fromCategory(UserCategory category) {
        return UserCategoryDto.builder()
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }
}
