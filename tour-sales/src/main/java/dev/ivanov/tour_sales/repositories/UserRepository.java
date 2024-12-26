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
    @Query(nativeQuery = true, value = "select * from get_user_by_id(:id)")
    Optional<User> getUserById(String id);

    @Query(nativeQuery = true, value = "select * from create_user(:id, :username, :password, :name, :surname, :email, :birthDate, :phone)")
    void createUser(String id, String username, String password, String name, String surname, String email, LocalDate birthDate, String phone);

    @Query(nativeQuery = true,
            value = "select * from update_user_by_id(:id, :name, :surname, :email, :birthDate, :phone)")
    void updateUser(String id, String name, String surname, String email, LocalDate birthDate, String phone);

    @Query(nativeQuery = true, value = "select * from delete_user_by_id(:id)")
    void deleteUserById(String id);

    @Query(nativeQuery = true, value = "select * from get_users_by_tour(:tourId)")
    List<User> getUsersByTour(String tourId);

    @Query(nativeQuery = true, value = "select * from get_user_by_username(:username)")
    Optional<User> getUserByUsername(String username);

    @Query(nativeQuery = true, value = "select * from get_all_users()")
    List<User> getAllUsers();
}
