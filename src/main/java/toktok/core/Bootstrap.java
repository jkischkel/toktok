package toktok.core;

public class Bootstrap {

    @Ctrl
    static class SampleController implements JsonController {{

        get("/",  req -> text("hello"));
        get("//", req -> text("double"));

        get("/users", req -> text("users"));
        get("/posts", req -> text("posts"));

        get("/sounds/:id", req -> text("sounds"));
    }}

    public static void main(String[] args) throws Exception {
       AnnotationScanner.createAllIn("toktok.core", Ctrl.class);

       Start.jetty(RouteMatcher.DefaultMatcher.instance.matcher());
    }
}
