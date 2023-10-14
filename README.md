# FinalProject 

This repository contains the testing framework and resources for the WEare web application project as part of the final project of Team 3 “The underdogs”. It serves as a foundation for testing and understanding the core functionality of the application and also aims to test the reliability, and performance of the web app. 


# Table of Contents
- [Structure](#structure)
- [Step by Step Guide](#guide)
- [Contributing](#contributing)
- [Notes](#notes)


## [Structure](#structure)

The project is organized into four main components:

**1. Manual Testing** 
   - Please refer to [Team 3 Final Project](https://github.com/TeamTheUnderdogs).

The Manual testing cover test plans, test cases, and related documentation per project requirements: 

- Test Plans: Contains high-level test plans outlining the testing strategy, objectives, and scope. In addition, the entry and exit criteria are included. 
- Test Cases: Contains detailed test cases for different functional areas of the application.
- Bug Reports: Contains templates and logs for reporting defects found during manual testing.

**2. Postman (API Testing)** 

The Postman directory contains the collection and environment files used for API testing.

- WEare_API_Collection.json: Postman collection file with a set of requests for testing the WEare application APIs.
- WEare_API_Environment.json: Postman environment file with environment variables and configurations for different testing environments (e.g., development, staging, production).
Running API Tests

**3. Rest Assured Testing** 

The RestAssured directory contains Java classes for automated testing of RESTful web services.
The Res Assured tests cover Java classes organized by test suites (e.g., user authentication, data retrieval, etc.).
pom.xml: Maven configuration file with dependencies required for the project.

**4. Selenium (UI Testing)**

The Selenium directory contains test scripts for UI testing of the WEare web application.
Contains Java classes for different test scenarios (e.g., login, registration, search functionality, etc.).
pom.xml: Maven configuration file with dependencies required for the project.


## [Step by Step Guide](#guide)


**1. Steps to review Manual tests** 
- Please refer to JIRA for the Manual Test cases.

**2. Steps to run the Postman requests:** 

- Import the WEare_API_Collection.json and WEare_API_Environment.json into your Postman workspace.
- Select the desired environment (e.g., development, staging) from the environment dropdown.
- Run the collection to execute the API tests.

**3. Steps to Run Rest Assured Tests:** 

- Running RESTful Web Service Tests
- Ensure you have Maven installed.
- Navigate to the RestAssured directory in the terminal.


**4. Steps to Run UI Tests:** 
- Ensure you have Maven installed.
- Navigate to the Selenium directory in the terminal.
- Choose the desired browser.


## [Contributing](#contributing)
- Elena Zlateva
- Vladislav Ganchev
- Denitsa Petrova

## [Notes](#notes)


