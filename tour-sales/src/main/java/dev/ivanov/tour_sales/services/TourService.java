package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.tour.TourCreateDto;
import dev.ivanov.tour_sales.dto.tour.TourDto;
import dev.ivanov.tour_sales.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private IdService idService;

    public void createTour(TourCreateDto tourCreateDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String id = idService.generate();

        tourRepository.createTour(
                id,
                tourCreateDto.getTitle(),
                tourCreateDto.getDescription(),
                LocalDateTime.now(),
                tourCreateDto.getPrice(),
                LocalDateTime.parse(tourCreateDto.getStartAt(), formatter),
                LocalDateTime.parse(tourCreateDto.getFinishAt(), formatter),
                tourCreateDto.getAvailableCount(),
                tourCreateDto.getCompanyId());
    }

    public void deleteTourById(String id) {
        tourRepository.deleteTourById(id);
    }

    public List<TourDto> getToursByCompanyId(String companyId) {
        return tourRepository.getToursByCompanyId(companyId)
                .stream()
                .map(tour -> TourDto.builder()
                        .id(tour.getId())
                        .title(tour.getTitle())
                        .description(tour.getDescription())
                        .createdAt(tour.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .startAt(tour.getStartAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .finishAt(tour.getFinishAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .price(tour.getPrice())
                        .availableCount(tour.getAvailableCount())
                        .companyId(tour.getCompanyId())
                        .build())
                .toList();
    }

    public List<>
}
