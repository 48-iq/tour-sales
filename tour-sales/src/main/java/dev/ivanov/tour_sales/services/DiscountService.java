package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.discount.DiscountCreateDto;
import dev.ivanov.tour_sales.dto.discount.DiscountDto;
import dev.ivanov.tour_sales.entities.Discount;
import dev.ivanov.tour_sales.entities.DiscountView;
import dev.ivanov.tour_sales.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public void createDiscount(DiscountCreateDto discountCreateDto) {
        discountRepository.createDiscount(discountCreateDto.getCategoryName(),
                discountCreateDto.getTourId(),
                discountCreateDto.getDiscount());
    }

    public void deleteDiscount(String categoryName, String tourId) {
        discountRepository.deleteDiscount(categoryName, tourId);
    }

    public double getPriceWithDiscount(String tourId, String userId) {
        return discountRepository.getPriceWithDiscount(tourId, userId);
    }

    public List<DiscountView> getAllDiscounts() {
        return discountRepository.getAllDiscounts();

    }

    public List<DiscountView> getDiscountsByTour(String tourId) {

        return discountRepository.getDiscountsByTour(tourId);

    }
}
