# Pre-paid Card System
A program for managing pre-paid cards and pay with them.
Prepaid systems refers to services paid for in advance. In the case of pre-paid credit cards, are cards whose balance only depends on the ammount of money charged on them. 

### Authors:
- [Victor Nieves Sanchez](https://twitter.com/VictorNS69)

- Daniel Morgera Pérez

- Alejandro Carmona Ayllon

### Main idea:
![diagram.png](/.info/diagram.png)


### Methods:
- Buy a card 
- Pay
- Charge money
- Change PIN
- Consult balance
- Consult movements

### How to run the application:
```
java -jar PrePaidSystem.jar
```
Or running [MainWindow.java](/src/main/java/es/upm/pproject/tdd/frontend/MainWindow.java)

### How to run tests:
In the directory where the _pom.xml_ is.

```
$ mvn test
```

### Structure of the project:
```
├── LICENSE
├── pom.xml
├── PrePaidSystem.jar
├── README.md
├── src
│   ├── assets
│   │   └── data.dat
│   ├── main
│   │   └── java
│   │       └── es
│   │           └── upm
│   │               └── pproject
│   │                   └── tdd
│   │                       ├── backend
│   │                       │   ├── Card.java
│   │                       │   ├── CardOperations.java
│   │                       │   ├── FileOperations.java
│   │                       │   ├── HashPin.java
│   │                       │   ├── Movement.java
│   │                       │   └── PrePaidInterface.java
│   │                       ├── exceptions
│   │                       │   ├── AlreadyRegisteredException.java
│   │                       │   ├── ExpiredCardException.java
│   │                       │   ├── IncorrectPinException.java
│   │                       │   ├── IncorrectPinFormatException.java
│   │                       │   ├── InvalidAmountException.java
│   │                       │   ├── InvalidMovementException.java
│   │                       │   ├── NotEnoughMoneyException.java
│   │                       │   └── NotRegisteredException.java
│   │                       └── frontend
│   │                           ├── MainWindow.java
│   │                           └── ViewController.java
│   └── test
│       └── java
│           └── es
│               └── upm
│                   └── pproject
│                       └── tdd
│                           └── backend
│                               ├── BuyCardTest.java
│                               ├── CardOperationsTest.java
│                               ├── CardTest.java
│                               ├── ChangePinTest.java
│                               ├── ChargeMoneyTest.java
│                               ├── ConsultBalanceTest.java
│                               ├── ConsultMovementsTest.java
│                               ├── HashPinTest.java
│                               ├── LoadFileTest.java
│                               ├── MovementTest.java
│                               ├── PayTest.java
│                               └── SaveFileTest.java

```
### Tools Used:
```
- Java 1.8

- OpenJDK 8

- GIT 2.19.1

- Maven 3.5.4

- JUnit 5.20

- Sonar 3.3.0
```

### Copyright:
This exercise was proposed in the 2018/2019 course of the **_Programming Project_** subject. All rights reserved for authors and the teacher **_Guillermo Roman_**.

[Escuela Técnica Superior de Ingenieros Informáticos](http://www.etsiinf.upm.es/)

[Universidad Politécnica de Madrid](http://www.upm.es/)

### References:
<https://maven.apache.org/>

<https://www.oracle.com/technetwork/java/javase/tech/index-137868.html>

<https://git-scm.com/>

<https://www.sonarqube.org/>

<https://en.wikipedia.org/wiki/Cryptographic_hash_function>
