package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.contract.ContractCreateDto;
import dev.ivanov.tour_sales.dto.contract.ContractDto;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.ContractRepository;
import dev.ivanov.tour_sales.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private IdService idService;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private TourRepository tourRepository;

    public void createContract(ContractCreateDto contractCreateDto) {

        String id = idService.generate();

        double price = discountService.getPriceWithDiscount(
                contractCreateDto.getTourId(),
                contractCreateDto.getUserId());


        contractRepository.createContract(id,
                contractCreateDto.getTourId(),
                contractCreateDto.getUserId(),
                price);

        tourRepository.minusTourAvailableCount(contractCreateDto.getTourId());
    }

    public void deleteContract(String id) {
        contractRepository.deleteContract(id);
    }


    public List<ContractDto> getContractsByUser(String userId) {
        return contractRepository.getContractsByUser(userId)
                .stream().map(contract -> ContractDto.builder()
                        .id(contract.getId())
                        .userId(contract.getUserId())
                        .tourId(contract.getTourId())
                        .price(contract.getPrice())
                        .build()
                ).toList();
    }

    public List<ContractDto> getAllContracts() {
        return contractRepository.getAllContracts()
                .stream().map(contract -> ContractDto.builder()
                        .id(contract.getId())
                        .userId(contract.getUserId())
                        .tourId(contract.getTourId())
                        .price(contract.getPrice())
                        .build()
                ).toList();
    }


}
