# Selenium + TestNG Automation Framework


A Java-based UI automation framework for testing [SauceDemo](https://www.saucedemo.com/) using Selenium WebDriver, TestNG, and Maven.


## ✨ Features


- Data-driven login testing with valid and invalid credentials
- Add-to-cart and remove-from-cart test scenarios
- Page Object Model (POM)
- Configurable browser and headless mode
- Automatic screenshots on test failure
- ExtentReports for test reporting
- Multi-browser support: Chrome, Firefox, and Edge
- CI pipeline with GitHub Actions
- Test reports and screenshots uploaded as CI artifacts


## 🛠️ Tech Stack


`Java` · `Selenium WebDriver` · `TestNG` · `Maven` · `Apache POI` · `ExtentReports` · `GitHub Actions`


## 📁 Project Structure


```text
src/test/
├── java/com/Shilan/
│   ├── tests/          # Test classes and BaseTest
│   ├── pages/          # Page Object classes
│   ├── utils/          # Config, Excel, screenshot & report utilities
│   └── listeners/      # TestNG listeners
│
└── resources/
    └── config.properties
▶️ How to Run
git clone https://github.com/ShilanKhedri/selenium-testng-framework.git
cd selenium-testng-framework
mvn test

Browser and execution settings can be configured in:

src/test/resources/config.properties
🔄 CI/CD

Tests are automatically executed with GitHub Actions on every push to main.

The pipeline:

Checkout → Setup Java → Run Maven Tests → Generate Reports → Upload Artifacts

📊 Reports

After a local test run:

ExtentReport.html → HTML test report
screenshots/ → Screenshots captured for failed tests

CI reports and screenshots are also available as GitHub Actions artifacts.
