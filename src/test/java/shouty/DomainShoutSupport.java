package shouty;

import shouty.domain.Person;
import shouty.domain.Shouty;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class DomainShoutSupport implements ShoutSupport {
    private final Map<String, List<String>> messagesShoutedBy = new HashMap<String, List<String>>();
    private Shouty shouty;

    @Override
    public void seanShout(String message) {
        shouty.shout("Sean", message);
        List<String> messages = messagesShoutedBy.get("Sean");
        if (messages == null) {
            messages = new ArrayList<String>();
            messagesShoutedBy.put("Sean", messages);
        }
        messages.add(message);
    }

    @Override
    public void assertLucyHearsAllSeansMessages() {
        List<String> heardByLucy = shouty.getMessagesHeardBy("Lucy");
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");

        // Hamcrest's hasItems matcher wants an Array, not a List.
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        assertThat(heardByLucy, hasItems(messagesFromSeanArray));
    }

    @Override
    public void setCredits(String personName, int credits) {
        shouty.setCredits(personName, credits);
    }

    @Override
    public void addPerson(String personName, int location) {
        shouty.addPerson(personName, location);
    }

    @Override
    public List<String> messagesHeardBy(String personName) {
        return shouty.getMessagesHeardBy(personName);
    }

    @Override
    public List<String> messagesShoutedBy(String personName) {
        return messagesShoutedBy.get(personName);
    }

    @Override
    public int getCredits(String personName) {
        return shouty.getCredits(personName);
    }

    @Override
    public void assertNobodyHearsMessageFrom(String personName) {
        List<String> messagesFromSean = messagesShoutedBy("Sean");
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        for (Person person : shouty.getPeople()) {
            assertThat(person.getMessagesHeard(), not(hasItems(messagesFromSeanArray)));
        }
    }

    @Override
    public void setRange(int range) {
        shouty = new Shouty(range);
    }
}
