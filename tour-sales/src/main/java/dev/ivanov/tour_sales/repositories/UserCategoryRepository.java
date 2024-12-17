package dev.ivanov.tour_sales.repositories;


import dev.ivanov.tour_sales.entities.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, String> {
}
