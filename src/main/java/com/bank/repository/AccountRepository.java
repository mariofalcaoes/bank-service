package com.bank.repository;

import com.bank.domain.AccountStatus;
import com.bank.domain.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Account c SET c.status = :status WHERE c.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") AccountStatus status);
}
