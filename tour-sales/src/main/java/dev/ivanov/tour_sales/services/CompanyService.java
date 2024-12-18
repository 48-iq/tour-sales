package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.company.CompanyCreateDto;
import dev.ivanov.tour_sales.dto.company.CompanyDto;
import dev.ivanov.tour_sales.dto.company.CompanyUpdateDto;
import dev.ivanov.tour_sales.entities.Company;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private IdService idService;

    public void createCompany(CompanyCreateDto companyCreateDto) {
        String id = idService.generate();
        companyRepository.createCompany(
                id,
                companyCreateDto.getName(),
                companyCreateDto.getDescription(),
                LocalDateTime.now(),
                companyCreateDto.getEmail());
    }

    public void updateCompanyById(String id, CompanyUpdateDto companyUpdateDto) {
        companyRepository.updateCompanyById(
                id,
                companyUpdateDto.getName(),
                companyUpdateDto.getDescription(),
                companyUpdateDto.getEmail());
    }

    public CompanyDto getCompanyById(String id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Company company = companyRepository.getCompanyById(id).
                orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found"));

        String registeredAt = formatter.format(company.getRegisteredAt());

        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .registeredAt(registeredAt)
                .email(company.getEmail())
                .build();
    }

    public List<CompanyDto> findCompaniesByNameOrEmail(String query) {
        List<Company> companies = companyRepository.findCompanyByNameOrEmail(query);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return companies.stream().map(company -> CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .registeredAt(formatter.format(company.getRegisteredAt()))
                .email(company.getEmail())
                .build()).toList();
    }

    public void deleteCompanyById(String id) {
        companyRepository.deleteCompanyById(id);
    }
}
