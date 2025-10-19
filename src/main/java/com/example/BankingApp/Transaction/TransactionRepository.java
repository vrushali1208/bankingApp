package com.example.BankingApp.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	List<TransactionEntity> findByAccountAccountId(Long accountId);

}
