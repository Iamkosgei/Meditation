import org.apache.log4j.BasicConfigurator;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        BasicConfigurator.configure();

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        get("/forum", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("forums", Forum.all() );
            model.put("template", "templates/forum.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        get("/set-timer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/set-timer.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        get("/time/:select-time", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String time = req.params("select-time");
            model.put("time",time);
          model.put("template","templates/post-to-forum.vtl");

            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        post("/forum", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String email = req.queryParams("email");
            String name = req.queryParams("name");
            String message = req.queryParams("message");
            int time = Integer.parseInt(req.queryParams("time"));


            Forum forum = new Forum(name,time,message,email);
            forum.save();



            res.redirect("/forum");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

    }
}
