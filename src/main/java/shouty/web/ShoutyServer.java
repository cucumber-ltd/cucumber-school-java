package shouty.web;

import spark.Spark;

import java.io.Console;

import static spark.Spark.get;
import static spark.Spark.post;

public class ShoutyServer {
    public ShoutyServer() {
        get("/", (req, res) -> "<html><body><form action='/messages' method=post><input type=text id=message name=message></form></body></html>");
        post("/messages", (req, res) -> {
            String message = req.queryParams("message");
            // TODO: shout it
            res.redirect("/");
            return null;
        });
    }

    public void stop() {
        Spark.stop();
    }
}