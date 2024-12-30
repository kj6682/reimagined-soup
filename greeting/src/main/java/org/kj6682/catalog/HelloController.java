package org.kj6682.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Environment environment;

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String index() {
        return helloService.sayHello() + this.environment.getActiveProfiles()[0];
    }

}