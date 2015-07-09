package shouty.features;

import cucumber.runtime.java.picocontainer.PicoFactory;

public class CustomPicoFactory extends PicoFactory {
    public CustomPicoFactory() {
        if("web".equals(System.getProperty("shouty.testDepth"))) {
            addClass(WebShoutSupport.class);
        } else {
            addClass(DomainShoutSupport.class);
        }
    }
}
