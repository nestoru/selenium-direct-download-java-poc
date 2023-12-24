[![](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/donate/?hosted_button_id=58F9TDDRBND4L)

# Selenium Direct Downloads - A Java POC
To download directly from selenium webdriver you have to inject a link in the DOM, otherwise the driver will hang forever.

## Manual Setup
- Download the Selemium webdriver jars https://www.selenium.dev/downloads/
- Download the Firefox gecko driver https://github.com/mozilla/geckodriver/releases
- Create a directory where you will checkout the code from this repository
- Enter that directory and create the app-dependencies sub-directory
- Checkout this project
  ```
  git clone https://github.com/nestoru/selenium-direct-download-java-poc.git
  ```
- Add to the root folder the app-dependencies directory the  uncompressed jars folder and the geckodriver executable

## Compile
```
javac -cp '.:../app-dependencies/*:../app-dependencies/lib/*' SeleniumFirefoxTest.java
```

## Run
```
java -cp '.:../app-dependencies/*:../app-dependencies/lib/*' SeleniumFirefoxTest --gecko-driver-path '../app-dependencies/geckodriver' --download-url "https://your-download-url" --download-dir "/your/download/dir" --headless
```

## Docker Setup

```
mkdir -p ~/workspace/selenium-poc
cd ~/workspace/selenium-poc
git clone https://github.com/nestoru/selenium-direct-download-java-poc.git
cd selenium-direct-download-java-poc/
docker stop selenium-firefox; docker rm selenium-firefox
docker build -t selenium-firefox .
docker run -d -v $(pwd):/usr/src/app --name selenium-firefox selenium-firefox tail -f /dev/null
docker exec -it selenium-firefox /bin/bash
# Compile (javac)
# Run (java) using --download-dir "/usr/src/app/downloads" so that you get the downloaded file in your original local directory
```
