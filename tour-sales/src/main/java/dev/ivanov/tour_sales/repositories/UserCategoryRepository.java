package dev.ivanov.tour_sales.repositories;


import dev.ivanov.tour_sales.entities.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, String> {
    @Query(nativeQuery = true, value = "call create_user_category(:title, :description)")
    void createUserCategory(String title, String description);

    @Query(nativeQuery = true, value = "call delete_user_category(:title)")
    void deleteUserCategory(String title);

    @Query(nativeQuery = true, value = "select get_all_user_categories()")
    List<UserCategory> getAllUserCategories();
}
