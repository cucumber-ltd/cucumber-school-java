package shouty.features;

import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserSessions {
    private Map<String, WebDriver> browsers = new HashMap<>();

    public WebDriver getBrowserFor(String personName) {
        if (!browsers.containsKey(personName)) {
            browsers.put(personName, new FirefoxDriver());
        }
        return browsers.get(personName);
    }

    @After
    public void closeAll() {
        for (WebDriver browser : browsers.values()) {
            browser.close();
        }
    }
}
