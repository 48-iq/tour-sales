package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    @Query(nativeQuery = true, value = "select * from create_city(:name, :countryName)")
    void createCity(String name, String countryName);

    @Query(nativeQuery = true, value = "select * from get_all_cities()")
    List<City> getAllCities();

    @Query(nativeQuery = true, value = "select * from delete_city(:name)")
    void deleteCity(String name);
}
