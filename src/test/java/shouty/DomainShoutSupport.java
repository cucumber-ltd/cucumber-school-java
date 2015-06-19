package shouty;

public class DomainShoutSupport extends ShoutSupport {

    @Override
    public void seanShout(String message) {
        getPeople().get("Sean").shout(message);
        String personName = "Sean";
        rememberMessageShoutedBy(message, personName);
    }

}
