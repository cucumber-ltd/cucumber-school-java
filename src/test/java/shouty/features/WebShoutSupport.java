package shouty.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import shouty.web.ShoutyServer;

public class WebShoutSupport extends ShoutSupport {

    private final BrowserSessions browsers;
    private WebDriver browser;
    private ShoutyServer server = new ShoutyServer();

    public WebShoutSupport(BrowserSessions browsers) {
        this.browsers = browsers;
    }

    @Override
    public void seanShout(String message) {
        loginAs("Sean");
        shout(message);
        rememberMessageShoutedBy(message, "Sean");
    }

    @Override
    public void before() throws Exception {
        server.start(getPeople(), 4567);
    }

    @Override
    public void after() throws Exception {
        server.stop();
    }

    private void loginAs(String personName) {
        browser = browsers.getBrowserFor(personName);
        browser.get("http://localhost:4567/?name=" + personName);
    }

    private void shout(String message) {
        browser.findElement(By.name("message")).sendKeys(message);
        browser.findElement(By.cssSelector("button[type=submit]")).click();
    }
}
