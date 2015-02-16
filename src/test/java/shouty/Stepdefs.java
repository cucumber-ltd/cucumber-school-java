package shouty;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Transpose;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Stepdefs {

    private Network network;
    private Map<String, Person> people;
    private Map<String, List<String>> messagesShoutedBy;

    @Before
    public void setup() {
        people = new HashMap<String, Person>();
        messagesShoutedBy = new HashMap<String, List<String>>();
    }

    @Given("^the range is (\\d+)$")
    public void the_range_is(int range) throws Throwable {
        network = new Network(range);
    }

    public static class Whereabouts {
        public String name;
        public int location;
    }

    @Given("^Sean has bought (\\d+) credits$")
    public void sean_has_bought_credits(int credits) throws Throwable {
        people.get("Sean").setCredits(credits);
    }

    @Given("^the following people:$")
    public void the_following_people(@Transpose List<Whereabouts> whereabouts) throws Throwable {
        for (Whereabouts whereabout : whereabouts) {
            people.put(whereabout.name, new Person(network, whereabout.location));
        }
    }

    @When("^Sean shouts a message containing the word \"(.*?)\"$")
    public void sean_shouts_a_message_containing_the_word(String word) throws Throwable {
        shout("a message containing the word " + word);
    }

    @When("^Sean shouts \"(.*?)\"$")
    public void sean_shouts(String message) throws Throwable {
        shout(message);
    }

    @When("^Sean shouts:$")
    public void sean_shouts_longer_message(String message) throws Throwable {
        shout(message);
    }

    private void shout(String message) {
        people.get("Sean").shout(message);
        List<String> messages = messagesShoutedBy.get("Sean");
        if (messages == null) {
            messages = new ArrayList<String>();
            messagesShoutedBy.put("Sean", messages);
        }
        messages.add(message);
    }

    @Then("^Lucy hears Sean's message$")
    public void lucy_hears_Sean_s_message() throws Throwable {
        lucy_hears_all_Sean_s_messages();
    }

    @Then("^Lucy hears all Sean's messages$")
    public void lucy_hears_all_Sean_s_messages() throws Throwable {
        List<String> heardByLucy = people.get("Lucy").getMessagesHeard();
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");

        // Hamcrest's hasItems matcher wants an Array, not a List.
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        assertThat(heardByLucy, hasItems(messagesFromSeanArray));
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
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        assertThat(heardByLarry, not(hasItems(messagesFromSeanArray)));
    }

    @Then("^nobody hears Sean's message$")
    public void nobody_hears_Sean_s_message() throws Throwable {
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        for (Person person : people.values()) {
            assertThat(person.getMessagesHeard(), not(hasItems(messagesFromSeanArray)));
        }
    }

    @Then("^Sean should have (\\d+) credits$")
    public void sean_should_have_credits(int credits) throws Throwable {
        assertEquals(credits, people.get("Sean").getCredits());
    }
}
