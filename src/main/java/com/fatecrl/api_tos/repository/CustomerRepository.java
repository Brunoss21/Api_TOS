package com.fatecrl.api_tos.repository;

import java.time.LocalDate;
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
    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findByName(@Param("name") String name);

    // Busca por data de nascimento
    @Query("SELECT c FROM Customer c WHERE c.birthdate = :birthdate")
    List<Customer> findByBirthdate(@Param("birthdate") LocalDate birthdate);


   /*@Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Cargo c WHERE c.customer.id = :customerId AND (c.status = 'pendente' OR c.status = 'em trânsito')")
    boolean hasPendingOrInTransitCargos(@Param("customerId") Long customerId);*/
}
