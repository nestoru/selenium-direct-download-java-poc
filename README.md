[![](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/donate/?hosted_button_id=58F9TDDRBND4L)

# Selenium Direct Downloads - A Java POC
To download directly from selenium webdriver you have to inject a link in the DOM, otherwise the driver will hang forever.

## OSX Manual Setup
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
javac -encoding UTF-8 -cp '.:../app-dependencies/*:../app-dependencies/lib/*' SeleniumFirefoxTest.java
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

## Ubuntu 22.04 Setup
```
apt install -y default-jdk maven
update-java-alternatives -l 
update-java-alternatives -s java-1.11.0-openjdk-amd64
java -version
mkdir -p ~/workspace/selenium-poc
cd ~/workspace/selenium-poc
git clone https://github.com/nestoru/selenium-direct-download-java-poc.git
mkdir -p app-dependencies
tar -xzf  geckodriver-v0.31.0-linux64.tar.gz -C  app-dependencies/
curl -LO "https://github.com/mozilla/geckodriver/releases/download/v0.31.0/geckodriver-v0.31.0-linux64.tar.gz"
tar -xzf  geckodriver-v0.31.0-linux64.tar.gz -C  app-dependencies/
curl -LO "https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.16.0/selenium-java-4.16.1.zip"
unzip selenium-java-4.16.1.zip -d app-dependencies/
apt install -y software-properties-common
add-apt-repository ppa:mozillateam/ppa
apt-get update -y
apt remove --autoremove firefox-esr -y
apt remove --autoremove firefox -y
apt install -y firefox-esr
ln -s /usr/bin/firefox-esr /usr/bin/firefox
firefox --version
javac -encoding UTF-8  -cp '.:../app-dependencies/*:../app-dependencies/lib/*' 
java -cp '.:../app-dependencies/*:../app-dependencies/lib/*' SeleniumFirefoxTest --gecko-driver-path '../app-dependencies/geckodriver' --download-url "https://your-url" --download-dir "/tmp" --headless
```
