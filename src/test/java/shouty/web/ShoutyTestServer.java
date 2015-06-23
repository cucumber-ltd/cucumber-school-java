package shouty.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import shouty.core.Network;
import shouty.core.Person;

import java.util.HashMap;
import java.util.Map;

public class ShoutyTestServer {
    private Server server;

    public static void main(String[] args) throws Exception {
        Network network = new Network(100);
        Map<String, Person> people = new HashMap<String, Person>() {{
            put("Sean", new Person(network, 0));
            put("Lucy", new Person(network, 100));
            put("Larry", new Person(network, 150));
        }};
        new ShoutyTestServer().start(people, 8080);
    }

    public void start(Map<String, Person> people, int port) throws Exception {
        ServletHandler handler = new ServletHandler();
        ShoutyServlet shoutyServlet = new ShoutyServlet(people);
        handler.addServletWithMapping(new ServletHolder(shoutyServlet), "/*");
        server = new Server(port);
        server.setHandler(handler);
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
        server.join();
    }
}
