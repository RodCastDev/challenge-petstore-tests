@echo off
REM Execute performance test for POST /pet

set RATE=10
set DURATION=20s

for /f %%i in ('powershell -Command "Get-Date -Format yyyyMMdd_HHmmss"') do set TIMESTAMP=%%i
set REPORT_FILE=..\reports\createPet-summary-%TIMESTAMP%.json

echo Running createPet.test.js...
k6 run createPet.test.js --env RATE=%RATE% --env DURATION=%DURATION% --summary-export=%REPORT_FILE%

echo Test completed. Report saved to %REPORT_FILE%
pause
