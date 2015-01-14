package shouty;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Transpose;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en_lol.DEN;
import cucumber.api.java.en_lol.ICANHAZ;
import cucumber.api.java.en_lol.WEN;

import java.util.ArrayList;
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

    public static class Whereabouts {
        public String name;
        public int location;
    }

    @Given("^the following people:$")
    public void the_following_people(@Transpose List<Whereabouts> whereabouts) throws Throwable {
        for (Whereabouts whereabout : whereabouts) {
            people.put(whereabout.name, new Person(network, whereabout.location));
        }
    }

    @When("^Sean shouts \"(.*?)\"$")
    public void sean_shouts(String message) throws Throwable {
        people.get("Sean").shout(message);
        messageFromSean = message;
    }

    @When("^Sean shouts:$")
    public void sean_shouts_longer_message(String message) throws Throwable {
        people.get("Sean").shout(message);
        messageFromSean = message;
    }

    @Then("^Lucy hears Sean's message$")
    public void lucy_hears_Sean_s_message() throws Throwable {
        assertEquals(asList(messageFromSean), people.get("Lucy").getMessagesHeard());
    }

    @Then("^Lucy hears the following messages:$")
    public void lucy_hears_the_following_messages(DataTable expectedMessages) throws Throwable {
        List<List<String>> actualMessages = new ArrayList<List<String>>();
        List<String> heard = people.get("Lucy").getMessagesHeard();
        for (String message : heard) {
            actualMessages.add(asList(message));
        }
        expectedMessages.diff(actualMessages);
    }

    @Then("^Larry does not hear Sean's message$")
    public void larry_does_not_hear_Sean_s_message() throws Throwable {
        List<String> heardByLarry = people.get("Larry").getMessagesHeard();
        assertThat(heardByLarry, not(hasItem(messageFromSean)));
    }

    @Then("^nobody hears Sean's message$")
    public void nobody_hears_Sean_s_message() throws Throwable {
        for (Person person : people.values()) {
            assertThat(person.getMessagesHeard(), not(hasItem(messageFromSean)));
        }
    }

    @ICANHAZ("^IN TEH BEGINNIN (\\d+) CUCUMBRZ$")
    public void in_TEH_BEGINNIN_CUCUMBRZ(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @WEN("^I EAT (\\d+) CUCUMBRZ$")
    public void i_EAT_CUCUMBRZ(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @DEN("^I HAS (\\d+) CUCUMBERZ IN MAH BELLY$")
    public void i_HAS_CUCUMBERZ_IN_MAH_BELLY(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @DEN("^IN TEH END (\\d+) CUCUMBRZ KTHXBAI$")
    public void in_TEH_END_CUCUMBRZ_KTHXBAI(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
