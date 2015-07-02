package shouty.web;

import org.junit.Before;
import org.junit.Test;
import shouty.core.Person;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShoutyServletTest extends BaseServletTest {
    private final HashMap<String, Person> people = new HashMap<String, Person>(){{
        put("Sean", mock(Person.class));
    }};

    @Test
    public void getShouldRespondWithHomepageForKnownUser() throws Exception {
        get("/?name=Sean");
        assertEquals(200, res.getStatus());
    }

    @Test
    public void getShouldRespondWith401WhenUserNotRecognised() throws Exception {
        get("/?name=Unknown");
        assertEquals(401, res.getStatus());
        assertThat(res.getErrorMessage(), containsString("Unauthorized"));
    }

    @Test
    public void getRendersAFormForPostingMessagesOnTheHomepage() throws Exception {
        get("/?name=Sean");
        assertNotNull(page.select("form [name=message]"));
        assertNotNull(page.select("form [type=submit]"));
    }

    @Test
    public void postShoutsMessageFromTheGivenUser() throws Exception {
        Person sean = people.get("Sean");
        Map<String, String> params = new HashMap<>();
        params.put("message", "Test message");
        post("/shout?name=Sean", params);
        verify(sean).shout("Test message");
    }

    @Test
    public void postRedirectsBackToHomePageKeepingUserLoggedIn() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("message", "Test message");
        post("/shout?name=Sean", params);
        assertEquals(302, res.getStatus());
        assertEquals("/?name=Sean", res.getRedirectedUrl());
    }

    @Override
    protected ShoutyServlet createServlet() {
        return new ShoutyServlet(people);
    }
}