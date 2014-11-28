package shouty;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Stepdefs {

    private Person sean;
    private Person lucy;
    private String messageFromSean;
    private Network network;
    private Map<String,Person> people;

    @Before
    public void createNetwork() {
        network = new Network();
        people = new HashMap<String, Person>();
    }

    @Given("^a person named (\\w+)$")
    public void a_person_named(String name) throws Throwable {
        people.put(name, new Person(network));
    }

    @When("^Sean shouts \"(.*?)\"$")
    public void sean_shouts(String message) throws Throwable {
        people.get("Sean").shout(message);
        messageFromSean = message;
    }

    @Then("^Lucy hears Sean's message$")
    public void lucy_hears_Sean_s_message() throws Throwable {
        assertEquals(asList(messageFromSean), people.get("Lucy").getMessagesHeard());
    }
}
