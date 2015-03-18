package shouty.web;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class WebServerHooks {

    private ShoutyServer server;

    @Before("@web")
    public void startWebServer() {
        server = new ShoutyServer();
    }

    @After("@web")
    public void stopWebServer() {
        server.stop();
    }
}
