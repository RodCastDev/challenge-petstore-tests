const fs = require('fs');
const path = require('path');


const testName = process.argv[2];
if (!testName) {
  console.error('You must provide the test name. Example: node scripts/generate-report.js getPet');
  process.exit(1);
}

const summaryPath = path.join(__dirname, `../reports/json/${testName}-summary.json`);
if (!fs.existsSync(summaryPath)) {
  console.error(`File not found: ${summaryPath}`);
  process.exit(1);
}

const rawData = fs.readFileSync(summaryPath);
const summary = JSON.parse(rawData);

const groupName = Object.keys(summary.root_group.groups)[0];
const checks = summary.root_group.groups[groupName]?.checks || {};
const metrics = summary.metrics;

// Build the content of the .md report
const mdContent = `
# Performance Report for ${groupName}

## ✅ Checks
- Status is 200: ✅ ${checks["Status is 200"]?.passes ?? 'N/A'}
- Field "name" exists: ✅ ${checks['Field "name" exists']?.passes ?? 'N/A'}

## 📊 Metrics
- Request duration (avg): ${metrics.http_req_duration?.avg?.toFixed(2) ?? 'N/A'} ms
- 95th percentile: ${metrics.http_req_duration?.["p(95)"]?.toFixed(2) ?? 'N/A'} ms
- Requests: ${metrics.http_reqs?.count ?? 'N/A'}
- Iterations: ${metrics.iterations?.count ?? 'N/A'}
- Failed requests: ${metrics.http_req_failed?.fails ?? 'N/A'}
`;

// Write Markdown file
const reportPath = path.join(__dirname, `../reports/markdown/${testName}-performance-report.md`);
fs.writeFileSync(reportPath, mdContent.trim());

console.log(`✅ Report generated: reports/markdown/${testName}-performance-report.md`);
