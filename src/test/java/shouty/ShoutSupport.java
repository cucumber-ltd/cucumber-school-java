package shouty;

import cucumber.api.java.Before;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class ShoutSupport {
    public final Map<String, Person> people = new HashMap<String, Person>();
    public final Map<String, List<String>> messagesShoutedBy = new HashMap<String, List<String>>();

    public void seanShout(String message) {
        people.get("Sean").shout(message);
        List<String> messages = messagesShoutedBy.get("Sean");
        if (messages == null) {
            messages = new ArrayList<String>();
            messagesShoutedBy.put("Sean", messages);
        }
        messages.add(message);
    }

    public void assertLucyHearsAllSeansMessages() {
        List<String> heardByLucy = people.get("Lucy").getMessagesHeard();
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");

        // Hamcrest's hasItems matcher wants an Array, not a List.
        String[] messagesFromSeanArray = messagesFromSean.toArray(new String[messagesFromSean.size()]);
        assertThat(heardByLucy, hasItems(messagesFromSeanArray));
    }
}
