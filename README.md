# Nova Invest API

_Nova Invest_ is a robust, Spring Boot-based API designed to manage investments in various market assets, including stocks, commodities, indexes, and more. The platform offers comprehensive portfolio management, enabling users to track their investments, execute buy/sell orders, and analyze portfolio performance through a clean and intuitive API.

---

## Table of Contents

- [Key Features](#key-features)
- [Technologies](#technologies)
- [Database Model](#database-model)
  - [Entities Overview](#entities-overview)
- [API Endpoints](#api-endpoints)
  - [User Management](#user-management)
  - [Portfolio Management](#portfolio-management)
  - [Investment Management](#investment-management)
  - [Asset Management](#asset-management)
  - [Order Management](#order-management)
  - [Watchlist Management](#watchlist-management)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
- [Best Practices & Contribution](#best-practices--contribution)
- [License](#license)

---

## Key Features

- **User Management**: Secure registration, login, and profile management with JWT-based authentication.
- **Portfolio Management**: Users can create and manage portfolios, add investments, and monitor portfolio performance.
- **Investment Tracking**: Detailed tracking of asset purchases and sales with automatic profit/loss calculation.
- **Asset and Market Data**: Access real-time data on assets and financial markets.
- **Order Management**: Place, update, and cancel buy/sell orders for assets.
- **Watchlists**: Track selected assets with customizable watchlists.

---

## Technologies

- **Backend**: Java 17, Spring Boot
- **Security**: Spring Security with JWT authentication
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Utilities**: Lombok for code simplification

---

## Database Model

### Entities Overview

1. **User**  
   Manages user information and authentication.
   - **Fields**: `id`, `userName`, `email`, `password`, `firstName`, `lastName`, `balance`, `phoneNumber`, `role`, `createdAt`, `updatedAt`
   - **Relationships**: 
     - `OneToMany` with `Portfolio`
     - `ManyToMany` with `Watchlist`

2. **Portfolio**  
   Represents a user’s collection of investments.
   - **Fields**: `id`, `name`, `description`, `createdAt`, `updatedAt`
   - **Relationships**: 
     - `ManyToOne` with `User`
     - `OneToMany` with `Investment`

3. **Investment**  
   Tracks the ownership of a financial asset within a portfolio.
   - **Fields**: `id`, `purchasePrice`, `quantity`, `transactionFees`, `currentPrice`, `profitOrLoss`
   - **Relationships**: 
     - `ManyToOne` with `Portfolio`
     - `ManyToOne` with `Asset`

4. **Asset**  
   Represents a market-traded asset (e.g., stocks, commodities).
   - **Fields**: `id`, `symbol`, `name`, `type`, `marketPrice`
   - **Relationships**: 
     - `OneToMany` with `Investment`

5. **Order**  
   Tracks buy/sell orders for an investment.
   - **Fields**: `id`, `type`, `orderDate`, `status`, `quantity`, `price`
   - **Relationships**: 
     - `ManyToOne` with `Investment`

6. **Market**  
   Represents the financial market where assets are traded.
   - **Fields**: `id`, `name`, `country`
   - **Relationships**: 
     - `OneToMany` with `Asset`

7. **Transaction**  
   Tracks the movement of funds during asset trades.
   - **Fields**: `id`, `transactionDate`, `amount`, `type`
   - **Relationships**: 
     - `ManyToOne` with `Portfolio`

8. **Watchlist**  
   Allows users to track selected assets.
   - **Fields**: `id`, `name`
   - **Relationships**: 
     - `ManyToMany` with `Assets`
     - `ManyToMany` with `Users`

---

## API Endpoints

### User Management
- **POST** `/api/users/register`: Register a new user.
- **POST** `/api/users/login`: Authenticate user and return JWT token.
- **GET** `/api/users/me`: Retrieve profile details of the authenticated user (balance, portfolios, etc.).
- **PUT** `/api/users/me`: Update the profile of the authenticated user.

### Portfolio Management
- **POST** `/api/portfolios`: Create a new portfolio.
- **GET** `/api/portfolios/{portfolioId}`: Get details of a specific portfolio.
- **PUT** `/api/portfolios/{portfolioId}`: Update portfolio information.
- **DELETE** `/api/portfolios/{portfolioId}`: Remove a portfolio.

### Investment Management
- **POST** `/api/portfolios/{portfolioId}/investments`: Add an investment to a portfolio.
- **GET** `/api/portfolios/{portfolioId}/investments/{investmentId}`: Retrieve a specific investment.
- **PUT** `/api/portfolios/{portfolioId}/investments/{investmentId}`: Update an investment.
- **DELETE** `/api/portfolios/{portfolioId}/investments/{investmentId}`: Remove an investment from a portfolio.

### Asset Management
- **GET** `/api/assets`: List available assets.
- **GET** `/api/assets/{assetId}`: Get details of a specific asset.

### Order Management
- **POST** `/api/orders`: Place a buy/sell order.
- **GET** `/api/orders/{orderId}`: Get details of a specific order.
- **PUT** `/api/orders/{orderId}`: Update an order status.
- **DELETE** `/api/orders/{orderId}`: Cancel an order.

### Watchlist Management
- **POST** `/api/watchlists`: Create a new watchlist.
- **GET** `/api/watchlists/{watchlistId}`: Get details of a specific watchlist.
- **POST** `/api/watchlists/{watchlistId}/assets`: Add assets to a watchlist.
- **DELETE** `/api/watchlists/{watchlistId}/assets/{assetId}`: Remove assets from a watchlist.

---

## Getting Started

### Prerequisites
- Java 17+
- Maven
- PostgreSQL database

### Setup

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/yourusername/novainvest.git
