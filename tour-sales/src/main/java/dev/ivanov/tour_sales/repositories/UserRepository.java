package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(nativeQuery = true, value = "select get_user_by_id(:id)")
    Optional<User> getUserById(String id);

    @Query(nativeQuery = true, value = "call create_user(:id, :username, :password, :name, :surname, :email, :birthDate, :phone)")
    void createUser(String id, String username, String password, String name, String surname, String email, LocalDate birthDate, String phone);

    @Query(nativeQuery = true,
            value = "call update_user_by_id(:id, :username, :name, :surname, :email, :birthDate, :phone)")
    void updateUser(String id, String username, String name, String surname, String email, LocalDate birthDate, String phone);

    @Query(nativeQuery = true, value = "call delete_user_by_id(:id)")
    void deleteUserById(String id);

    @Query(nativeQuery = true, value = "select get_users_by_tour(:tourId)")
    List<User> getUsersByTour(String tourId);
}
