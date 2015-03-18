package shouty;

import java.util.Collection;
import java.util.List;

public interface ShoutSupport {
    void seanShout(String message);

    void assertLucyHearsAllSeansMessages();

    void setCredits(String personName, int credits);

    void addPerson(String personName, Person person);

    List<String> messagesHeardBy(String personName);

    List<String> messagesShoutedBy(String personName);

    Collection<Person> getPeople();

    int getCredits(String personName);
}
