package com.test.BankApp.Controller;


import com.test.BankApp.Exceptions.InsufficentFundsException;
import com.test.BankApp.Exceptions.InvalidPinException;
import com.test.BankApp.Exceptions.ResourceNotFoundException;
import com.test.BankApp.Model.BankAccount;
import com.test.BankApp.Model.BankAccountDTO;
import com.test.BankApp.Model.BankAccountRepository;
import com.test.BankApp.Model.TransactionDTO;
import com.test.BankApp.Service.BankAccountService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/show")
    public Iterable<BankAccount> getPhoto(){
        return bankAccountService.get();
    }

    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccountDTO bankAccountDTO){
        BankAccount account = bankAccountService.createAccount(bankAccountDTO.getName(), bankAccountDTO.getPin());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<BankAccount> deposit(@PathVariable Long accountId, @RequestBody TransactionDTO transactionDTO){
       BankAccount account = bankAccountService.makeDeposit(accountId,transactionDTO.getAmount(),transactionDTO.getPin());
       return ResponseEntity.ok(account);
    }


    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId,@RequestBody TransactionDTO transactionDTO){
        BankAccount account = bankAccountService.makeWithdraw(accountId, transactionDTO.getAmount(), transactionDTO.getPin());
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{sourceAccountId}/transfer/{destinationAccountId}")
    public ResponseEntity<String> transfer(@PathVariable Long sourceAccountId, @PathVariable Long destinationAccountId, @RequestBody TransactionDTO transactionDTO){
       bankAccountService.makeTransfer(sourceAccountId,destinationAccountId,transactionDTO.getAmount(),transactionDTO.getPin());
       return ResponseEntity.ok("Transfer done successfully");
    }

}
