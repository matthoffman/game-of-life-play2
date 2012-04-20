package org.mhoffman.life;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import play.*;
import play.api.mvc.AnyContent;
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
     *
     * But here in the mini version, I don't know how to do it. I tried for a while to coerce the output of
     * controllers.Assets.at("/public", file)
     * into a Result, but wasn't successful. I don't understand the internals that well.
     *
     * So I've rewritten a very basic version, using Spring's resource handling under the covers.
     *
     * Some funny side-effects of this version:
     * <ul>
     * <li>It doesn't hot-reload. Normal Play framework would, of course. But if you add or change files, you won't see them reflected right away like you'd expect. </li>
     *
     * </ul>
     * @param file
     * @return
     */
    @URL("/assets/*")
    public static Result getAssets(String file) {
       ClassPathResource r = new ClassPathResource(file);
        if (!r.exists()) {
            return notFound("Asset "+ file+" not found.");
        } else if (!r.isReadable()) {
            return forbidden("Unable to open "+ file);
        } else {
            try {
                return ok(r.getFile());
            } catch (IOException e) {
                if (Logger.isDebugEnabled()) {
                    // don't show an exception on the page unless we're in debug mode
                    return internalServerError("Error retrieving "+ file+": "+ e.toString());
                } else {
                    return internalServerError("Error retrieving "+ file);
                }
            }
        }
    }


    @URL("/*/name/*")
    public static Result index(String string1, String string2) {
        response().setContentType("text/html");
        return ok("It works. No, really, it does! " + string1 + " " + string2);
    }

    @URL("/")
    public static Result plain() {
        return redirect("/assets/index.html");
    }

//
//
//

}
