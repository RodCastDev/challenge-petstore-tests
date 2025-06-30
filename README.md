# 🧪 challenge-petstore-tests

Este repositorio contiene dos proyectos independientes diseñados para probar y validar la API Swagger Petstore:

1. **Automatización de pruebas funcionales (API Test Automation)**
2. **Pruebas de rendimiento (Performance Testing con K6)**

---

## 🔍 Proyecto 1: API Test Automation

### 🧭 Descripción

Framework profesional de automatización de pruebas para la API Swagger Petstore, usando **Serenity BDD**, **Cucumber** y el **patrón Screenplay**.

### 🚀 Tecnologías utilizadas

- Java 17+
- Serenity BDD
- Cucumber + JUnit
- REST Assured
- Gradle
- Screenplay Pattern
- Serenity Reports (HTML)

### ✅ Escenarios implementados

- **Registro de mascotas (POST /pet)**
- **Consulta de mascotas por ID (GET /pet/{id})**
- **Eliminación de mascotas por ID (DELETE /pet/{id})**

Incluye validaciones de datos válidos, casos negativos, inputs faltantes o inválidos, y manejo de IDs dinámicos mediante sesión compartida.

### 🧠 Buenas prácticas

- Código limpio y estructurado
- Alta reutilización y bajo acoplamiento
- Centralización de validaciones
- Sin Postman ni datos quemados

### ▶️ Cómo ejecutar

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


