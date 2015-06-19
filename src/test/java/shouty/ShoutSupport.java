package shouty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShoutSupport {
    private final Map<String, Person> people = new HashMap<String, Person>();
    protected final Map<String, List<String>> messagesShoutedBy = new HashMap<String, List<String>>();

    public abstract void seanShout(String message);

    public Map<String, Person> getPeople() {
        return people;
    }

    public Map<String, List<String>> getMessagesShoutedBy() {
        return messagesShoutedBy;
    }

    protected void rememberMessageShoutedBy(String message, String personName) {
        List<String> messages = getMessagesShoutedBy().get(personName);
        if (messages == null) {
            messages = new ArrayList<String>();
            getMessagesShoutedBy().put(personName, messages);
        }
        messages.add(message);
    }
}
