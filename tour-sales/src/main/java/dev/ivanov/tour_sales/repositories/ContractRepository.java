package dev.ivanov.tour_sales.repositories;

import dev.ivanov.tour_sales.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

    @Query(nativeQuery = true, value = "call create_contract(:id, :tourId, :userId, :wasPaid)")
    void createContract(String id, String tourId, String userId, Boolean wasPaid);

    @Query(nativeQuery = true, value = "call delete_contract(:id)")
    void deleteContract(String id);

    @Query(nativeQuery = true, value = "select get_contract_by_id(:id)")
    Optional<Contract> getContractById(String id);

    @Query(nativeQuery = true, value = "select get_contracts_by_user(:userId()")
    List<Contract> getContractsByUser(String userId);

    @Query(nativeQuery = true, value = "select get_contracts_by_tour(:tourId)")
    List<Contract> getContractsByTour(String tourId);

    @Query(nativeQuery = true, value = "call update_contract_by_id(:id, :wasPaid)")
    void updateContract(String id, Boolean wasPaid);
}
