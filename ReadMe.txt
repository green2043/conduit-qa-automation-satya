Project: Conduit Web Automation assignment
Technology: Selenium + Java + TestNG + Hybrid Framework + POM
Author: Satyanarayan Sahoo

------------------------------------------------------------
1. Overview
------------------------------------------------------------
This project automates the core user flows of the Conduit web
application (https://conduit.bondaracademy.com). The framework
follows a Hybrid Model combining:
-- Page Object Model (POM)
-- Data-Driven Testing (Excel)
-- Reusable Utility Layer
-- TestNG-based execution

------------------------------------------------------------
2. Technology Stack
------------------------------------------------------------
-- Java 17
-- Selenium WebDriver 4.25
-- TestNG 7.10
-- Maven
-- Excel (Apache POI)
-- Page Object Model (POM)
-- Hybrid Automation Framework

------------------------------------------------------------
3. Automated Scenarios
------------------------------------------------------------
The following scenarios are automated:

1. User Registration (single + multiple users from Excel)
2. User Login (single + multiple users from Excel)
3. Create Article
4. Global Feed – Article Listing & Navigation
5. Global Feed → Open Article & Data Verification

All tests are stable and include:
-- Explicit waits
-- Stale element retry logic
-- Robust element interactions
-- Auto-highlighting for debugging

------------------------------------------------------------
4. How to Run Tests
------------------------------------------------------------
1. Open project in Eclipse or IntelliJ
2. Run any test class using TestNG runner.

------------------------------------------------------------
5. Requirements
------------------------------------------------------------
-- JDK 17+
-- Maven installed
-- Chrome browser installed

------------------------------------------------------------
6. Notes
------------------------------------------------------------
-- All locators follow the rule: only ID, name, className,
  linkText, partialLinkText, XPath (no CSS selectors).
-- Framework includes element highlighting & retry on stale elements.
-- Excel data drives registration & login scenarios.

------------------------------------------------------------
7. Running the Full Test Suite (testng_testrunner.xml)
------------------------------------------------------------
The main TestNG suite file for this project is:

    testng_testrunner.xml

Run the full suite in two ways:

----------------------------
A) Run Using Command Prompt
----------------------------

1. Open Command Prompt  
2. Navigate to the project root (where pom.xml is located)

   Example:
   D:\SatyaWS\conduit-automation>

3. Run the suite:

   mvn clean test -DsuiteXmlFile=testng_testrunner.xml

This will:
- Launch the browser  
- Execute all configured tests  
- Apply TestNG parameters from the XML  
- Print results in console + target/surefire-reports  

----------------------------
B) Run Using IDE (Eclipse / IntelliJ)
----------------------------

1. Right-click on testng_testrunner.xml  
2. Select: Run As → TestNG Suite  
