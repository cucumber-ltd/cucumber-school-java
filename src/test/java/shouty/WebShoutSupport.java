package shouty;

import org.openqa.selenium.WebDriver;

public class WebShoutSupport extends ShoutSupport {

    private final BrowserSessions browsers;

    public WebShoutSupport(BrowserSessions browsers) {
        this.browsers = browsers;
    }

    @Override
    public void seanShout(String message) {
        loginAs("Sean");
        shout(message);
        rememberMessageShoutedBy(message, "Sean");
    }

    private void loginAs(String personName) {
        WebDriver browser = browsers.getBrowserFor(personName);
        browser.get("http://localhost:5001/?name=" + personName);
    }

    private void shout(String message) {
        throw new UnsupportedOperationException();
    }
}
