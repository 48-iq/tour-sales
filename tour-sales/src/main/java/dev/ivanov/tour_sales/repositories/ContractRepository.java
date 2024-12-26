package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

    @Query(nativeQuery = true, value = "select * from create_contract(:id, :tourId, :userId, :price)")
    void createContract(String id, String tourId, String userId, Double price);

    @Query(nativeQuery = true, value = "select * from delete_contract(:id)")
    void deleteContract(String id);

    @Query(nativeQuery = true, value = "select * from get_all_contracts()")
    List<Contract> getAllContracts();

    @Query(nativeQuery = true, value = "select * from get_contracts_by_user(:userId()")
    List<Contract> getContractsByUser(String userId);


}
