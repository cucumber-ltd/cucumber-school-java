package shouty;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NetworkTest {
    private int range = 100;
    private Network network = new Network(range);
    private String message = "Free bagels!";

    @Test
    public void broadcasts_a_message_to_a_listener_within_range() {
        int seanLocation = 0;
        Person lucy = mock(Person.class);
        network.subscribe(lucy);
        network.broadcast(message, seanLocation);

        verify(lucy).hear(message);
    }

    @Test
    public void does_not_broadcast_a_message_to_a_litener_out_of_range() {
        int seanLocation = 0;
        Person laura = mock(Person.class);
        when(laura.getLocation()).thenReturn(101);
        network.subscribe(laura);
        network.broadcast(message, seanLocation);

        verify(laura, never()).hear(message);
    }

    @Test
    public void does_not_broadcast_a_message_to_a_litener_out_of_range_negative_distance() {
        int sallyLocation = 101;
        Person lionel = mock(Person.class);
        when(lionel.getLocation()).thenReturn(0);
        network.subscribe(lionel);
        network.broadcast(message, sallyLocation);

        verify(lionel, never()).hear(message);
    }

    @Test
    public void does_not_broadcast_messages_longer_than_180_chars_even_within_range() {
        int seanLocation = 0;
        Person lucy = mock(Person.class);
        when(lucy.getLocation()).thenReturn(100);
        network.subscribe(lucy);
        String longMessage = "";
        for (int i = 0; i < 181; i++) {
            longMessage += "x";
        }
        network.broadcast(longMessage, seanLocation);

        verify(lucy, never()).hear(longMessage);
    }
}
