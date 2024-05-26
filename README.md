AmazonAutomation

Overview

AmazonAutomation is a test automation project built using Java and Selenium WebDriver. It follows the Page Object Model (POM) design pattern, enabling a more readable, maintainable, and reusable test code structure. The project leverages an XML file for configuration, and integrates with Selenoid for scalable browser automation, as well as Allure for detailed and beautiful test reports.

Features
Java and Selenium WebDriver: Core technologies for browser automation.
Page Object Model (POM): Design pattern to enhance code maintainability and readability.
XML Configuration: Centralized configuration management.
Selenoid: Scalable and efficient browser automation.
Allure Reporting: Comprehensive and visually appealing test reports.
Project Structure
The project is organized as follows:

src/main/java: Contains the main Java source files.
pages: Contains the Page Object Model classes.
tests: Contains the test classes.
utils: Contains utility classes for common operations.
src/test/resources: Contains test resources and configuration files.
config.xml: Main configuration file.
pom.xml: Maven Project Object Model file.
Getting Started
Prerequisites
Java 11 or higher
Maven 3.6 or higher
Selenoid
Allure
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/maor1614/AmazonAutomation.git
cd AmazonAutomation
Install dependencies:

bash
Copy code
mvn clean install
Set up Selenoid:

Follow the Selenoid installation guide to set up Selenoid on your machine.

Set up Allure:

Follow the Allure installation guide to install Allure.

Running Tests
Update the config.xml file:

Ensure that the config.xml file has the correct configuration for your environment.

Run the tests:

bash
Copy code
mvn test
Generate Allure report:

After running the tests, generate the Allure report using:

bash
Copy code
allure serve target/allure-results
