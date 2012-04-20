package org.mhoffman.life;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import play.*;
import play.api.mvc.AnyContent;
import play.libs.Json;
import play.mvc.*;
import com.typesafe.play.mini.*;

import java.io.IOException;

/**
 * this app is configured through Global.scala
 */
public class App extends Controller {


    /**
     * In standard Play we'd use this line in our Routes file to map resources from the /public folder to the /assets URL path:
     * GET     /assets/*file               controllers.Assets.at(path="/public", file)
     * <p/>
     * But here in the mini version, I don't know how to do it. I tried for a while to coerce the output of
     * controllers.Assets.at("/public", file)
     * into a Result, but wasn't successful. I don't understand the internals that well.
     * <p/>
     * So I've rewritten a very basic version, using Spring's resource handling under the covers.
     * <p/>
     * Some funny side-effects of this version:
     * <ul>
     * <li>It doesn't hot-reload. Normal Play framework would, of course. But if you add or change files, you won't see them reflected right away like you'd expect. </li>
     * <p/>
     * </ul>
     *
     * @param file
     * @return
     */
    @URL("/assets/*")
    public static Result getAssets(String file) {
        ClassPathResource r = new ClassPathResource(file);
        if (!r.exists()) {
            return notFound("Asset " + file + " not found.");
        } else if (!r.isReadable()) {
            return forbidden("Unable to open " + file);
        } else {
            try {
                return ok(r.getFile());
            } catch (IOException e) {
                if (Logger.isDebugEnabled()) {
                    // don't show an exception on the page unless we're in debug mode
                    return internalServerError("Error retrieving " + file + ": " + e.toString());
                } else {
                    return internalServerError("Error retrieving " + file);
                }
            }
        }
    }


    @URL("/*/name/*")
    public static Result index(String string1, String string2) {
        response().setContentType("text/html");
        return ok("It works. No, really, it does! " + string1 + " " + string2);
    }

    /**
     * Given the current state of the world as input, advance one generation.
     *
     * @return
     */
    @URL("/next")
    public static Result next() {
//        Logger.info("Got a gameboard: " + o);
        Logger.info("Got request: "+ request().body().asText());
        Logger.info("request: "+ request().toString());

        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            Logger.info("As JSON: " + json);
        }
        ObjectNode result = Json.newObject();
        ArrayNode resultArray = result.arrayNode();
        for (int i = 0; i < 3; i++) {
            ArrayNode row = result.arrayNode();
            for (int j = 0; j < 3; j++) {
                row.add(0);
            }
            resultArray.add(row);
        }
        result.put("nextGeneration", resultArray);
        return ok(result);
    }

    @URL("/")
    public static Result plain() {
        return redirect("/assets/index.html");
    }

//
//
//

}
