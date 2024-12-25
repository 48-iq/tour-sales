package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(nativeQuery = true, value = "select * from get_roles_by_user_id(:userId)")
    List<Role> getRolesByUserId(String userId);

    @Query(nativeQuery = true, value = "select * from set_role_admin_to_user(:id)")
    void setRoleAdminToUser(String id);
}
