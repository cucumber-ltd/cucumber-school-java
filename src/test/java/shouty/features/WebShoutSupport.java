package shouty.features;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import shouty.web.ShoutyServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebShoutSupport extends ShoutSupport {

    private Map<String, WebDriver> browsers = new HashMap<>();
    private WebDriver currentBrowser;
    private ShoutyServer server = new ShoutyServer();

    @Before
    public void startServer() throws Exception {
        server.start(getPeople(), 4567);
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }

    @After
    public void closeAllBrowsers() {
        for (WebDriver browser : browsers.values()) {
            browser.close();
        }
    }

    @Override
    public void seanShout(String message) {
        loginAs("Sean");
        shout(message);
        rememberMessageShoutedBy(message, "Sean");
    }

    @Override
    public List<String> getMessagesHeardBy(String listenerName) {
        loginAs(listenerName);
        List<WebElement> elements = currentBrowser.findElements(By.cssSelector(".message"));
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
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








    private class FirefoxDriver extends org.openqa.selenium.firefox.FirefoxDriver {
        public FirefoxDriver() {
            int w = 1274;
            int h = 714;
            int x = 84; //322;
            int y = 90; //-898;
            this.manage().window().setSize(new Dimension(w/2, h));
            this.manage().window().setPosition(new Point(x + (w/2), y));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
