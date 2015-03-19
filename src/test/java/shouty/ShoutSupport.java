package shouty;

import java.util.List;

public interface ShoutSupport {
    void seanShout(String message);

    void assertLucyHearsAllSeansMessages();

    List<String> getMessagesHeardBy(String personName);

    void assertNobodyHearsMessageFrom(String personName);
}
