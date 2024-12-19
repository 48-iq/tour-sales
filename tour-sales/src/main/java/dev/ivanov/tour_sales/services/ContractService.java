package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.contract.ContractCreateDto;
import dev.ivanov.tour_sales.dto.contract.ContractDto;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.ContractRepository;
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

    public void createContract(ContractCreateDto contractCreateDto) {

        String id = idService.generate();

        double price = discountService.getPriceWithDiscount(
                contractCreateDto.getTourId(),
                contractCreateDto.getUserId());



        contractRepository.createContract(id,
                contractCreateDto.getTourId(),
                contractCreateDto.getUserId(),
                false);
    }

    public void deleteContract(String id) {
        contractRepository.deleteContract(id);
    }

    public void updateContract(String id, Boolean wasPaid) {
        contractRepository.updateContract(id, wasPaid);
    }

    public List<ContractDto> getContractsByUser(String userId) {
        return contractRepository.getContractsByUser(userId)
                .stream().map(contract -> ContractDto.builder()
                        .id(contract.getId())
                        .userId(contract.getUserId())
                        .tourId(contract.getTourId())
                        .price(contract.getPrice())
                        .wasPaid(contract.getWasPaid())
                        .build()
                ).toList();
    }

    public List<ContractDto> getContractsByTour(String tourId) {
        return contractRepository.getContractsByTour(tourId)
                .stream().map(contract -> ContractDto.builder()
                        .id(contract.getId())
                        .userId(contract.getUserId())
                        .tourId(contract.getTourId())
                        .price(contract.getPrice())
                        .wasPaid(contract.getWasPaid())
                        .build()
                ).toList();
    }

    public ContractDto getContractById(String id) {
        return contractRepository.getContractById(id)
                .map(contract -> ContractDto.builder()
                        .id(contract.getId())
                        .userId(contract.getUserId())
                        .tourId(contract.getTourId())
                        .price(contract.getPrice())
                        .wasPaid(contract.getWasPaid())
                        .build()
                ).orElseThrow(() -> new EntityNotFoundException("Contract with id " + id + " not found"));
    }


}
