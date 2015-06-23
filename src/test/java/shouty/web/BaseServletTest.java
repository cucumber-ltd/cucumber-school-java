package shouty.web;

import cucumber.api.java.Before;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

// Base class for conveniently testing a servlet
public abstract class BaseServletTest {
    protected final MockHttpServletRequest req = new MockHttpServletRequest();
    protected final MockHttpServletResponse res = new MockHttpServletResponse();
    protected Document page;
    private HttpServlet servlet;

    @Before
    public HttpServlet getServlet() {
        if (servlet != null) return servlet;
        return servlet = createServlet();
    }

    protected abstract HttpServlet createServlet();

    protected void get(String pathAndQuery) throws ServletException, IOException {
        URI uri = getUri(pathAndQuery);
        req.setRequestURI(uri.getPath());
        req.setQueryString(uri.getQuery());
        req.setMethod("GET");
        doRequest();
    }

    protected void post(String pathAndQuery, Map<String, String> params) throws ServletException, IOException {
        URI uri = getUri(pathAndQuery);
        req.setRequestURI(uri.getPath());
        req.setQueryString(uri.getQuery());
        req.addParameters(params);
        req.setMethod("POST");
        doRequest();
    }

    private void doRequest() throws ServletException, IOException {
        getServlet().service(req, res);
        page = Jsoup.parse(res.getContentAsString());
    }

    private URI getUri(String pathAndQuery) {
        return URI.create("http://host.com" + pathAndQuery);
    }
}