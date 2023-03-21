## Java Banking Application

## About the project:

#### This is a banking application project that I am further developing. The application is written in pure Java and has database connectivity. The application has been fully tested using tests in JUnit. To communicate with the database, the AccountDao class was used where methods were placed: GET, POST, PUT, DELETE. 
- as the owner of the bank, we can create a new bank, give it a name and address, create a new customer, enter its data, and give the customer a unique account number

- as a customer, we can deposit money into the account, withdraw money from the account, check the account balance, make transfers from one account to another, open a new account
## TODO:
| DONE :)                                                               | IN PROGRESS :)                                |
|-----------------------------------------------------------------------|-----------------------------------------------|   
| - Created Application                                                 | - I need to create tests for ApplicationCli   |
| - Created Account                                                     | - I need to create tests for AccountDao       |
| - Created  Address                                                    | - I need to create a view for the application |
| - Created  Bank                                                       | - Sending emails to the customer              |
| - Created  BankApplicationConstants                                   |                                               |
| - Created  Client                                                     |                                               |
| - Created  Transfer                                                   |                                               |
| - Created enuma TransferStatus                                        |                                               |
| - Created UniqueIdentifierGenerator                                   |                                               |
| - Created  UniqueIdGenerator                                          |                                               |
| - Created AccountDao                                                  |                                               |
| - Created ApplicationCli                                              |                                               |
| - Created and executed tests for individual classes and their methods |                                               |
| - Connected to the database                                           |                                               |

## How to run:

## What you can find in my project:
* Account, this is the class that is responsible for creating the user's account, mnadad the user's account number, the initial balance of the account, as well as giving the id. In addition, the class has methods that handle withdrawal of funds, deposit of funds, as well as checking the account balance 
* Address, jest to klasa, która odpowiada za dodawanie danych o użytkowniku
* Bank, jest to klasa, w której dokonujemy rejestracji nowego klienta, możemy również utworzyć nowy bank, zobaczyć listę wszystkich klientów banku
* Client, jest to klasa, która odpowiada, za otworzenie konta
* Transfer, jest to klasa, która odpowiada za wykonywanie przelewów
* TransferStatus jest to enum, w którym umieszczone są statusy o błedach, lub poprawnych komunikatach w aplikacji
* UniqueIdentifierGenerator interfejs odpowiadający za nadawanie unikalnych numerów kont dla nowego klienta
* UniqueIdGenerator klasa odpowiadająca za generowanie unikalnych identyfikatorów użytkownika
* AccountDao jest to klasa odpowiadająca za komunikację z bazą danych
* ApplicationCli jest to klasa, która pokazuje jak działa aplikacja
* Dodatkowo aplikacja posiada klasy testowe, gdzie za pomocą JUnit przetestowałem działanie aplikacji

## Technologies used: 
| Technologies I used |
|---------------------|
| - Java 17           |
| - H2Database        |
| - Git               |
| - JUnit             |
| - Maven             |








