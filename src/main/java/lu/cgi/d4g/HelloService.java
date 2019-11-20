package lu.cgi.d4g;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {
    public String hello() {
        return "Hello!";
    }

    public String hello(String name) {
        return "Hello, " + name + "!";
    }
}
