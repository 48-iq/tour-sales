package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
