package shouty.web;

import shouty.domain.Shouty;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ShoutyServer {
    public static void main(String[] args) {
        Shouty shouty = new Shouty();
        shouty.setRange(100);

        shouty.createPerson("Sean", 0);
        shouty.createPerson("Lucy", 100);
        shouty.createPerson("Larry", 150);

        new ShoutyServer(shouty);
    }

    public ShoutyServer(Shouty shouty) {
        get("/", (req, res) -> {
            String personName = req.queryParams("personName");
            Map<String, Object> map = new HashMap<>();
            map.put("messagesHeard", shouty.getMessagesHeardBy(personName));
            map.put("personName", personName);
            return new ModelAndView(map, "index.mustache");
        }, new MustacheTemplateEngine());

        post("/messages", (req, res) -> {
            String personName = req.queryParams("personName");
            String message = req.queryParams("message");
            shouty.shout(personName, message);
            String referer = req.headers("Referer");
            res.redirect(referer);
            return null;
        });
    }

    public void stop() {
        Spark.stop();
    }
}