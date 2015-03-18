package shouty;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class DomainShoutSupport implements ShoutSupport {
    private final Map<String, Person> people = new HashMap<String, Person>();
    private final Map<String, List<String>> messagesShoutedBy = new HashMap<String, List<String>>();

    @Override
    public void seanShout(String message) {
        people.get("Sean").shout(message);
        List<String> messages = messagesShoutedBy.get("Sean");
        if (messages == null) {
            messages = new ArrayList<String>();
            messagesShoutedBy.put("Sean", messages);
        }
        messages.add(message);
    }

    @Override
    public void assertLucyHearsAllSeansMessages() {
        List<String> heardByLucy = people.get("Lucy").getMessagesHeard();
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");

        // Hamcrest's hasItems matcher wants an Array, not a List.
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        assertThat(heardByLucy, hasItems(messagesFromSeanArray));
    }

    @Override
    public void setCredits(String personName, int credits) {
        people.get(personName).setCredits(credits);
    }

    @Override
    public void addPerson(String personName, Person person) {
        people.put(personName, person);
    }

    @Override
    public List<String> messagesHeardBy(String personName) {
        return people.get(personName).getMessagesHeard();
    }

    @Override
    public List<String> messagesShoutedBy(String personName) {
        return messagesShoutedBy.get(personName);
    }

    @Override
    public Collection<Person> getPeople() {
        return people.values();
    }

    @Override
    public int getCredits(String personName) {
        return people.get(personName).getCredits();
    }
}
