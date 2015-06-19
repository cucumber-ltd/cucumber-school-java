package shouty;

public class WebShoutSupport extends ShoutSupport {
    @Override
    public void seanShout(String message) {
        loginAs("Sean");
        shout(message);
        rememberMessageShoutedBy(message, "Sean");
    }

    private void loginAs(String personName) {
        throw new UnsupportedOperationException();
    }

    private void shout(String message) {
        throw new UnsupportedOperationException();
    }
}
