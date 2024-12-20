package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {

    @Query(nativeQuery = true, value = "select create_tour(:id, :title, :description, :createdAt, :price, :startAt, :finishAt, :availableCount, :companyId)")
    void createTour(String id, String title, String description, LocalDateTime createdAt, Double price, LocalDateTime startAt, LocalDateTime finishAt, int availableCount, String companyId);

    @Query(nativeQuery = true, value = "select delete_tour_by_id(:id)")
    void deleteTourById(String id);

    @Query(nativeQuery = true, value = "select * from update_tour_by_id(:id, :title, :description, :price, :startAt :finishedAt, :availableCount)")
    void updateTour(String id, String title, String description, Double price, LocalDateTime startAt, LocalDateTime finishedAt, int availableCount);

    @Query(nativeQuery = true, value = "select * from get_tour_by_id(:id)")
    Optional<Tour> getTourById(String id);

    @Query(nativeQuery = true, value = "select * from get_tours_by_title(:title)")
    List<Tour> getToursByTitle(String title);

    @Query(nativeQuery = true, value = "select * from get_tours_by_title_and_city(:query, :city)")
    List<Tour> getToursByTitleAndCity(String query, String city);

    @Query(nativeQuery = true, value = "select * from get_tours_by_city(:city)")
    List<Tour> getToursByCity(String city);

    @Query(nativeQuery = true, value = "select * from get_tours_by_company(:companyId)")
    List<Tour> getToursByCompanyId(String companyId);


}
