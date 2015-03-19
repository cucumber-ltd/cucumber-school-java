package shouty.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ShoutyTest {
    @Test
    public void throws_nice_error_when_person_not_found() {
        Shouty shouty = new Shouty();
        shouty.setRange(100);

        shouty.createPerson("Bob", 10);
        try {
            shouty.getMessagesHeardBy("doesnt-exist");
            fail();
        } catch (RuntimeException expected) {
            assertEquals("No such person: doesnt-exist. Known people: [Bob]", expected.getMessage());
        }
    }
}
