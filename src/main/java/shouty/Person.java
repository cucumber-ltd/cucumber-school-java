package shouty;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int location;

    public void setLocation(int location) {
        this.location = location;
    }

    public void shout(String message) {

    }

    public List<String> getMessagesHeard() {
        List<String> result = new ArrayList<String>();
        result.add("free bagels at Sean's");
        return result;
    }
}
