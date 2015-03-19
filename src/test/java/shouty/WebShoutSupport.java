package shouty;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import shouty.domain.Person;
import shouty.domain.Shouty;
import shouty.web.BrowserSessions;
import shouty.web.ShoutyServer;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class WebShoutSupport implements ShoutSupport {
    private final Shouty shouty;
    private final BrowserSessions browserSessions;
    private ShoutyServer server;

    public WebShoutSupport(Shouty shouty, BrowserSessions browserSessions) {
        this.shouty = shouty;
        this.browserSessions = browserSessions;
    }

    public void startServer() {
        server = new ShoutyServer(shouty);
    }

    @After("@web")
    public void stopWebServer() {
        if (server != null)
            server.stop();
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
        assertThat(getMessagesHeardBy("Lucy"), hasItems(messagesShoutedBy("Sean")));
    }

    @Override
    public List<String> getMessagesHeardBy(String personName) {
        WebDriver browser = browserSessions.getBrowser(personName);
        browser.get("http://localhost:4567/?personName=" + personName);
        List<WebElement> messageElements = browser.findElements(By.cssSelector("#messages li"));
        return messageElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public void assertNobodyHearsMessageFrom(String personName) {
        for (Person person : shouty.getPeople()) {
            assertThat(getMessagesHeardBy(person.getName()), not(hasItems(messagesShoutedBy("Sean"))));
        }
    }

    // Hamcrest's hasItems matcher wants an Array, not a List.
    private String[] messagesShoutedBy(String shouterName) {
        List<String> messagesFromSean = shouty.getMessagesShoutedBy(shouterName);
        return messagesFromSean.toArray(new String[messagesFromSean.size()]);
    }

}
