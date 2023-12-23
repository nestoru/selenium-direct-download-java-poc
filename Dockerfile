# Use a Debian-based official OpenJDK runtime as the base image
FROM openjdk:11-jdk-slim

# Create a directory for the app and another for the dependencies
WORKDIR /usr/src/app
RUN mkdir /usr/src/app-dependencies

# Install Firefox and other necessary utilities
RUN apt-get update && \
    apt-get install -y firefox-esr wget tar unzip && \
    apt-get clean

# Download and install GeckoDriver in the app-dependencies directory
RUN wget -q "https://github.com/mozilla/geckodriver/releases/download/v0.31.0/geckodriver-v0.31.0-linux64.tar.gz" -O /usr/src/app-dependencies/geckodriver.tar.gz && \
    tar -xzf /usr/src/app-dependencies/geckodriver.tar.gz -C /usr/src/app-dependencies && \
    rm /usr/src/app-dependencies/geckodriver.tar.gz

# Download and unzip Selenium Java bindings in the app-dependencies directory
RUN wget -q "https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.16.0/selenium-java-4.16.1.zip" -O /usr/src/app-dependencies/selenium-java.zip && \
    unzip /usr/src/app-dependencies/selenium-java.zip -d /usr/src/app-dependencies && \
    rm /usr/src/app-dependencies/selenium-java.zip

