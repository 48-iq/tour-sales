package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
