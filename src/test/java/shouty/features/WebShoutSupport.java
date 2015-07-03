package shouty.features;

import cucumber.api.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import shouty.web.ShoutyServer;

import java.util.HashMap;
import java.util.Map;

public class WebShoutSupport extends ShoutSupport {

    private Map<String, WebDriver> browsers = new HashMap<>();
    private WebDriver currentBrowser;
    private ShoutyServer server = new ShoutyServer();

    @Override
    public void beforeScenario() throws Exception {
        server.start(getPeople(), 4567);
    }

    @Override
    public void afterScenario() throws Exception {
        server.stop();
        closeAllBrowsers();
    }

    @Override
    public void seanShout(String message) {
        loginAs("Sean");
        shout(message);
        rememberMessageShoutedBy(message, "Sean");
    }

    private void loginAs(String personName) {
        currentBrowser = getBrowserFor(personName);
        currentBrowser.get("http://localhost:4567/?name=" + personName);
    }

    private void shout(String message) {
        currentBrowser.findElement(By.name("message")).sendKeys(message);
        currentBrowser.findElement(By.cssSelector("button[type=submit]")).click();
    }

    private WebDriver getBrowserFor(String personName) {
        if (!browsers.containsKey(personName)) {
            browsers.put(personName, new FirefoxDriver());
        }
        return browsers.get(personName);
    }

    private void closeAllBrowsers() {
        for (WebDriver browser : browsers.values()) {
            browser.close();
        }
    }

}
