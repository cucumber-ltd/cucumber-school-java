package shouty;

import java.util.List;

public interface ShoutSupport {
    void seanShout(String message);

    void assertLucyHearsAllSeansMessages();

    void setCredits(String personName, int credits);

    void addPerson(String personName, int person);

    List<String> messagesHeardBy(String personName);

    List<String> messagesShoutedBy(String personName);

    int getCredits(String personName);

    void assertNobodyHearsMessageFrom(String personName);

    void setRange(int range);
}
