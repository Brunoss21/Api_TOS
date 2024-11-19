package com.fatecrl.api_tos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatecrl.api_tos.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Busca por ID
    Customer findById(long id);

    // Busca por nome do cliente
    @Query("SELECT c FROM Customer c WHERE c.companyName = :companyName")
    List<Customer> findByName(@Param("companyName") String companyName);

}
