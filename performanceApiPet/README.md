# 🧪 Performance Testing Project - Petstore API

Este proyecto está orientado a evaluar el rendimiento de la API [Swagger Petstore](https://petstore.swagger.io/) utilizando la herramienta de pruebas de carga [K6](https://k6.io/).

## 📌 Objetivo

Realizar pruebas de performance sobre los endpoints más relevantes del sistema con el fin de detectar cuellos de botella, validar tiempos de respuesta y asegurar la escalabilidad de la API. Se incluyen scripts para pruebas específicas y un flujo completo de pruebas integradas.

---

## ⚙️ Tecnologías utilizadas

| Herramienta     | Uso principal                          |
|-----------------|----------------------------------------|
| [K6](https://k6.io/)              | Ejecución de pruebas de carga |
| Node.js         | Generación de reportes en Markdown     |
| PowerShell / .bat | Automatización de ejecución de tests |
| JSON            | Exportación de resultados              |
| Markdown (`.md`) | Documentación y reportes              |

---

## 📁 Estructura del proyecto

performanceApiPet/
├── docs/ # Documentación estratégica
│ ├── performance-strategy.md
│ └── performance-summary.md
├── scripts/
│ ├── generate-report.js # Genera reportes Markdown desde JSON
│ └── batch/ # Archivos .bat para ejecutar tests
│ └── run-all-tests-and-generate-reports.bat
├── tests/ # Scripts K6
│ ├── getPet.test.js
│ ├── createPet.test.js
│ └── fullScenario.test.js
├── reports/ # Resultados de ejecución
│ ├── json/ # Resúmenes JSON generados por K6
│ └── markdown/ # Reportes generados en formato Markdown
└── README.md
