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
• Java 17
• Selenium WebDriver 4.25
• TestNG 7.10
• Maven
• Excel (Apache POI)
• Page Object Model (POM)
• Hybrid Automation Framework

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
• Explicit waits
• Stale element retry logic
• Robust element interactions
• Auto-highlighting for debugging

------------------------------------------------------------
4. Project Structure
------------------------------------------------------------
conduit-automation/
 ├── src/main/java
 │     ├── core (driver setup, waits, JS utils, element utils)
 │     ├── pages (POM classes)
 ├── src/test/java
 │     ├── base (BaseTest)
 │     ├── tests (all test classes)
 ├── src/test/resources
 │     ├── config.properties
 │     ├── testdata/ConduitTestData.xlsx
 ├── pom.xml
 ├── README.txt  (this file)

------------------------------------------------------------
5. How to Run Tests
------------------------------------------------------------
1. Open project in Eclipse or IntelliJ
2. Run any test class using TestNG runner.

------------------------------------------------------------
6. Requirements
------------------------------------------------------------
• JDK 17+
• Maven installed
• Chrome browser installed

------------------------------------------------------------
7. Notes
------------------------------------------------------------
• All locators follow the rule: only ID, name, className,
  linkText, partialLinkText, XPath (no CSS selectors).
• Framework includes element highlighting & retry on stale elements.
• Excel data drives registration & login scenarios.
