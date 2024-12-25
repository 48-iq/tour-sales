package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query(nativeQuery = true, value = "select * from create_company(:id, :name, :description, :email)")
    void createCompany(String id, String name, String description, String email);

    @Query(nativeQuery = true, value = "select * from delete_company_by_id(:id)")
    void deleteCompanyById(String id);

    @Query(nativeQuery = true, value = "select * from update_company_by_id(:id, :name, :description, :email)")
    void updateCompanyById(String id, String name, String description, String email);

    @Query(nativeQuery = true, value = "select * from get_company_by_id(:id)")
    Optional<Company> getCompanyById(String id);

    @Query(nativeQuery = true, value = "select * from get_all_companies()")
    List<Company> getAllCompanies();
}
