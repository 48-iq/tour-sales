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

    @Query(nativeQuery = true, value = "call create_tour(:id, :title, :description, :createdAt, :price, :finishedAt, :availableCount, :companyId)")
    void createTour(String id, String name, String description, LocalDateTime createdAt, Double price, LocalDateTime finishedAt, int availableCount, String companyId);

    @Query(nativeQuery = true, value = "call delete_tour(:id)")
    void deleteTour(String name);

    @Query(nativeQuery = true, value = "call update_tour_by_id(:id, :title, :description, :price, :finishedAt, :availableCount)")
    void updateTour(String id, String name, String description, Double price, LocalDateTime finishedAt, int availableCount);

    @Query(nativeQuery = true, value = "select get_tour_by_id(:id)")
    Optional<Tour> getTourById(String id);

    @Query(nativeQuery = true, value = "select get_tours_by_title(:title)")
    List<Tour> getToursByTitle(String title);

    @Query(nativeQuery = true, value = "select get_tours_by_title_or_description(:query)")
    List<Tour> getToursByTitleOrDescription(String query);

    @Query(nativeQuery = true, value = "select get_tours_by_company(:companyId)")
    List<Tour> getToursByCompanyId(String companyId);


}
