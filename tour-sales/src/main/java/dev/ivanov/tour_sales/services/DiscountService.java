package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.discount.DiscountCreateDto;
import dev.ivanov.tour_sales.dto.discount.DiscountDto;
import dev.ivanov.tour_sales.entities.Discount;
import dev.ivanov.tour_sales.entities.DiscountView;
import dev.ivanov.tour_sales.entities.User;
import dev.ivanov.tour_sales.entities.UserCategory;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.DiscountRepository;
import dev.ivanov.tour_sales.repositories.TourRepository;
import dev.ivanov.tour_sales.repositories.UserCategoryRepository;
import dev.ivanov.tour_sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public void createDiscount(DiscountCreateDto discountCreateDto) {
        discountRepository.createDiscount(discountCreateDto.getCategoryName(),
                discountCreateDto.getTourId(),
                discountCreateDto.getDiscount());
    }

    public void deleteDiscount(String categoryName, String tourId) {
        discountRepository.deleteDiscount(categoryName, tourId);
    }

    public double getPriceWithDiscount(String tourId, String userId) {
        List<DiscountView> discountViews = discountRepository.getDiscountsByTour(tourId);
        List<UserCategory> userCategories = userCategoryRepository.getCategoriesByUserId(userId);
        Double price = tourRepository.getTourById(tourId)
                .orElseThrow(() -> new EntityNotFoundException("Tour not found")).getPrice();
        Double maxDiscount = 0.0;
        for (DiscountView discountView : discountViews) {
            if (discountView.getDiscount() > maxDiscount
                    && userCategories.stream().anyMatch(uc -> uc.getTitle().equals(discountView.getCategoryName()))) {
                maxDiscount = discountView.getDiscount();
            }
        }

        return price * (1 - maxDiscount / 100);

    }

    public List<DiscountView> getAllDiscounts() {
        return discountRepository.getAllDiscounts();

    }

    public List<DiscountView> getDiscountsByTour(String tourId) {

        return discountRepository.getDiscountsByTour(tourId);

    }
}
