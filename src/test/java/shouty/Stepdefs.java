package shouty;

import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import shouty.domain.Shouty;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Stepdefs {

    private final ShoutSupport domainShoutSupport;
    private final ShoutSupport webShoutSupport;
    private ShoutSupport shoutSupport;
    private final Shouty shouty;

    public Stepdefs(Shouty shouty, DomainShoutSupport domainShoutSupport, WebShoutSupport webShoutSupport) {
        this.shouty = shouty;
        this.domainShoutSupport = domainShoutSupport;
        this.webShoutSupport = webShoutSupport;

        // use domain by default
        this.shoutSupport = domainShoutSupport;
    }

    @Before("@web")
    public void switchToWebSupport() {
        this.shoutSupport = webShoutSupport;
    }

    @Given("^the range is (\\d+)$")
    public void the_range_is(int range) throws Throwable {
        shouty.setRange(range);
    }

    public static class Whereabouts {
        public String name;
        public int location;
    }

    @Given("^Sean has bought (\\d+) credits$")
    public void sean_has_bought_credits(int credits) throws Throwable {
        shouty.setCredits("Sean", credits);
    }

    @Given("^the following people:$")
    public void the_following_people(@Transpose List<Whereabouts> whereabouts) throws Throwable {
        for (Whereabouts whereabout : whereabouts) {
            shouty.setPersonLocation(whereabout.name, whereabout.location);
        }
    }

    @When("^Sean shouts a message containing the word \"(.*?)\"$")
    public void sean_shouts_a_message_containing_the_word(String word) throws Throwable {
        shoutSupport.seanShout("a message containing the word " + word);
    }

    @When("^Sean shouts (\\d+) messages containing the word \"(.*?)\"$")
    public void sean_shouts_messages_containing_the_word(int num, String word) throws Throwable {
        for (int j = 0; j < num; j++) {
            shoutSupport.seanShout("a message containing the word " + word);
        }
    }

    @When("^Sean shouts a message$")
    public void sean_shouts_a_message() throws Throwable {
        shoutSupport.seanShout("here is a message");
    }

    @When("^Sean shouts a long message$")
    public void sean_shouts_a_long_message() throws Throwable {
        String longMessage = "";
        for (int i = 0; i < 180; i++) longMessage += "x";
        shoutSupport.seanShout(longMessage);
    }

    @When("^Sean shouts an over-long message$")
    public void sean_shouts_an_over_long_message() throws Throwable {
        String longMessage = "";
        for (int i = 0; i < 181; i++) longMessage += "x";
        shoutSupport.seanShout(longMessage);
    }

    @When("^Sean shouts (\\d+) over-long message$")
    public void sean_shouts_over_long_message(int num) throws Throwable {
        for (int j = 0; j < num; j++) {
            String longMessage = "";
            for (int i = 0; i < 181; i++) longMessage += "x";
            shoutSupport.seanShout(longMessage);
        }
    }

    @When("^Sean shouts \"(.*?)\"$")
    public void sean_shouts(String message) throws Throwable {
        shoutSupport.seanShout(message);
    }

    @When("^Sean shouts:$")
    public void sean_shouts_longer_message(String message) throws Throwable {
        shoutSupport.seanShout(message);
    }

    @Then("^Lucy hears Sean's message$")
    public void lucy_hears_Sean_s_message() throws Throwable {
        shoutSupport.assertLucyHearsAllSeansMessages();
    }

    @Then("^Lucy hears all Sean's messages$")
    public void lucy_hears_all_Sean_s_messages() throws Throwable {
        shoutSupport.assertLucyHearsAllSeansMessages();
    }

    @Then("^Lucy hears the following messages:$")
    public void lucy_hears_the_following_messages(DataTable expectedMessages) throws Throwable {
        List<List<String>> actualMessages = new ArrayList<>();
        List<String> heard = shoutSupport.getMessagesHeardBy("Lucy");
        for (String message : heard) {
            actualMessages.add(asList(message));
        }
        expectedMessages.diff(actualMessages);
    }

    @Then("^Larry does not hear Sean's message$")
    public void larry_does_not_hear_Sean_s_message() throws Throwable {
        List<String> heardByLarry = shoutSupport.getMessagesHeardBy("Larry");
        List<String> messagesFromSean = shoutSupport.getMessagesShoutedBy("Sean");
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        assertThat(heardByLarry, not(hasItems(messagesFromSeanArray)));
    }

    @Then("^nobody hears Sean's message$")
    public void nobody_hears_Sean_s_message() throws Throwable {
        shoutSupport.assertNobodyHearsMessageFrom("Sean");
    }

    @Then("^Sean should have (\\d+) credits$")
    public void sean_should_have_credits(int credits) throws Throwable {
        assertEquals(credits, shouty.getCredits("Sean"));
    }
}
