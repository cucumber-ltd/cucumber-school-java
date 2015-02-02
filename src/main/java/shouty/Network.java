package shouty;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private final List<Person> listeners = new ArrayList<Person>();
    private final int range;

    public Network(int range) {
        this.range = range;
    }

    public void subscribe(Person person) {
        listeners.add(person);
    }

    public void broadcast(String message, Person shouter) {
        int shouterLocation = shouter.getLocation();
        boolean shortEnough = message.length() <= 180;
        deductCredits(shortEnough, message, shouter);
        for (Person listener : listeners) {
            boolean withinRange = Math.abs(listener.getLocation() - shouterLocation) <= range;
            if (withinRange && (shortEnough || shouter.getCredits() >= 0)) {
                listener.hear(message);
            }
        }
    }

    private void deductCredits(boolean shortEnough, String message, Person shouter) {
        if (!shortEnough) {
            shouter.setCredits(shouter.getCredits() - 2);
        }
        if (message.toLowerCase().contains("buy")) {
            shouter.setCredits(shouter.getCredits() - 5);
        }
    }
}
