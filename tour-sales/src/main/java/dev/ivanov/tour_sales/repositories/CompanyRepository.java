package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query(nativeQuery = true, value = "call create_company(:id, :name, :description, :createdAt, :email)")
    void createCompany(String name, String description, LocalDateTime createdAt, String email);

    @Query(nativeQuery = true, value = "call delete_company(:id)")
    void deleteCompany(String id);

    @Query(nativeQuery = true, value = "call update_company_by_id(:id, :name, :description, :email)")
    void updateCompany(String id, String name, String description, String email);

    @Query(nativeQuery = true, value = "select get_company_by_id(:id)")
    Optional<Company> getCompanyById(String id);

    @Query(nativeQuery = true, value = "select find_company_by_name_or_email(:query)")
    List<Company> findCompanyByNameOrEmail(String query);
}
