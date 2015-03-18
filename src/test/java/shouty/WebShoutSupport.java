package shouty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import shouty.domain.Shouty;

import java.util.List;

public class WebShoutSupport implements ShoutSupport {
    private Shouty shouty;

    @Override
    public void seanShout(String message) {
        WebDriver browser = new FirefoxDriver();
        browser.get("http://localhost:5001/");
    }

    @Override
    public void assertLucyHearsAllSeansMessages() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCredits(String personName, int credits) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPerson(String personName, int location) {
        shouty.addPerson(personName, location);
    }

    @Override
    public List<String> messagesHeardBy(String personName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> messagesShoutedBy(String personName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCredits(String personName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void assertNobodyHearsMessageFrom(String personName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRange(int range) {
        shouty = new Shouty(range);
    }
}
