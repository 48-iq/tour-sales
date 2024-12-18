package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;


    public void createCountry(String name) {
        countryRepository.createCountry(name);
    }

    public List<String> getAllCountries() {
        return countryRepository.getAllCountries()
                .stream().map(country -> country.getName()).toList();
    }

    public void deleteCountry(String name) {
        countryRepository.deleteCountry(name);
    }
}
