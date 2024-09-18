# Investment-Portfolio API

The **Investment-Portfolio API** is a backend application designed to help users manage their investment portfolios. The API provides functionality for users to track stocks, manage their investments, check inflation rates, and handle payments securely. This service integrates with various financial data sources, such as Stripe for payments, to ensure accurate and secure portfolio management.

## Technologies and Tools

This project utilizes a variety of modern tools to build a scalable and secure API:

- **Spring Boot 3.3.3** - Simplifies the creation of Java-based web applications with minimal configuration.
- **Spring Security** - Implements authentication and authorization for secure access.
- **Spring Data JPA** - Manages data persistence with the Java Persistence API.
- **Swagger (Springdoc OpenAPI)** - Automatically generates API documentation.
- **JWT (JSON Web Token)** - Handles authentication with stateless JWT tokens.
- **MapStruct** - Simplifies mapping between DTOs and entities.
- **Liquibase** - Provides version control for the database schema.
- **Testcontainers** - Facilitates integration testing using Docker containers.
- **H2 Database** - In-memory database for testing purposes.
- **MySQL** - Production-grade database for persistence.
- **Lombok** - Reduces boilerplate code through annotations.
- **Stripe** - Facilitates payment processing.
- **SerpApi** - Used for integrating with Google Search to retrieve search results.
- **Checkstyle** - Ensures code quality by adhering to coding standards.

## Architecture Overview

The application is designed with modular controllers that handle different business functions, including user management, portfolio handling, inflation data, and payments.

- **Funds Management:** Handles adding and tracking payments through Stripe.
- **Inflation Service:** Provides inflation data, either for a specific country or globally.
- **Portfolio Management:** Manages users' stock portfolios, including buying, selling, and checking stock values.
- **Stock Management:** Provides stock data and allows admins to add new stocks.
- **User Management:** Handles user profiles and roles.

### API Endpoints

### 1. Funds Controller

- **POST** `/funds/add/{amount}` - Adds funds to the user's account (Stripe integration).
- **GET** `/funds/success/{sessionId}` - Checks if the payment was successful.
- **GET** `/funds/cancel/{sessionId}` - Handles canceled payment sessions.

### 2. Inflation Controller

- **GET** `/inflation/my` - Retrieves year-over-year inflation for a specific country.
- **GET** `/inflation/all` - Retrieves all inflation data from the database.
- **PUT** `/inflation/add-new` - Adds new inflation data (Admin only).
- **POST** `/inflation/update` - Updates inflation data (Admin only).

### 3. Portfolio Controller

- **GET** `/portfolio/me` - Retrieves the user's stock portfolio.
- **POST** `/portfolio/buy/{stockId}/{quantity}` - Buys stock for the user's portfolio.
- **PUT** `/portfolio/sell/{stockId}/{quantity}` - Sells stock from the user's portfolio.
- **GET** `/portfolio/me/portfolio-value` - Gets the total value of the user's portfolio (cash + stocks).

### 4. Stock Controller

- **GET** `/stocks/all` - Retrieves all available stocks.
- **POST** `/stocks/add-new` - Adds a new stock to the application (Admin only).
- **POST** `/stocks/add-new/force` - Adds a stock without validation (Admin only).

### 5. Users Controller

- **GET** `/users/me` - Retrieves the authenticated user's profile.
- **PUT** `/users/me` - Updates the authenticated user's profile.

## Data Models

### 1. Funds

The `Funds` entity represents a user's payment session for adding funds. It tracks payment details, status, and integrates with Stripe for payment processing.

- **id:** Long - Primary key, auto-generated.
- **userId:** Long - The ID of the user associated with the payment.
- **status:** Enum (PENDING, PAUSED, PAID) - The current status of the payment.
- **type:** Enum (ADDING, FINE) - The type of payment (either adding funds or a fine).
- **sessionUrl:** String - The URL for the Stripe payment session.
- **sessionId:** String - Unique session ID provided by Stripe.
- **amountToPay:** BigDecimal - The total amount to be paid (precision: 10, scale: 2).
- **isDeleted:** Boolean - Marks the entity as soft-deleted (`false` means active).

### 2. Inflation

The `Inflation` entity stores year-over-year inflation data for different countries.

- **id:** Long - Primary key, auto-generated.
- **yearToYear:** Double - The inflation rate from year to year.
- **countryName:** String - The name of the country associated with the inflation data (unique).
- **isDeleted:** Boolean - Marks the entity as soft-deleted (`false` means active).

### 3. Role

The `Role` entity defines the different roles a user can have in the system.

- **id:** Long - Primary key, auto-generated.
- **name:** Enum (ROLE_USER, ROLE_ADMIN) - The name of the role (unique).
- **isDeleted:** Boolean - Marks the entity as soft-deleted (`false` means active).

### 4. Stock

The `Stock` entity represents the stocks available in the portfolio management system.

- **id:** Long - Primary key, auto-generated.
- **stockSymbol:** String - The stock ticker symbol (unique).
- **isDeleted:** Boolean - Marks the entity as soft-deleted (`false` means active).

### 5. User

The `User` entity represents a registered user in the system.

- **id:** Long - Primary key, auto-generated.
- **email:** String - The user's email (unique).
- **password:** String - The user's hashed password.
- **firstName:** String - The user's first name.
- **lastName:** String - The user's last name.
- **cash:** BigDecimal - The user's available cash balance for trading.
- **stocks:** Set<UserStock> - A collection of stocks the user owns.
- **roles:** Set<Role> - The roles assigned to the user (many-to-many relationship).
- **isDeleted:** Boolean - Marks the entity as soft-deleted (`false` means active).

### 6. UserStock

The `UserStock` entity represents the relationship between a user and the stocks they own.

- **id:** Long - Primary key, auto-generated.
- **user:** User - The user who owns the stock (many-to-one relationship).
- **stock:** Stock - The stock that the user owns (many-to-one relationship).
- **quantity:** Integer - The number of shares the user owns.
- **isDeleted:** Boolean - Marks the entity as soft-deleted (`false` means active).

### Enums

- **PaymentType** - Enum that defines the type of payment:
  - `ADDING`: Payment for adding funds.
  - `FINE`: Payment for fines.

- **RoleName** - Enum that defines the user roles:
  - `ROLE_USER`: Regular user role.
  - `ROLE_ADMIN`: Admin role with elevated privileges.

- **Status** - Enum that defines the status of a payment:
  - `PENDING`: Payment is initiated but not yet completed.
  - `PAUSED`: Payment is temporarily paused.
  - `PAID`: Payment is completed successfully.


## Setup Instructions

### Prerequisites

- Java 17
- Docker

### Setup Steps

1. Check if you have Git installed: `git --version`
2. Clone the repository using SSH: `git clone git@github.com:...`
3. Alternatively, use HTTPS: `git clone https://...`
4. Navigate into the cloned repository: `cd jv-car-sharing-service`
5. (Optional) Check the repository status: `git status`

### Build the project:
./mvnw clean package

### Start the project:
 **Start Docker**: Make sure Docker is up and running before you proceed with the setup.

### After starting the application, the API documentation will be available at:
http://localhost:8080/swagger-ui.html - when you run app from intelij IDEA
http://localhost:8081/swagger-ui.html - when you run app from docker

## Login Credentials For Default Users

### MANAGER:

Email: admin@admin.com
Password: password

## Application 

- **Developer**: Bartosz Wójcik


