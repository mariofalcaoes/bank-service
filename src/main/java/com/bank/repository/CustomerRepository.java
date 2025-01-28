package com.bank.repository;

import com.bank.domain.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Page<Customer> findAllByOrderByIdDesc(Pageable pageable);

    Optional<Customer> findByCpf(String cpf);

}
