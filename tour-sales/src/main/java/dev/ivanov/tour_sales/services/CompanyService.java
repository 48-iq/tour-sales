package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.company.CompanyCreateDto;
import dev.ivanov.tour_sales.dto.company.CompanyDto;
import dev.ivanov.tour_sales.dto.company.CompanyUpdateDto;
import dev.ivanov.tour_sales.entities.Company;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        Company company = companyRepository.getCompanyById(id).
                orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found"));


        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .email(company.getEmail())
                .build();
    }

    public List<CompanyDto> getAllComanies() {
        List<Company> companies = companyRepository.getAllCompanies();

        return companies.stream().map(company -> CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .email(company.getEmail())
                .build()).toList();
    }

    public void deleteCompanyById(String id) {
        companyRepository.deleteCompanyById(id);
    }
}
