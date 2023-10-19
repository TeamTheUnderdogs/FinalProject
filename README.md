# Final Project 

This repository contains the testing framework and resources for the WEare web application project as part of the final project of Team 3 “The Underdogs”. It serves as a foundation for testing and understanding the core functionality of the application and also aims to test the reliability, and performance of the web app. 

The goal is to ensure a seamless and reliable user experience while maintaining data security and system performance. Also, the team will follow the testing the latest practices and learnings, encompassing both manual and automated testing methodologies, to ensure comprehensive coverage of the testing process of the web application. 

# Table of Contents
- [Objective](#objective)
- [Testing approach](#testing)
- [Project Structure](#structure)
- [Repository Structure](#repository)
- [Step by Step Guide](#step-by-step-guide)
- [Tools, Languages and Framework](#tools-languages-framework)
- [Contributing](#contributing)
- [Documentation](#documentation)
- [Notes](#notes)

## [Objective](#objective)

The main purpose of the test process is to verify if the Social Media web application meets defined requirements. This means that the web app works to ensure that it functions reliably, securely, and effectively for its users. This involves a range of testing activities to identify and rectify potential issues before the platform is released to a wider audience.

## [Testing approach](#testing)

### Manual Testing ###

Manual testing will cover all aspects of the "WEare" application, including user interfaces, navigation, and user scenarios. These includes user and admin registration, content creation, user interaction and usability.

### Postman and Rest Assured ###

API testing will be performed on all exposed APIs. The testing scope includes testing different API endpoints, request methods, and data validation. 
This includes login validation, content creation and deletion and profile management. 

### Selenium ### 

Testing will include various scenarios such as user registration, login, profile management, search functionality and social interactions. 


## [Project Structure](#structure)

The project is organized into four main components:

### 1. Manual Testing ### 
   - Please refer to [Team 3 Final Project](https://final-project-team-3.atlassian.net/).

The Manual testing cover test plans, test cases, and related documentation per project requirements: 

- Test Plans: Contains high-level test plans outlining the testing strategy, objectives, and scope. In addition, the entry and exit criteria are included. 
- Test Cases: Contains detailed test cases for different functional areas of the application.
- Bug Reports: Contains templates and logs for reporting defects found during manual testing.

### 2. Postman (API Testing) ###  

The Postman directory contains the collection and environment files used for API testing.

- WEare_API_Collection.json: Postman collection file with a set of requests for testing the WEare application APIs.
- WEare_API_Environment.json: Postman environment file with environment variables and configurations for different testing environments (e.g., development, staging, production).
Running API Tests

### 3. Rest Assured Testing ###

The RestAssured directory contains Java classes for automated testing of REST Assured web services.
The Res Assured tests cover Java classes organized by test suites (e.g., user authentication, login registration, etc.).
pom.xml: Maven configuration file with dependencies required for the project.

### 4. Selenium (UI Testing)###

The Selenium directory contains test scripts for UI testing of the WEare web application.
Contains Java classes for different test scenarios (e.g., login, registration, search functionality, etc.).
pom.xml: Maven configuration file with dependencies required for the project.

## [Repository Structure](#repository)

- Test Framework which covers Rest Assured and Selenium testing
- Postman collection, Postman Environments and Reports
- Additional reports
  

## [Step by Step Guide](#step-by-step-guide)


**1. Steps to review Manual tests** 
- Please refer to reports in the repository and [JIRA Project]((https://final-project-team-3.atlassian.net/) for the Manual Test cases.


**2. Steps to review and run the Postman requests:** 

- Review the reports extracted by Newman
- Import the WEare_API_Collection.json and WEare_API_Environment.json into your Postman workspace.
- Select the desired environment (e.g., development, staging) from the environment dropdown.
- Run the collection to execute the API tests.


**3. Steps to Run Rest Assured Tests:** 

- Running REST Assured Web Service Tests
- Ensure you have Maven installed
- Navigate to the Rest Assured directory in the terminal


**4. Steps to Run Selenium UI**
- Ensure you have Maven installed.
- Navigate to the Selenium directory in the terminal.
- Choose the desired browser.

## [Tools, Languages and Framework](#tools-languages-framework)

- [JIRA](https://www.atlassian.com/software/jira) as tracking and project tool
- [XRAY](https://www.getxray.app/) to organize test cases, execute them, and generate detailed reports on test coverage and results
- [Java 11](https://www.java.com/en/) as the programming language used
- [Maven](https://maven.apache.org/) to build and automate the project
- [jUnit](https://junit.org/junit5/) as testing framework
- [IntelliJ](https://www.jetbrains.com/idea/) as IDE
- [SQL Database](https://www.freemysqlhosting.net/) as main database
  

## [Contributing](#contributing)
- Elena Zlateva: [Github Account](https://github.com/ElenaZlatevaNikolova) 
- Vladislav Ganchev: [Github Account](https://github.com/vladislavganchev)
- Denitsa Petrova: [Github Account](https://github.com/DenitsaN)


## [Documentation](#documentation)

- [Test Plan](https://drive.google.com/file/d/1oaedFnsmgLDLy7daQjimDlIxz8AA_KEk/view?usp=sharing)
- [Schedule](https://docs.google.com/spreadsheets/d/1-omMXMc-DQ2R_o4HFXL12XjSKpMbJrMLg-gz9B9eyyE/edit?usp=sharing)
- Bug test sample

For detailed information and additional documentation, refer to Test Plan](https://drive.google.com/file/d/1oaedFnsmgLDLy7daQjimDlIxz8AA_KEk/view?usp=sharing).


## [Notes](#notes)
