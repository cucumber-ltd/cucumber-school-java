package shouty;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Stepdefs {

    private Person sean;
    private Person lucy;
    private String messageFromSean;

    @Given("^Lucy is (\\d+)m from Sean$")
    public void lucy_is_located_m_from_Sean(int distance) throws Throwable {
        Network network = new Network();
        sean = new Person(network);
        lucy = new Person(network);
        lucy.moveTo(distance);
    }

    @When("^Sean shouts \"(.*?)\"$")
    public void sean_shouts(String message) throws Throwable {
        sean.shout(message);
        messageFromSean = message;
    }

    @Then("^Lucy hears Sean's message$")
    public void lucy_hears_Sean_s_message() throws Throwable {
        assertEquals(asList(messageFromSean), lucy.getMessagesHeard());
    }
}
