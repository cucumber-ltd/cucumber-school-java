package shouty;

import java.util.Collection;
import java.util.List;

public class WebShoutSupport implements ShoutSupport {
    @Override
    public void seanShout(String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void assertLucyHearsAllSeansMessages() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCredits(String personName, int credits) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPerson(String personName, Person person) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> messagesHeardBy(String personName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> messagesShoutedBy(String personName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Person> getPeople() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCredits(String personName) {
        throw new UnsupportedOperationException();
    }
}
