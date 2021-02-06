package com.tavant.exam.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.exam.exception.AccountNotFoundException;
import com.tavant.exam.exception.EmptyObjectException;
import com.tavant.exam.exception.NoDataFoundException;
import com.tavant.exam.model.Account;
import com.tavant.exam.repository.AccountRepository;


@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	AccountRepository accountRepository;
	@GetMapping
	public String getAccount() {
		return "Inside Account Model";
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() throws NoDataFoundException, AccountNotFoundException 
	{
		Optional<List<Account>> optional = Optional.of(accountRepository.findAll());
		//To get all details in Json
		if(optional.isEmpty())
		{
			throw new AccountNotFoundException("Record not found");
			
		}
		else {
			return ResponseEntity.ok(optional.get());	
		}

	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<?> getAccountById(@PathVariable("accountNumber") Integer id) throws AccountNotFoundException {
		Optional<Account> optional = accountRepository.findById(id);
		//To get the details by id
		if(optional.isPresent()) 	{
			return ResponseEntity.ok(optional.get());
		}
		else {
			throw new AccountNotFoundException("Record not found");
		}
		
	}
	
	@PostMapping 
	public Account addAccount(@RequestBody @Valid Account account) throws EmptyObjectException {
		//To add an account details
		if(account.getAccountNumber() == null )
		{
			throw new EmptyObjectException("Provide Employee Object");
		}
		return accountRepository.save(account);
	}
	
	@DeleteMapping("/del/{accountNumber}")
	public String deleteAccount(@PathVariable("accountNumber") Integer id) throws AccountNotFoundException {
		// To delete an account details
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("Details not found"));
		accountRepository.delete(account);	 
		return "Deleted";
		 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Integer Id, @Valid @RequestBody Account AccountDetails) throws AccountNotFoundException {
	 // To update the existing account details
	Account account = accountRepository.findById(Id).orElseThrow(() -> new AccountNotFoundException("Not found "));
	 
	 account.setAccountNumber(AccountDetails.getAccountNumber());
	 account.setHolderName(AccountDetails.getHolderName());
	 account.setHolderAddress(AccountDetails.getHolderAddress());
	 account.setAge(AccountDetails.getAge());
	 account.setEmail(AccountDetails.getEmail());
	 
	 final Account updatedAccount = accountRepository.save(account);
	 return ResponseEntity.ok(updatedAccount);
	}

	
}
