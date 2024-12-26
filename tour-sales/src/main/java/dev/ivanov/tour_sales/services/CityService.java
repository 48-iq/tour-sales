package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.city.CityCreateDto;
import dev.ivanov.tour_sales.dto.city.CityDto;
import dev.ivanov.tour_sales.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {


    @Autowired
    private CityRepository cityRepository;

    public void createCity(CityCreateDto cityCreateDto) {
        cityRepository.createCity(
                cityCreateDto.getName(),
                cityCreateDto.getCountryName()
        );
    }

    public List<CityDto> getAllCities() {
        return cityRepository.getAllCities()
                .stream()
                .map(city -> CityDto.builder()
                        .name(city.getName())
                        .countryName(city.getCountryName())
                        .build())
                .toList();
    }

    public List<CityDto> getCitiesByTour(String tourId) {
        return cityRepository.getCitiesByTour(tourId)
                .stream()
                .map(city -> CityDto.builder()
                        .name(city.getName())
                        .countryName(city.getCountryName())
                        .build())
                .toList();
    }

    public void deleteCity(String name) {
        cityRepository.deleteCity(name);
    }
}
