package shouty.web;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import shouty.domain.Shouty;

public class WebServerHooks {

    private final Shouty shouty;
    private ShoutyServer server;

    public WebServerHooks(Shouty shouty) {
        this.shouty = shouty;
    }

    @Before("@web")
    public void startWebServer() {
        server = new ShoutyServer(shouty);
    }

    @After("@web")
    public void stopWebServer() {
        server.stop();
    }
}
