package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.category.UserCategoryDto;
import dev.ivanov.tour_sales.dto.user.UserDto;
import dev.ivanov.tour_sales.dto.user.UserUpdateDto;
import dev.ivanov.tour_sales.entities.User;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.UserCategoryRepository;
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

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public void updateUser(String id, UserUpdateDto userUpdateDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedBirthDate = null;

        parsedBirthDate = LocalDate.parse(userUpdateDto.getBirthDate(), formatter);
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

        UserDto dto = UserDto.fromUser(user);
        dto.setCategories(userCategoryRepository.getCategoriesByUserId(id)
                .stream().map(UserCategoryDto::fromCategory).toList());
        return dto;
    }

    public List<UserDto> getUsersByTour(String tourId) {
        return userRepository.getUsersByTour(tourId)
                .stream()
                .map(UserDto::fromUser)
                .peek(dto -> dto.setCategories(userCategoryRepository.getCategoriesByUserId(dto.getId())
                        .stream().map(UserCategoryDto::fromCategory).toList()))
                .toList();
    }

    public void deleteUserById(String id) {
        userRepository.deleteUserById(id);
    }
}
