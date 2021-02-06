package com.tavant.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.exam.model.Account;
//To use Spring JPA repository we have to extend the interface to JpaRepository and add the class and primary key

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{


	
}
