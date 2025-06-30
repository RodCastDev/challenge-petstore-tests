# 🧪 challenge-petstore-tests

This repository contains **two independent projects** designed to test and validate the **Swagger Petstore API**:

1. **API Test Automation**
2. **Performance Testing with K6**

---

## 🔍 Project 1: API Test Automation

### 🧭 Overview

A professional API test automation framework for the Swagger Petstore API, built using **Serenity BDD**, **Cucumber**, and the **Screenplay Pattern**.

### 🚀 Technologies Used

- Java 17+
- Serenity BDD
- Cucumber + JUnit
- REST Assured
- Gradle
- Screenplay Pattern
- Serenity Reports (HTML)

### ✅ Implemented Scenarios

- **Pet Registration (POST /pet)**
- **Pet Retrieval by ID (GET /pet/{id})**
- **Pet Deletion by ID (DELETE /pet/{id})**

Covers valid data, negative cases, missing/invalid input validation, and dynamic ID management using session sharing.

### 🧠 Best Practices Applied

- Clean, structured code
- High reusability and low coupling
- Centralized validations
- No Postman or hardcoded data

### ▶️ How to Run

`bash
cd automation
./gradlew clean test --tests runners.PetStoreSuite
./gradlew aggregate

# 🧪 Performance Testing Project - Petstore API

This project focuses on evaluating the performance of the **Swagger Petstore API** using the **K6** load testing tool. The goal is to identify potential performance issues and validate response times under different loads.

---

## 🎯 Objective

To simulate realistic load conditions on key endpoints of the Swagger Petstore API and analyze:

- Response time
- Request throughput
- Failure rate

---

## ⚙️ Technologies Used

| Tool/Technology | Purpose                            |
|-----------------|------------------------------------|
| K6              | Load testing execution             |
| Node.js         | Markdown report generation         |
| PowerShell / .bat | Automation of test execution     |
| JSON            | Result export format               |
| Markdown (.md)  | Human-readable reports             |


