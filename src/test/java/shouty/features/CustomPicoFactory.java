package shouty.features;

import cucumber.runtime.java.picocontainer.PicoFactory;

public class CustomPicoFactory extends PicoFactory {

    public static final boolean WEB = "web".equals(System.getProperty("shouty.testDepth"));

    @Override
    public boolean addClass(Class<?> glueClass) {
        if (ShoutSupport.class.isAssignableFrom(glueClass)) {
            if (WEB) {
                super.addClass(WebShoutSupport.class);
                return glueClass == WebShoutSupport.class;
            } else {
                super.addClass(DomainShoutSupport.class);
                return glueClass == DomainShoutSupport.class;
            }
        }
        return super.addClass(glueClass);
    }
}
