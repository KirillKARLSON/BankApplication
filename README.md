# BankApplication
Test task for some company

This application represents to users oppotunity to create bank accounts, 
put and withdraw funds and also make tranfers of money between accounts.

Application consists of:
- BankAccount (represents main object);
- BankAccountDTO, TransactionDTO (Classes for describing a user request);
- BankAccountService (class for implementing business logic, actions on objects);
- BankAccountController (class for processing user requests and server response);
- InsufficentFundsException, InvalidPinException, ResourceNotFoundException;
- ApiError and RestExceptionHandler (for handle Exception and show bug-message);

# Used Technologies:
Spring Framework, Spring Boot, Lombok, Maven, Spring Web, H2 Database, Spring Data JPA


# How to use application:
1. Download the contents of the repository to your computer and run it in your IDE;
2. To create a few accounts go to Postman and make Http POST-Request on "http://localhost:8080/accounts"
with body {"name":"Name", "pin": yourPin(Integer)}, repeat this request few times with other data-set;
3. To show all account make Http Get-Request on "http://localhost:8080/accounts/show";
4. To make deposit on account you have to make Http PUT-Request on "http://localhost:8080/accounts/{accountId}/deposit"
with body { "pin": accountPin(), "amount": yourAmount(double)}. If accountId doesn't exist, you'll see a bug-message.
If pin of your account is uncorrect, you'll also see a bug-message.
5. To make withdraw on account you have to make Http PUT-Request on "http://localhost:8080/accounts/{accountId}/withdraw"
with body { "pin": accountPin(), "amount": yourAmount(double)}. If accountId doesn't exist, you'll see a bug-message.
If pin of your account is incorrect, you'll also see a bug-message. If you don't have enough money for withdraw, you'll
see a bug-message;
6. To make transfer from one account to another account you have to make Http PUT-Request on "http://localhost:8080/accounts//{sourceAccountId}/transfer/{destinationAccountId}"
with body { "pin": accountPin(), "amount": yourAmount(double)}. If sourceAccountId or destinationAccountId doesn't exist,
you'll see a bug-message. If pin of sourceAccount is incorrect, you'll also see a bug-message. If you don't have enough money for transfer,
you'll see a bug-message;
  
