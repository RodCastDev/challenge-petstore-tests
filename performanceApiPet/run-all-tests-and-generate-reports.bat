@echo off
echo 🔁 Ejecutando pruebas de performance con K6...

REM --- Ejecutar getPet ---
echo ▶ Ejecutando getPet.test.js...
k6 run tests\getPet.test.js --summary-export=reports\json\getPet-summary.json --env PET_ID=5 --env RATE=10 --env DURATION=20s

REM --- Ejecutar createPet ---
echo ▶ Ejecutando createPet.test.js...
k6 run tests\createPet.test.js --summary-export=reports\json\createPet-summary.json --env RATE=10 --env DURATION=20s

REM --- Ejecutar fullScenario ---
echo ▶ Ejecutando fullScenario.test.js...
k6 run tests\fullScenario.test.js --summary-export=reports\json\fullScenario-summary.json --env RATE=10 --env DURATION=20s

echo ­ƒôª Generando reportes Markdown...

REM --- Generar reportes para cada test ---
node "scripts\generate-report.js" getPet
node "scripts\generate-report.js" createPet
node "scripts\generate-report.js" fullScenario

echo ✅ Todos los tests y reportes fueron generados exitosamente.
pause
