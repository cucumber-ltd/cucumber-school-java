package shouty.features;

import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserSessions {
    private Map<String, WebDriver> browsers = new HashMap<>();

    public WebDriver getBrowserFor(String personName) {
        WebDriver browser = browsers.get(personName);
        if (browser == null) {
            browser = new FirefoxDriver();
            browsers.put(personName, browser);
        }
        return browser;
    }

    @After
    public void closeAll() {
        for (WebDriver browser : browsers.values()) {
            browser.close();
        }
    }
}
