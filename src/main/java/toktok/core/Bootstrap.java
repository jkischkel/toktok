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
        new SampleController();
        Start.jetty(DefaultRouteMatcher.instance.matcher());
    }
}
