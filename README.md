# 🏦 Banking Application (Spring Boot REST API)

## 📌 Project Overview

This is a **Banking Application REST API** built using Spring Boot.
It provides core banking functionalities such as **Customer Management, Account Management, and Transactions** with proper validation, exception handling, and standardized API responses.

The application follows a **layered architecture** and is designed to demonstrate real-world backend development practices.

---

## 🚀 Features

* 👤 Customer Management (Create, Update, Delete, Fetch)
* 🏦 Account Management (Link accounts to customers)
* 💳 Transaction Handling (Credit/Debit)
* ✅ Input Validation using Jakarta Validation
* ⚠️ Global Exception Handling
* 📦 Standardized API Response Structure
* 📄 API Documentation using Swagger (OpenAPI)

---

## 🛠️ Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** MySQL / H2
* **ORM:** Spring Data JPA (Hibernate)
* **API Testing:** Swagger UI, Postman
* **Build Tool:** Maven

---

## 📂 Project Structure

```
com.example.BankingApp
│
├── Customer
│   ├── Controller
│   ├── Service
│   ├── ServiceImpl
│   ├── Repository
│   └── Entity
│
├── Account
├── Transaction
│
├── exception        # Global Exception Handling
├── response         # ApiResponse Wrapper
└── config           # Swagger Configuration
```

---

## 🔗 API Endpoints

### 👤 Customer APIs

```
GET    /api/customer/all/customers
POST   /api/customer/new
GET    /api/customer/{customerId}
PUT    /api/customer/{customerId}
DELETE /api/customer/{customerId}
```

### 🏦 Account APIs

```
POST   /api/account/new
GET    /api/account/all/accounts
GET    /api/account/customer/{customerId}
```

### 💳 Transaction APIs

```
POST   /api/transaction/new
GET    /api/transaction/account/{accountId}
```

---

## 📄 Swagger API Documentation

Access Swagger UI to test APIs:

```
http://localhost:8081/banking-app/swagger-ui/index.html
```

---

## ⚙️ How to Run the Project

### 1️⃣ Clone the Repository

```
git clone https://github.com/your-username/banking-app.git
```

### 2️⃣ Open in IDE

* IntelliJ / Eclipse / VS Code

### 3️⃣ Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

### 4️⃣ Run the Application

* Run as Spring Boot Application

### 5️⃣ Access APIs

* Swagger UI (recommended)
* Postman

---

## 📦 Sample API Response

```json
{
  "message": "Customer fetched successfully",
  "data": {
    "customerId": 1,
    "fname": "John",
    "lname": "Doe"
  },
  "success": true
}
```

---

## ⚠️ Error Handling

The application uses a **Global Exception Handler** to return consistent error responses.

### Example:

```json
{
  "message": "Customer not found with Id: 10",
  "data": null,
  "success": false
}
```

---

## 🔮 Future Enhancements

* 🔐 JWT Authentication & Authorization
* 👥 Role-Based Access Control (Admin/User)
* 📄 Pagination & Sorting
* 📊 Transaction Analytics
* 📡 Kafka Integration for Event Processing
* 🐳 Docker Deployment

---

## 👩‍💻 Author

**Vrushali kottkonda**
Backend Developer (Java + Spring Boot)

---

## ⭐ Contribution

Feel free to fork this repository and improve it. Suggestions are welcome!

---

## 📜 License

This project is open-source and available under the MIT License.
