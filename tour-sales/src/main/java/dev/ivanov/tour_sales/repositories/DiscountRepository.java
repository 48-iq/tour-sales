package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Discount;
import dev.ivanov.tour_sales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {
    @Query(nativeQuery = true, value = "select * from create_discount(:categoryName, :tourId, :discount)")
    void createDiscount(String categoryName, String tourId, double discount);

    @Query(nativeQuery = true, value = "select * from delete_discount(:categoryName, :tourId)")
    void deleteDiscount(String categoryName, String tourId);

    @Query(nativeQuery = true, value = "select * from get_price_with_discount(:tourId, :userId)")
    double getPriceWithDiscount(String tourId, String userId);

    @Query(nativeQuery = true, value = "select * from get_discounts_by_tour(:tourId)")
    List<Discount> getDiscountsByTour(String tourId);

}
