package com.test.BankApp.Service;

import com.test.BankApp.Exceptions.InsufficentFundsException;
import com.test.BankApp.Exceptions.InvalidPinException;
import com.test.BankApp.Exceptions.ResourceNotFoundException;
import com.test.BankApp.Model.BankAccount;
import com.test.BankApp.Model.BankAccountDTO;
import com.test.BankApp.Model.BankAccountRepository;
import com.test.BankApp.Model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService (BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccountService(){
      bankAccountRepository = null;
    }

    public BankAccount createAccount(String name, int pin){
        BankAccount account = new BankAccount(name,pin);
        return bankAccountRepository.save(account);
    }

    public BankAccount makeDeposit(Long accountId, double amount, int pin){
        BankAccount account = getAccountById(accountId);
        validatePin(account, pin);
        account.setBalance(account.getBalance()+amount);
        return bankAccountRepository.save(account);
    }

    public BankAccount makeWithdraw(Long accountId, double amount, int pin){
        BankAccount account = getAccountById(accountId);
        validatePin(account, pin);
        isEnoughMoney(account, amount);
        account.setBalance(account.getBalance()-amount);
        return bankAccountRepository.save(account);
    }

    public void makeTransfer(Long sourceAccountId, Long destinationAccountId, double amount, int pin){
        BankAccount sourceAccount = getAccountById(sourceAccountId);
        BankAccount destinationAccount = getAccountById(destinationAccountId);
        validatePin(sourceAccount, pin);
        isEnoughMoney(sourceAccount, amount);
        sourceAccount.setBalance(sourceAccount.getBalance()-amount);
        destinationAccount.setBalance(destinationAccount.getBalance()+amount);

        bankAccountRepository.save(sourceAccount);
        bankAccountRepository.save(destinationAccount);
    }

    public Iterable<BankAccount> get() {
        return bankAccountRepository.findAll();
    }


    private BankAccount getAccountById(Long id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Account not found with this id:"+id));
    }

    private void validatePin(BankAccount account, int pin){
        if(account.getPin()!=pin) {
            throw new InvalidPinException("Uncorrect PIN for account: " + account.getName());
        }
    }

    private void isEnoughMoney(BankAccount account, double amount){
        if(account.getBalance()<amount){
            throw new InsufficentFundsException("You don't have enough money in "+account.getName()+" account");
        }
    }
}
