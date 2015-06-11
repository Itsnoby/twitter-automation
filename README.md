# Automation Demo
This is an automation demo project to demonstrate opportunities of [Selenium 2.0 (Selenium WebDriver)] open source automation framework in combination with [TestNG] and [ReportNG] frameworks. 
The project is written in Java and can be built and executed using provided scripts for [Apache Ant] command-line tool.
The project includes two testing scripts for Twitter automation:

* The first script performs the following actions:
 * log in as registered user;
 * send a tweet;
 * follow a user from suggestions list.
* The second script demonstrates which form have tests execution reports in the case of errors.

You can run the scripts on Chrome or Firefox browsers on Windows, Linux or Mac. 

## Environment configuration
To run the tests you need to download and install:

* Java JDK for project compilation: http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Apache Ant command-line tool for the tests execution: http://ant.apache.org/bindownload.cgi
* Mozilla Firefox browser if you want to run the tests on the Firefox browser.
* Google Chrome / Chromium browser if you want to run the test on the Chrome browser.
 
## How to use
* Download the project.
* Download and install all required software.
* In command prompt navigate to the directory with project files and run one of the commands from examples below.
* View execution report in HTML format which is located at `./build/report/html/`
 
## Examples
Run the tests on the Firefox browser:

```sh
ant testAll -Dbrowser=firefox
```

Execution on the Chrome browser:

```sh
ant  testAll -Dbrowser=chrome
```

or without browser indication (`chrome` is default value):

```sh
ant testAll
```


[Selenium 2.0 (Selenium WebDriver)]:http://www.seleniumhq.org/projects/webdriver/
[TestNG]:http://testng.org/
[ReportNG]:http://reportng.uncommons.org/
[Apache Ant]:http://ant.apache.org/
