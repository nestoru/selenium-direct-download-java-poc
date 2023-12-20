# Selenium Direct Downloads - A Java POC
To download directly from selenium webdriver you have to inject a link in the DOM, otherwise the driver will hang forever.

## Setup
- Download the Selemium webdriver jars https://www.selenium.dev/downloads/
- Download the Firefox gecko driver https://github.com/mozilla/geckodriver/releases
- Create a dir for the project and add the uncompress jars folder and the geckodriver executable in it

## Compile
```
javac -cp '.:./selenium-java-4.16.1/*:./selenium-java-4.16.1/lib/*' SeleniumFirefoxTest.java
```

## Run
```
java -cp '.:./selenium-java-4.16.1/*:./selenium-java-4.16.1/lib/*' SeleniumFirefoxTest --gecko-driver-path './geckodriver' --download-url "https://your-file-url" --headless
```

