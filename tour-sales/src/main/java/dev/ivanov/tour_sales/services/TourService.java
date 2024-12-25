package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.city.CityDto;
import dev.ivanov.tour_sales.dto.tour.TourCreateDto;
import dev.ivanov.tour_sales.dto.tour.TourDto;
import dev.ivanov.tour_sales.dto.tour.TourUpdateDto;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.CityRepository;
import dev.ivanov.tour_sales.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private IdService idService;

    @Autowired
    private CityRepository cityRepository;

    public TourDto getTourById(String id) {
        TourDto tourDto = tourRepository.getTourById(id)
                .map(TourDto::from)
                .orElseThrow(() -> new EntityNotFoundException("Tour with id " + id + " not found"));
        tourDto.setCities(cityRepository.getCitiesByTour(id).stream()
                .map(CityDto::from).toList());

        return tourDto;
    }

    public List<TourDto> getAllTours() {
        return tourRepository.getAllTours().stream().map(TourDto::from).toList();
    }

    public void addCityToTour(String tourId, String cityId) {
        tourRepository.addCityToTour(cityId, tourId);
    }

    public void removeCityFromTour(String tourId, String cityId) {
        tourRepository.removeCityFromTour(cityId, tourId);
    }

    public void createTour(TourCreateDto tourCreateDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
                LocalDate.parse(tourUpdateDto.getStartAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(tourUpdateDto.getFinishAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                tourUpdateDto.getAvailableCount());
    }

    public void deleteTourById(String id) {
        tourRepository.deleteTourById(id);
    }

    public List<TourDto> getToursByCompanyId(String companyId) {
        return tourRepository.getToursByCompanyId(companyId)
                .stream()
                .map(TourDto::from)
                .peek(tour -> tour.setCities(cityRepository
                        .getCitiesByTour(tour.getId()).stream().map(CityDto::from).toList()))
                .toList();
    }

    public List<TourDto> getToursByTitleAndCity(String city, String title) {
        if (city == null || city.isEmpty() || city.equals("all"))
        {

            return tourRepository.getToursByTitle(title)
                    .stream()
                    .map(TourDto::from)
                    .peek(tour -> tour.setCities(cityRepository
                            .getCitiesByTour(tour.getId()).stream().map(CityDto::from).toList()))
                    .toList();
        }

        if (title == null || title.isEmpty()) {
            return tourRepository.getToursByCity(city)
                    .stream()
                    .map(TourDto::from)
                    .peek(tour -> tour.setCities(cityRepository
                            .getCitiesByTour(tour.getId()).stream().map(CityDto::from).toList()))
                    .toList();
        }


        return tourRepository.getToursByTitleAndCity(title, city)
                .stream()
                .map(TourDto::from)
                .peek(tour -> tour.setCities(cityRepository
                        .getCitiesByTour(tour.getId()).stream().map(CityDto::from).toList()))
                .toList();
    }



}
