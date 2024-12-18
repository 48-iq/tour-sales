package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.user.UserDto;
import dev.ivanov.tour_sales.dto.user.UserUpdateDto;
import dev.ivanov.tour_sales.entities.User;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void updateUser(String id, UserUpdateDto userUpdateDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedBirthDate = LocalDate.parse(userUpdateDto.getBirthDate(), formatter);
        userRepository.updateUser(id,
                userUpdateDto.getName(),
                userUpdateDto.getSurname(),
                userUpdateDto.getEmail(),
                parsedBirthDate,
                userUpdateDto.getPhone()
                );
    }

    public UserDto getUserById(String id) {
        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .birthDate(user.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .phone(user.getPhone())
                .build();
    }

    public List<UserDto> getUsersByTour(String tourId) {
        return userRepository.getUsersByTour(tourId)
                .stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .email(user.getEmail())
                        .birthDate(user.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .phone(user.getPhone())
                        .build())
                .toList();
    }

    public void deleteUserById(String id) {
        userRepository.deleteUserById(id);
    }
}
