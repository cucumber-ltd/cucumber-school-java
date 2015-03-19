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
        findPerson(personName).shout(message);
    }

    public List<String> getMessagesHeardBy(String personName) {
        return findPerson(personName).getMessagesHeard();
    }

    public void setCredits(String personName, int credits) {
        findPerson(personName).setCredits(credits);
    }

    public int getCredits(String personName) {
        return findPerson(personName).getCredits();
    }

    public Collection<Person> getPeople() {
        return people.values();
    }

    public List<String> getMessagesShoutedBy(String personName) {
        return findPerson(personName).getMessagesShouted();
    }

    private Person findPerson(String personName) {
        Person person = people.get(personName);
        if(person == null) {
            throw new RuntimeException("No such person: " + personName + ". Known people: " + people.keySet());
        }
        return person;
    }

    public void createPerson(String personName, int location) {
        people.put(personName, new Person(network, location, personName));
    }
}
