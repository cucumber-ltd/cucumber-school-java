package shouty.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Facade that hides most of the internal domain logic
 */
public class Shouty {
    private final Map<String, Person> people = new HashMap<>();
    private Network network;

    public void setRange(int range) {
        this.network = new Network(range);
    }

    public void shout(String personName, String message) {
        people.get(personName).shout(message);
    }

    public List<String> getMessagesHeardBy(String personName) {
        return people.get(personName).getMessagesHeard();
    }

    public void setCredits(String personName, int credits) {
        people.get(personName).setCredits(credits);
    }

    public void addPerson(String personName, int location) {
        people.put(personName, new Person(network, location));
    }

    public int getCredits(String personName) {
        return people.get(personName).getCredits();
    }

    public Collection<Person> getPeople() {
        return people.values();
    }

    public List<String> getMessagesShoutedBy(String personName) {
        return people.get(personName).getMessagesShouted();
    }
}
