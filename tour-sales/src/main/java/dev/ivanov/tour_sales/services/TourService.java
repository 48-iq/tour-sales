package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.DateFormats;
import dev.ivanov.tour_sales.dto.city.CityDto;
import dev.ivanov.tour_sales.dto.tour.TourCreateDto;
import dev.ivanov.tour_sales.dto.tour.TourDto;
import dev.ivanov.tour_sales.dto.tour.TourUpdateDto;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private IdService idService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private CompanyService companyService;

    public TourDto getTourById(String id) {
        TourDto tourDto = tourRepository.getTourById(id)
                .map(t -> TourDto.from(t, cityService.getCitiesByTour(t.getId()),
                        companyService.getCompanyById(t.getCompanyId()),
                        discountService.getDiscountsByTour(t.getId())))
                .orElseThrow(() -> new EntityNotFoundException("Tour with id " + id + " not found"));
        return tourDto;
    }

    public List<TourDto> getAllTours() {
        return tourRepository.getAllTours().stream()
                .map(t -> TourDto.from(t, cityService.getCitiesByTour(t.getId()),
                        companyService.getCompanyById(t.getCompanyId()),
                        discountService.getDiscountsByTour(t.getId())))
                .toList();
    }

    public void addCityToTour(String cityId, String tourId) {
        tourRepository.addCityToTour(cityId, tourId);
    }

    public void removeCityFromTour(String cityId, String tourId) {
        tourRepository.removeCityFromTour(cityId, tourId);
    }

    public void createTour(TourCreateDto tourCreateDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormats.DATE_FORMAT);

        String id = idService.generate();

        tourRepository.createTour(
                id,
                tourCreateDto.getTitle(),
                tourCreateDto.getDescription(),
                tourCreateDto.getPrice(),
                LocalDate.parse(tourCreateDto.getStartAt(), formatter),
                LocalDate.parse(tourCreateDto.getFinishAt(), formatter),
                tourCreateDto.getAvailableCount(),
                tourCreateDto.getCompanyId());
    }

    public void updateTourById(String id, TourUpdateDto tourUpdateDto) {
        tourRepository.updateTour(id,
                tourUpdateDto.getTitle(),
                tourUpdateDto.getDescription(),
                tourUpdateDto.getPrice(),
                LocalDate.parse(tourUpdateDto.getStartAt(), DateTimeFormatter.ofPattern(DateFormats.DATE_FORMAT)),
                LocalDate.parse(tourUpdateDto.getFinishAt(), DateTimeFormatter.ofPattern(DateFormats.DATE_FORMAT)),
                tourUpdateDto.getAvailableCount());
    }

    public void deleteTourById(String id) {
        tourRepository.deleteTourById(id);
    }

    public List<TourDto> getToursByCompanyId(String companyId) {
        return tourRepository.getToursByCompanyId(companyId)
                .stream()
                .map(t -> TourDto.from(t, cityService.getCitiesByTour(t.getId()),
                        companyService.getCompanyById(t.getCompanyId()),
                        discountService.getDiscountsByTour(t.getId())))
                .toList();
    }

    public List<TourDto> getToursByTitle(String title) {
        return tourRepository.getToursByTitle(title)
                .stream()
                .map(t -> TourDto.from(t, cityService.getCitiesByTour(t.getId()),
                        companyService.getCompanyById(t.getCompanyId()),
                        discountService.getDiscountsByTour(t.getId())))
                .toList();
    }

    public List<TourDto> getToursByTitleAndCompanyId(String title, String companyId) {
        return tourRepository.getToursByTitleAndCompanyId(title, companyId)
                .stream()
                .map(t -> TourDto.from(t, cityService.getCitiesByTour(t.getId()),
                        companyService.getCompanyById(t.getCompanyId()),
                        discountService.getDiscountsByTour(t.getId())))
                .toList();
    }





}
