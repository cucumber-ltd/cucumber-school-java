package shouty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import shouty.domain.Shouty;
import shouty.web.BrowserSessions;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class WebShoutSupport implements ShoutSupport {
    private final Shouty shouty;
    private final BrowserSessions browserSessions;

    public WebShoutSupport(Shouty shouty, BrowserSessions browserSessions) {
        this.shouty = shouty;
        this.browserSessions = browserSessions;
    }

    @Override
    public void seanShout(String message) {
        WebDriver browser = browserSessions.getBrowser("Sean");
        browser.get("http://localhost:4567/?personName=Sean");
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
    public List<String> getMessagesHeardBy(String personName) {
        return shouty.getMessagesHeardBy(personName);
    }

    @Override
    public List<String> getMessagesShoutedBy(String personName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void assertNobodyHearsMessageFrom(String personName) {
        throw new UnsupportedOperationException();
    }
}
