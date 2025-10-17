# TestNG Framework for OpenCart APP

A robust Selenium + TestNG automation framework featuring:
- Page Object Model (POM)
- Data-driven testing
- Parallel and cross-browser execution
- Extent Reports HTML reporting
- Docker/Selenium Grid support
- Jenkins CI integration

This project targets end-to-end UI automation for an OpenCart application and is designed for local runs, CI pipelines, and containerized/grid setups.

---

## Table of Contents
- Features
- Tech Stack
- Project Structure
- Getting Started
- Running Tests
  - Using TestNG suites
  - Cross-browser and parallel runs
  - Running with Docker/Selenium Grid
  - Running via batch script
- Test Data
- Reports and Logs
- Jenkins Integration
- Contributing
- FAQ
- Contact

---

## Features
- TestNG suite orchestration with multiple suite XMLs:
  - testng.xml (default run)
  - testng2.xml (alternate suite)
  - crossBrowserTesting.xml (cross-browser suite)
  - grouping.xml (group-based execution)
  - testngUsingDocker.xml (grid-based execution)
- Page Object Model for maintainable, scalable test design
- Data-driven tests using external test files in testData
- Extent Reports and TestNG reports generation
- Screenshot capture on failures (saved to screenshots)
- Easy local run via Run.bat
- CI/CD friendly with Maven commands and Jenkins compatibility

---

## Tech Stack
- Language: Java
- Test Framework: TestNG
- Web Automation: Selenium WebDriver
- Build: Maven
- Reporting: Extent Reports + TestNG default reports
- Optional: Docker + Selenium Grid, Jenkins

---

## Project Structure

Top-level contents:
- .classpath, .project, .settings/ — Eclipse project files
- OnlyRemote.txt — helper marker for remote/grid runs (reference)
- Run.bat — convenience script to trigger a default run locally
- pom.xml — Maven build and dependencies
- crossBrowserTesting.xml — TestNG suite for cross-browser
- grouping.xml — TestNG suite for grouped tests
- testng.xml — primary TestNG suite
- testng2.xml — alternate TestNG suite
- testngUsingDocker.xml — TestNG suite for Docker/Grid execution
- logs/ — runtime logs
- reports/ — HTML reports (e.g., Extent) output
- screenshots/ — screenshots captured during tests
- src/test/ — test code, page objects, utilities
- target/ — Maven build output
- test-output/ — TestNG default report output
- testData/ — external test data files

A typical code layout under src/test may include:
- pageobjects/ — Page Object classes
- tests/ — TestNG test classes
- utils/ or base/ — WebDriver management, helpers, hooks

(Note: Exact package names may vary; check src/test for details.)

---

## Getting Started

Prerequisites:
- Java 11+ installed and JAVA_HOME set
- Maven 3.8+ installed and on PATH
- A modern browser (e.g., Chrome/Firefox) locally if running without Grid
- Optional for Grid runs:
  - Docker Desktop or Docker Engine
  - Network access to Selenium Grid

Clone the repository:
```bash
git clone https://github.com/Ashu0729/TestNGFrameworkForOpenCartAPP.git
cd TestNGFrameworkForOpenCartAPP
```

Build and verify:
```bash
mvn -q -DskipTests clean install
```

---

## Running Tests

### 1) Using TestNG suites (local browser)
Run the default suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

Run the alternate suite:
```bash
mvn clean test -DsuiteXmlFile=testng2.xml
```

Run with groups (as defined in grouping.xml):
```bash
mvn clean test -DsuiteXmlFile=grouping.xml -Dgroups=smoke
```

Common JVM/system properties (if supported in your setup):
```bash
# Example of commonly used runtime properties
mvn clean test -DsuiteXmlFile=testng.xml -Dbrowser=chrome -Denvironment=qa -Dheadless=true
```
Tip: Check your driver factory/base config to confirm supported properties.

### 2) Cross-browser and parallel runs
Use the dedicated suite:
```bash
mvn clean test -DsuiteXmlFile=crossBrowserTesting.xml
```

You can often override browser at runtime:
```bash
mvn clean test -DsuiteXmlFile=crossBrowserTesting.xml -Dbrowser=firefox
```

### 3) Running with Docker/Selenium Grid
Start a Selenium Grid locally (one of the simple options):
```bash
# Standalone Chrome
docker run -d --rm -p 4444:4444 --name selenium-standalone selenium/standalone-chrome:4

# OR a full Grid (hub + nodes) - consult Selenium docs for advanced setups
```

Then execute the Grid-enabled suite:
```bash
mvn clean test -DsuiteXmlFile=testngUsingDocker.xml
```

Make sure your framework’s remote WebDriver config points to the Grid URL (e.g., http://localhost:4444/wd/hub for Selenium Grid 3 or http://localhost:4444 for Selenium Grid 4).

### 4) Running via batch script (Windows)
Double-click Run.bat or execute:
```bash
.\Run.bat
```

---

## Test Data
Place and manage your data files under:
- testData/

Typical usage:
- CSV/Excel/JSON property files consumed by data providers or utilities.
- Keep sensitive values out of VCS; prefer environment variables or secure stores.

---

## Reports and Logs

After a run:
- Extent Reports and custom HTML reports: reports/
- TestNG default reports: test-output/
- Logs: logs/
- Screenshots on failure: screenshots/

Open the latest HTML report in reports/ to review pass/fail details with screenshots embedded where configured.

---

## Jenkins Integration

Basic freestyle or pipeline steps:
```bash
# Freestyle "Execute Shell"
mvn clean test -DsuiteXmlFile=testng.xml
```

Sample Declarative Pipeline:
```groovy
pipeline {
  agent any
  tools {
    jdk 'jdk-11'
    maven 'maven-3'
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Test') {
      steps {
        sh 'mvn -B clean test -DsuiteXmlFile=testng.xml'
      }
    }
    stage('Archive Reports') {
      steps {
        archiveArtifacts artifacts: 'reports/**/*.html, test-output/**/*', fingerprint: true
        junit 'test-output/testng-results.xml'
      }
    }
  }
}
```

Tips:
- Configure JDK and Maven tools in Jenkins Global Tool Configuration.
- Archive the HTML reports and publish JUnit XML from test-output.

---

## Contributing
- Open an issue to propose improvements or report bugs.
- Use feature branches and submit a pull request.
- Follow the existing code style and naming conventions.
- Add or update tests and suites where applicable.

---

## FAQ

Q: How do I change the browser?
- Use a system property like -Dbrowser=chrome/firefox if supported, or select the appropriate TestNG suite (e.g., crossBrowserTesting.xml).

Q: Where do I find screenshots for failed tests?
- Check the screenshots/ directory.

Q: Where are the reports stored?
- Extent/HTML in reports/ and TestNG defaults in test-output/.

Q: How do I run on Selenium Grid?
- Start the Grid (local Docker or remote), then run the Docker/Grid suite: -DsuiteXmlFile=testngUsingDocker.xml. Ensure your remote URL matches the Grid endpoint.

---

## Contact
Maintainer: @Ashu0729  
Repository: https://github.com/Ashu0729/TestNGFrameworkForOpenCartAPP

If you find this useful, consider starring the repo!
