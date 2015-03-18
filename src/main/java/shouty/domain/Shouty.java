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
        findOrCreatePerson(personName).shout(message);
    }

    public List<String> getMessagesHeardBy(String personName) {
        return findOrCreatePerson(personName).getMessagesHeard();
    }

    public void setCredits(String personName, int credits) {
        findOrCreatePerson(personName).setCredits(credits);
    }

    public void setPersonLocation(String personName, int location) {
        findOrCreatePerson(personName).setLocation(location);
    }

    public int getCredits(String personName) {
        return findOrCreatePerson(personName).getCredits();
    }

    public Collection<Person> getPeople() {
        return people.values();
    }

    public List<String> getMessagesShoutedBy(String personName) {
        return findOrCreatePerson(personName).getMessagesShouted();
    }

    private Person findOrCreatePerson(String personName) {
        Person person = people.get(personName);
        if(person == null) {
            person = new Person(network, 0);
            people.put(personName, person);
        }
        return person;
    }
}
