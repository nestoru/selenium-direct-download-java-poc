[![](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/donate/?hosted_button_id=58F9TDDRBND4L)

# Selenium Direct Downloads - A Java POC
To download directly from selenium webdriver you have to inject a link in the DOM, otherwise the driver will hang forever.

## Setup
- Download the Selemium webdriver jars https://www.selenium.dev/downloads/
- Download the Firefox gecko driver https://github.com/mozilla/geckodriver/releases
- Checkout this project
- Add to the root folder the downloaded uncompressed jars folder and the geckodriver executable

## Compile
```
javac -cp '.:./selenium-java-4.16.1/*:./selenium-java-4.16.1/lib/*' SeleniumFirefoxTest.java
```

## Run
```
java -cp '.:./selenium-java-4.16.1/*:./selenium-java-4.16.1/lib/*' SeleniumFirefoxTest --gecko-driver-path './geckodriver' --download-url "https://your-file-url" --headless
```

