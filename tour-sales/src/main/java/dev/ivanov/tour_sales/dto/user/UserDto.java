package dev.ivanov.tour_sales.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
