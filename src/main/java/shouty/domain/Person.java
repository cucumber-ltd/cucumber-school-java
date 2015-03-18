package shouty.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final List<String> messagesShouted = new ArrayList<>();
    private final List<String> messagesHeard = new ArrayList<>();
    private final Network network;
    private final int location;
    private int credits;

    Person(Network network, int location) {
        this.network = network;
        this.location = location;
        this.credits = 0;
        network.subscribe(this);
    }

    public List<String> getMessagesHeard() {
        return messagesHeard;
    }

    public void shout(String message) {
        network.broadcast(message, this);
        messagesShouted.add(message);
    }

    public void hear(String message) {
        messagesHeard.add(message);
    }

    public int getLocation() {
        return location;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

    public List<String> getMessagesShouted() {
        return messagesShouted;
    }
}
