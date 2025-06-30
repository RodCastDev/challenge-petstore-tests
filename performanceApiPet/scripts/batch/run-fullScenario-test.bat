@echo off
REM Execute full CRUD performance scenario

set VUS=15
set DURATION=60s

for /f %%i in ('powershell -Command "Get-Date -Format yyyyMMdd_HHmmss"') do set TIMESTAMP=%%i
set REPORT_FILE=..\reports\fullScenario-summary-%TIMESTAMP%.json

echo Running fullScenario.test.js...
k6 run fullScenario.test.js --env VUS=%VUS% --env DURATION=%DURATION% --summary-export=%REPORT_FILE%

echo Test completed. Report saved to %REPORT_FILE%
pause
