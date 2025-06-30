
# 🐾 Swagger Pet Store - API Test Automation Framework

## 📌 Project Overview

This repository contains a professional API test automation framework built for the [Swagger Pet Store API](https://petstore.swagger.io/). It leverages **Serenity BDD**, **Cucumber**, and the **Screenplay Pattern** to ensure maintainability, reusability, and clean architecture.

> ✅ The goal is to demonstrate best testing practices, dynamic data handling, and scenario coverage for a RESTful service—without using Postman as an automation tool.

---

## 🚀 Technologies Used

- Java 17+
- Serenity BDD
- Cucumber
- JUnit
- Screenplay Pattern
- Gradle
- REST Assured
- Serenity Reports (HTML)

---

## 📋 Test Scenarios Proposed 

The following scenarios have been identified and automated:

### 🐶 Pet Registration (`POST /pet`)
- ✅ Successful registration of pets with different combinations of valid data.
- ✅ Successful pet creation with different data sets.
- ❌ Validation of required fields:
  - Missing `name`
  - Missing `photoUrls`
- ⚠️ Validation of invalid inputs:
  - Invalid `name` format
  - Invalid `status` domain values

### 🔎 Pet Retrieval (`GET /pet/{id}`)
- ✅ Retrieval using valid IDs (stored in session after registration).
- ✅ Confirmation that valid requests return a 200 status with accurate response bodies.
- ❌ Negative cases with:
  - Non-existing IDs (e.g. `99999`)
  - Invalid IDs (e.g. `0`, `-123`)

### 🗑️ Pet Deletion (`DELETE /pet/{id}`)
- ✅ Deletion of pets using dynamically stored IDs.
- ✅ Validation that the endpoint returns appropriate success responses (200) for deletions.
- ❌ Negative cases with:
  - Manual IDs not linked to existing pets
  - Invalid formats or values

---

## 🤖 Automation Implementation Summary (Step 4)

This framework implements automation using:

- **Cucumber Feature Files** to define behavior-driven test cases in Gherkin syntax.
- **Screenplay Pattern** to structure actions (`Tasks`), validations (`Questions`), and scenarios (`StepDefinitions`) with low coupling.
- **SharedPetData** class for ID session management and test data reuse across features.
- **Data-driven tests** using `Scenario Outline` and `DataTable` for flexible input.
- **Session variables** (`Serenity.setSessionVariable(...)`) to persist data between steps and scenarios.

All assertions and validations are centralized using `Questions` for expected status codes and business rules.

---

## 📄 Implemented Features (Step 5)

Here’s a list of `.feature` files that are implemented:

### 1. `register_pet.feature`
Registers new pets with various inputs, including valid and invalid combinations.

### 2. `get_pet_by_id.feature`
Fetches a pet using dynamic or manually specified IDs to validate response and status.

### 3. `delete_pet.feature`
Deletes a pet using IDs stored in session after registration. Includes validations for non-existing pets.

---

## 📦 How to Run the Project

1. Clone the repository.
2. Run the Swagger Pet Store API locally.
3. Run the tests:

```bash
./gradlew clean test --tests runners.PetStoreSuite
```

4. Generate the Serenity report:

```bash
./gradlew aggregate
```

5. Open the HTML report located in:

```
target/site/serenity/index.html
```

---

## 📈 Serenity Report Example

![Serenity Screenshot](docs\ReportTestAPI.png)

---

## 🧠 Design Principles Followed

- Clean Code & Readability
- Reusability of test logic
- Low coupling and high cohesion
- Separation of concerns via Screenplay architecture
- Professional logging and error messages for debugging

---

## ❌ What We Avoided

- No Postman collections or runners were used.
- No hardcoded data or static dependencies.
- No flaky tests depending on fixed response content.

---

## 📬 Contact

For questions or suggestions, feel free to reach out via GitHub Issues or email.

---
