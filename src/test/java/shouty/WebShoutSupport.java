package shouty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import shouty.domain.Shouty;
import shouty.web.BrowserSessions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class WebShoutSupport implements ShoutSupport {
    private Shouty shouty;
    private final BrowserSessions browserSessions;

    public WebShoutSupport(BrowserSessions browserSessions) {
        this.browserSessions = browserSessions;
    }

    @Override
    public void seanShout(String message) {
        WebDriver browser = browserSessions.getBrowser("Sean");
        browser.get("http://localhost:4567/?name=Sean");
        WebElement messageField = browser.findElement(By.id("message"));
        messageField.sendKeys(message);
        messageField.submit();
    }

    @Override
    public void assertLucyHearsAllSeansMessages() {
        WebDriver browser = browserSessions.getBrowser("Lucy");
        List<WebElement> messageElements = browser.findElements(By.cssSelector("#messages li"));
        List<String> heardByLucy = messageElements.stream().map(WebElement::getText).collect(Collectors.toList());

        List<String> messagesFromSean = shouty.getMessagesShoutedBy("Sean");
        // Hamcrest's hasItems matcher wants an Array, not a List.
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        assertThat(heardByLucy, hasItems(messagesFromSeanArray));
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
