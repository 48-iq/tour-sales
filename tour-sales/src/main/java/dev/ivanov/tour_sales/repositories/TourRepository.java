package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {

    @Query(nativeQuery = true, value = "select create_tour(:id, :title, :description, :price, :startAt, :finishAt, :availableCount, :companyId)")
    void createTour(String id, String title, String description, Double price, LocalDate startAt, LocalDate finishAt, int availableCount, String companyId);

    @Query(nativeQuery = true, value = "select delete_tour_by_id(:id)")
    void deleteTourById(String id);

    @Query(nativeQuery = true, value = "select * from update_tour_by_id(:id, :title, :description, :price, :startAt, :finishAt, :availableCount)")
    void updateTour(String id, String title, String description, Double price, LocalDate startAt, LocalDate finishAt, Integer availableCount);

    @Query(nativeQuery = true, value = "select * from minus_tour_available_count(:id)")
    void minusTourAvailableCount(String id);

    @Query(nativeQuery = true, value = "select * from get_tour_by_id(:id)")
    Optional<Tour> getTourById(String id);

    @Query(nativeQuery = true, value = "select * from get_tours_by_title(:title)")
    List<Tour> getToursByTitle(String title);

    @Query(nativeQuery = true, value = "select * from get_tours_by_title_and_company_id(:title, :companyId)")
    List<Tour> getToursByTitleAndCompanyId(String title, String companyId);

    @Query(nativeQuery = true, value = "select * from get_tours_by_company(:companyId)")
    List<Tour> getToursByCompanyId(String companyId);

    @Query(nativeQuery = true, value = "select * from add_city_to_tour(:city, :tourId)")
    void addCityToTour(String city, String tourId);

    @Query(nativeQuery = true, value = "select * from remove_city_from_tour(:city, :tourId)")
    void removeCityFromTour(String city, String tourId);

    @Query(nativeQuery = true, value = "select * from get_all_tours()")
    List<Tour> getAllTours();
}
