package dev.ivanov.tour_sales.dto.user;

import dev.ivanov.tour_sales.dto.category.UserCategoryDto;
import dev.ivanov.tour_sales.entities.User;
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
public class UserDto {
    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String birthDate;
    private String phone;
    private List<UserCategoryDto> categories;

    public static UserDto fromUser(User user) {
        String birthDate = null;
        if (user.getBirthDate() != null)
            birthDate = user.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .birthDate(birthDate)
                .phone(user.getPhone())
                .build();
    }
}
