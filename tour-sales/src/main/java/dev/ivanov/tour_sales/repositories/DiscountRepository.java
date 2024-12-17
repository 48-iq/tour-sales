package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Discount;
import dev.ivanov.tour_sales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {

    @Procedure(name = "find_user_by_id")
    Optional<User> findUserById(@Param("id") String id);

    @Procedure(name = "register_user")
    User registerUser(@Param("id") String id,
                      @Param("username") String username,
                      @Param("password") String password);

    @Procedure(name = "delete_user_by_id")
    void deleteUserById(@Param("id") String id);

    @Procedure(name = "update_user")
    User updateUser(@Param("id") String id,
                    @Param("name") String name,
                    @Param("surname") String surname,
                    @Param("phone") String phone);


}
