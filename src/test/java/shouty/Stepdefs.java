package shouty;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Stepdefs {

    private String messageFromSean;
    private Network network;
    private Map<String, Person> people;

    @Before
    public void createNetwork() {
        people = new HashMap<String, Person>();
    }

    @Given("^the range is (\\d+)$")
    public void the_range_is(int range) throws Throwable {
        network = new Network(range);
    }

    @Given("^a person named (\\w+) at location (\\d+)$")
    public void a_person_named(String name, int location) throws Throwable {
        people.put(name, new Person(network, location));
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

    @Then("^Larry does not hear Sean's message$")
    public void larry_does_not_hear_Sean_s_message() throws Throwable {
        List<String> heardByLarry = people.get("Larry").getMessagesHeard();
        assertThat(heardByLarry, not(hasItem(messageFromSean)));
    }
}
