/*
- Download the Selemium webdriver jars https://www.selenium.dev/downloads/
- Download the Firefox gecko driver https://github.com/mozilla/geckodriver/releases
- Create a dir for the project and add the uncompress jars folder and the geckodriver executable in it
- Compile
javac -cp '.:./selenium-java-4.16.1/*:./selenium-java-4.16.1/lib/*' SeleniumFirefoxTest.java
- Run
java -cp '.:./selenium-java-4.16.1/*:./selenium-java-4.16.1/lib/*' SeleniumFirefoxTest
*/
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.HashMap;
import java.util.Map;

public class SeleniumFirefoxTest {
    public static void main(String[] args) {
        // Parse command-line arguments into a map for easy access
        Map<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            arguments.put(args[i], args.length > i + 1 ? args[i + 1] : null);
        }

        // Check for mandatory --gecko-driver-path argument
        String geckoDriverPath = arguments.get("--gecko-driver-path");
        if (geckoDriverPath == null || geckoDriverPath.isEmpty()) {
            System.out.println("Error: The --gecko-driver-path argument is mandatory.");
            System.exit(1);
        }

        // Check for mandatory --download-url argument
        String downloadUrl = arguments.get("--download-url");
        if (downloadUrl == null || downloadUrl.isEmpty()) {
            System.out.println("Error: The --download-url argument is mandatory.");
            System.exit(1);
        }

        // Check for optional --headless flag
        boolean isHeadless = arguments.containsKey("--headless");

        // Set the path to the GeckoDriver
        System.setProperty("webdriver.gecko.driver", geckoDriverPath);

        // Set Firefox to automatically download the file type
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", "/path/to/download");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream"); // MIME type of your file

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        if (isHeadless) {
            options.addArguments("--headless");
        }

        WebDriver driver = new FirefoxDriver(options);
        try {
            // Open a blank page
            driver.get("about:blank");

            // Use JavaScript to inject an HTML link
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "var link = document.createElement('a');" +
                "link.href = arguments[0];" +
                "link.textContent = 'Download Link';" +
                "document.body.appendChild(link);", 
                downloadUrl
            );

            // Click the link
            WebElement downloadLink = driver.findElement(By.linkText("Download Link"));
            downloadLink.click();

            // Add a wait or delay as needed

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

