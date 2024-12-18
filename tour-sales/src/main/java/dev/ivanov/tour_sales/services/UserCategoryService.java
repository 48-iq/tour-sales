package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.category.UserCategoryCreateDto;
import dev.ivanov.tour_sales.dto.category.UserCategoryDto;
import dev.ivanov.tour_sales.repositories.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;


    public void createUserCategory(UserCategoryCreateDto userCategoryCreateDto) {
        userCategoryRepository.createUserCategory(
                userCategoryCreateDto.getTitle(),
                userCategoryCreateDto.getDescription()
        );
    }

    public void deleteUserCategory(String title) {
        userCategoryRepository.deleteUserCategory(title);
    }

    public List<UserCategoryDto> getAllUserCategories() {
        return userCategoryRepository.getAllUserCategories()
                .stream()
                .map(userCategory -> UserCategoryDto.builder()
                        .title(userCategory.getTitle())
                        .description(userCategory.getDescription())
                        .build())
                .toList();
    }
}
