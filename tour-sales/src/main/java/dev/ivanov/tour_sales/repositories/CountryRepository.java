package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    @Query(nativeQuery = true, value = "select * from create_country(:name)")
    void createCountry(String name);

    @Query(nativeQuery = true, value = "select * from get_all_countries()")
    List<Country> getAllCountries();

    @Query(nativeQuery = true, value = "select * from delete_country(:name)")
    void deleteCountry(String name);
}
