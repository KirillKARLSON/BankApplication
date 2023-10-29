package com.test.BankApp.Controller;


import com.test.BankApp.Model.*;
import com.test.BankApp.Service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/show")
    public List<BankAccountOpenInfoDTO> getBankAccount(){
        return bankAccountService.getAccountsOpenInfo();
    }

    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccountDTO bankAccountDTO){
        BankAccount account = bankAccountService.createAccount(bankAccountDTO.getName(), bankAccountDTO.getPin());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<BankAccountOpenInfoDTO> deposit(@PathVariable Long accountId, @RequestBody TransactionDTO transactionDTO){
       BankAccount account = bankAccountService.makeDeposit(accountId,transactionDTO.getAmount(),transactionDTO.getPin());
       BankAccountOpenInfoDTO accountOpenInfo = new BankAccountOpenInfoDTO(account.getName(), account.getBalance());
       return ResponseEntity.ok(accountOpenInfo);
    }


    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId,@RequestBody TransactionDTO transactionDTO){
        BankAccount account = bankAccountService.makeWithdraw(accountId, transactionDTO.getAmount(), transactionDTO.getPin());
        BankAccountOpenInfoDTO accountOpenInfo = new BankAccountOpenInfoDTO(account.getName(), account.getBalance());
        return ResponseEntity.ok(accountOpenInfo);
    }

    @PutMapping("/{sourceAccountId}/transfer/{destinationAccountId}")
    public ResponseEntity<String> transfer(@PathVariable Long sourceAccountId, @PathVariable Long destinationAccountId, @RequestBody TransactionDTO transactionDTO){
       bankAccountService.makeTransfer(sourceAccountId,destinationAccountId,transactionDTO.getAmount(),transactionDTO.getPin());
       return ResponseEntity.ok("Transfer done successfully");
    }

}
