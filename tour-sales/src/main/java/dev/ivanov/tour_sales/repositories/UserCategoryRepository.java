package dev.ivanov.tour_sales.repositories;


import dev.ivanov.tour_sales.entities.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, String> {
    @Query(nativeQuery = true, value = "select * from create_user_category(:title, :description)")
    void createUserCategory(String title, String description);

    @Query(nativeQuery = true, value = "select * from delete_user_category(:title)")
    void deleteUserCategory(String title);

    @Query(nativeQuery = true, value = "select * from get_all_user_categories()")
    List<UserCategory> getAllUserCategories();

    @Query(nativeQuery = true, value = "select * from get_categories_by_user_id(:userId)")
    List<UserCategory> getCategoriesByUserId(String userId);

    @Query(nativeQuery = true, value = "select * from add_user_to_category(:userId, :userCategoryName)")
    void addUserToCategory(String userId, String userCategoryName);

    @Query(nativeQuery = true, value = "select * from remove_user_from_category(:userId, :userCategoryName)")
    void removeUserFromCategory(String userId, String userCategoryName);
}

