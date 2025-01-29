package com.bank.repository;

import com.bank.domain.CardStatus;
import com.bank.domain.entity.CreditCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE CreditCard c SET c.observation = :observation, c.status = :status WHERE c.id = :id")
    void updateStatus(@Param("id") Long id, @Param("observation") String observation,
                       @Param("status") CardStatus status);
    @Transactional
    @Modifying
    @Query("UPDATE CreditCard c SET c.cvv = :cvv, c.expirationDate = :expirationDate WHERE c.id = :id")
    void updateCard(@Param("id") Long id, @Param("cvv") String cvv,
                    @Param("expirationDate") LocalDateTime expirationDate);

}
