@echo off
REM Execute performance test for GET /pet/{id}

set PET_ID=10
set RATE=10
set DURATION=20s

REM Ensure reports/json folder exists
if not exist "..\reports\json" (
  mkdir "..\reports\json"
)

REM Get current timestamp
for /f %%i in ('powershell -Command "Get-Date -Format yyyyMMdd_HHmmss"') do set TIMESTAMP=%%i

REM Define output file
set REPORT_FILE=..\reports\json\getPet-summary-%TIMESTAMP%.json

echo Running getPet.test.js...
k6 run getPet.test.js --env PET_ID=%PET_ID% --env RATE=%RATE% --env DURATION=%DURATION% --summary-export=%REPORT_FILE%

echo Test completed. Report saved to %REPORT_FILE%
pause
