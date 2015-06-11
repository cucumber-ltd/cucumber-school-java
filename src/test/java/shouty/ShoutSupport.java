package shouty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
