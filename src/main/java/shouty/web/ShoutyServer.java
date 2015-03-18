package shouty.web;

import spark.Spark;
import static spark.Spark.get;

public class ShoutyServer {
    public ShoutyServer() {
        get("/", (req, res) -> "Hello World");
    }

    public void stop() {
        Spark.stop();
    }
}